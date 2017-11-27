package cn.mailu.LushX.controller.protal;

import cn.mailu.LushX.common.ServerResponse;
import cn.mailu.LushX.constant.RedisKey;
import cn.mailu.LushX.constant.VideoTypeEnum;
import cn.mailu.LushX.entity.Article;
import cn.mailu.LushX.entity.User;
import cn.mailu.LushX.entity.Video;
import cn.mailu.LushX.security.JWTUserDetails;
import cn.mailu.LushX.security.JWTUserFactory;
import cn.mailu.LushX.service.*;
import cn.mailu.LushX.util.CommonUtils;
import cn.mailu.LushX.util.JWTUtils;
import cn.mailu.LushX.util.MD5Utils;
import cn.mailu.LushX.vo.UserVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.collect.Maps;
import io.swagger.annotations.*;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Author: NULL
 * @Description:
 * @Date: Create in 2017/11/5 19:57
 */
@RestController
@Api(value = "UserController", description = "用户相关接口")
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private ArticleRepertoryService articleRepertoryService;

    @Autowired
    private VideoRepertoryService videoRepertoryService;

    @Autowired
    private RedisService redisService;


    @Autowired
    private FileService fileService;

    @Value("${jwt.header}")
    private String token_header;

    @Resource
    private JWTUtils jwtUtils;

    @ApiOperation(value = "注册用户", notes = "根据User对象创建用户")
    @ApiImplicitParam(name = "user", value = "只需要username和password字段", required = true, dataType = "User")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ServerResponse<String> register(@RequestBody User user) {
        return userService.register(user);
    }

    @ApiOperation(value = "用户登录")
    @ApiImplicitParam(name = "user", value = "只需要username和password字段", required = true, dataType = "User")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ServerResponse login(@RequestBody User user) {
        User userNew = null;
        userNew = userService.findByUsernameAndPassword(user.getUsername(), MD5Utils.MD5EncodeUtf8(user.getPassword()));
        if (userNew != null) {
          return generateUserVO(userNew);
        }
        return ServerResponse.createByErrorMessage("用户名或密码错误");
    }

    @ApiOperation(value = "用户首页", notes = "用户首页")
    @GetMapping("/u")
    public ServerResponse userspace(@AuthenticationPrincipal JWTUserDetails jwtuser) {
        if (jwtuser != null) {
            User user = userService.selectById(jwtuser.getUserId());
            UserVO userVo = UserVO.toUserVO(user);
            Pageable pageable = new PageRequest(0, 10);
            Map map = Maps.newHashMap();
            map.put("article", articleRepertoryService.getLikeArticleListByUserId(user.getUserId(), pageable));
            map.put("video", videoRepertoryService.getLikeVideoListByUserId(user.getUserId(),pageable));
            userVo.setCollection(map);
            return ServerResponse.createBySuccess(userVo);
        }
        return ServerResponse.createByErrorMessage("未登录");
    }


    @ApiOperation(value = "更新用户头像", notes = "更新用户头像")
    @ApiImplicitParam(name = "headImg", value = "headImg传base64字符串", required = true, dataType = "String")
    @PostMapping("/u/avatar")
    public ServerResponse updateAvatar(@AuthenticationPrincipal JWTUserDetails jwtuser, @ApiParam(hidden = true) @RequestBody User user) {
        if (jwtuser != null) {
            Map map = fileService.uploadImage(user.getHeadImg());
            if (((int) map.get("status") == 0)) {
                String headImg = (String) map.get("message");
                logger.info("图片地址{}", headImg);
                user.setUserId(jwtuser.getUserId());
                user.setHeadImg(headImg);
                if (userService.updateSelective(user) == null) {
                    return ServerResponse.createByErrorMessage("更新用户头像错误");
                }
                return ServerResponse.createBySuccess(headImg);
            }
            return ServerResponse.createByErrorMessage("图片上传错误");
        }
        return ServerResponse.createByErrorMessage("未登录");
    }

    @ApiOperation(value = "更新用户信息", notes = "更新用户信息")
    @PostMapping("/u")
    public ServerResponse updateUser(@AuthenticationPrincipal @ApiParam(hidden = true) JWTUserDetails jwtuser, @ApiParam(required = true) @RequestBody User user) {
        if (jwtuser != null) {
            user.setUserId(jwtuser.getUserId());
            if (StringUtils.isNotEmpty(user.getPassword())) {
                user.setPassword(MD5Utils.MD5EncodeUtf8(user.getPassword()));
            }
            if (StringUtils.isNotEmpty(user.getUsername())) {
                User res = userService.findByUsername(user.getUsername());
                if (res != null) {
                    return ServerResponse.createByErrorMessage("用户名已存在");
                }
                user.setMd5(MD5Utils.MD5EncodeUtf8(user.getUsername()));
            }
            User userNew = userService.updateSelective(user);
            if (userNew != null) {
                return generateUserVO(userNew);
            }
            return ServerResponse.createByErrorMessage("更新信息失败");
        }
        return ServerResponse.createByErrorMessage("未登录");
    }

    private ServerResponse generateUserVO(User userNew) {
        String token = null;
        try {
            token = jwtUtils.generateAccessToken(JWTUserFactory.create(userNew));
            Map<String, Object> map = Maps.newHashMap();
            map.put(token_header, "Bearer " + token);
            UserVO userVo = UserVO.toUserVO(userNew);
            Pageable pageable = new PageRequest(0, 10);
            Map map2 = Maps.newHashMap();
            map2.put("video", videoRepertoryService.getLikeVideoListByUserId(userNew.getUserId(), pageable));
            map2.put("article", articleRepertoryService.getLikeArticleListByUserId(userNew.getUserId(), pageable));
            userVo.setCollection(map2);
            map.put("info", userVo);
            logger.info("验证成功，发出token");
            return ServerResponse.createBySuccess(map);
        } catch (JsonProcessingException e) {
            logger.error("generateAccessToken error");
        }
        return ServerResponse.createByError();
    }

}
