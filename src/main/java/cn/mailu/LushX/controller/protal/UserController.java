package cn.mailu.LushX.controller.protal;

import cn.mailu.LushX.common.ServerResponse;
import cn.mailu.LushX.entity.Article;
import cn.mailu.LushX.entity.User;
import cn.mailu.LushX.security.JWTUserDetails;
import cn.mailu.LushX.security.JWTUserFactory;
import cn.mailu.LushX.service.FileService;
import cn.mailu.LushX.service.UserService;
import cn.mailu.LushX.util.JWTUtils;
import cn.mailu.LushX.util.MD5Utils;
import cn.mailu.LushX.vo.UserVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Author: NULL
 * @Description:
 * @Date: Create in 2017/11/5 19:57
 */
@RestController
@Api(value ="UserController", description= "用户相关接口")
public class UserController {

    private static Logger logger= LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private FileService fileService;

    @Value("${jwt.header}")
    private String token_header;

    @Resource
    private JWTUtils jwtUtils;

    @ApiOperation(value="注册用户", notes="根据User对象创建用户")
    @ApiImplicitParam(name = "user", value = "只需要username和password字段", required = true, dataType = "User")
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public ServerResponse<String> register(@RequestBody User user){
        logger.info(user.getGender());
        logger.info(user.getUsername());
        return userService.register(user);
    }

    @ApiOperation(value="用户登录")
    @ApiImplicitParam(name = "user", value = "只需要username和password字段", required = true, dataType = "User")
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ServerResponse login( @RequestBody User user){
        User userNew=null;
        logger.info("username:{}",user.getUsername());
        logger.info("password:{}",user.getPassword());
        logger.info("password:{}",MD5Utils.MD5EncodeUtf8(user.getPassword()));
        userNew=userService.findByUsernameAndPassword(user.getUsername(), MD5Utils.MD5EncodeUtf8(user.getPassword()));
        if(userNew!=null){
            String token= null;
            try {
                token = jwtUtils.generateAccessToken(JWTUserFactory.create(userNew));
                Map<String,Object> map= Maps.newHashMap();
                map.put(token_header,"Bearer "+token);
                map.put("info",toUserVO(userNew));
                logger.info("验证成功，发出token");
                return ServerResponse.createBySuccess(map);
            } catch (JsonProcessingException e) {
                logger.error("generateAccessToken error");
            }
        }
        return ServerResponse.createByErrorMessage("用户名或密码错误");
    }

    @ApiOperation(value="用户首页", notes="用户首页")
    @GetMapping("/u")
    public ServerResponse<UserVO> userspace(@AuthenticationPrincipal JWTUserDetails jwtuser){
        if(jwtuser!=null){
            User user=userService.selectById(jwtuser.getUserId());
            UserVO userVo=toUserVO(user);
            return ServerResponse.createBySuccess(userVo);
        }
        return ServerResponse.createByErrorMessage("未登录");
    }



    @ApiOperation(value="更新用户头像", notes="更新用户头像")
    @ApiImplicitParam(name = "headImg", value = "headImg传base64字符串", required = true,dataType ="String")
    @PostMapping("/u/avatar")
    public ServerResponse updateAvatar(@AuthenticationPrincipal JWTUserDetails jwtuser, @ApiParam(hidden = true)@RequestBody User user ){

        Map map=fileService.uploadImage(user.getHeadImg());
        if(((int)map.get("status")==0)){
            String headImg= (String) map.get("message");
            logger.info("图片地址{}",headImg);
            user.setUserId(jwtuser.getUserId());
            user.setHeadImg(headImg);
            if(userService.save(user)==null){
                return ServerResponse.createByErrorMessage("更新用户头像错误");
            }
            return ServerResponse.createBySuccess(headImg);
        }
        return ServerResponse.createByErrorMessage("图片上传错误");
    }

    @ApiOperation(value="更新用户信息", notes="更新用户信息")
    @PostMapping("/u")
    public ServerResponse updateUser(@AuthenticationPrincipal @ApiParam(hidden = true)  JWTUserDetails jwtuser,@ApiParam(required = true) @RequestBody User user ){
        user.setUserId(jwtuser.getUserId());
        user.setPassword(MD5Utils.MD5EncodeUtf8(user.getPassword()));
        if(userService.save(user)!=null){
            return ServerResponse.createBySuccess();
        }
        return ServerResponse.createByErrorMessage("更新密码失败");
    }

    //生成UserVO
    private UserVO toUserVO(User user){
        UserVO userVO=new UserVO();
        userVO.setUserId(user.getUserId());
        userVO.setHeadImg(user.getHeadImg());
        userVO.setUsername(user.getUsername());
        return userVO;
    }


}
