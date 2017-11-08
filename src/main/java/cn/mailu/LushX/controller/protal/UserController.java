package cn.mailu.LushX.controller.protal;

import cn.mailu.LushX.common.ServerResponse;
import cn.mailu.LushX.entity.User;
import cn.mailu.LushX.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author: NULL
 * @Description:
 * @Date: Create in 2017/11/5 19:57
 */
@RestController
@RequestMapping("/user")
@Api("用户相关接口")
public class UserController {

    private static Logger logger= LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;


}
