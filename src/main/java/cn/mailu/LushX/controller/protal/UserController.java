package cn.mailu.LushX.controller.protal;

import cn.mailu.LushX.common.ServerResponse;
import cn.mailu.LushX.entity.Article;
import cn.mailu.LushX.entity.User;
import cn.mailu.LushX.security.JWTUserDetails;
import cn.mailu.LushX.service.FileService;
import cn.mailu.LushX.service.UserService;
import cn.mailu.LushX.vo.UserVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

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
    public void login( @RequestBody User user){
    }

    @ApiOperation(value="用户首页", notes="用户首页")
    @GetMapping("/u")
    public ServerResponse<UserVO> userspace(@AuthenticationPrincipal JWTUserDetails jwtuser){
        User user=userService.selectById(jwtuser.getUserId());
        UserVO userVo=toUserVO(user);
        return ServerResponse.createBySuccess(userVo);
    }

    @ApiOperation(value="更新用户头像", notes="更新用户头像")
    @ApiImplicitParam(name = "imgStr", value = "base64", required = true)
    @PutMapping("/u/avatar")
    public ServerResponse updateAvatar(@AuthenticationPrincipal JWTUserDetails jwtuser,@RequestBody String imgStr ){
        logger.info(imgStr);
        Map map=fileService.uploadImage(imgStr);
        if(((int)map.get("status")==0)){
            String headImg= (String) map.get("message");
            logger.info("图片地址{}",headImg);
            User user=userService.selectById(jwtuser.getUserId());
            user.setHeadImg(headImg);
            if(userService.save(user)==null){
                return ServerResponse.createByErrorMessage("更新用户头像错误");
            }
            return ServerResponse.createBySuccess(headImg);
        }
        return ServerResponse.createByErrorMessage("图片上传错误");
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
