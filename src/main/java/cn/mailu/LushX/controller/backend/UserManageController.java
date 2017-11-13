package cn.mailu.LushX.controller.backend;

import cn.mailu.LushX.common.ServerResponse;
import cn.mailu.LushX.entity.User;
import cn.mailu.LushX.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author: NULL
 * @Description:后台用户管理
 * @Date: Create in 2017/11/5 19:56
 */
@RestController
@RequestMapping("/manage/user")
@Api(value = "UserManageController",description = "后台用户管理接口")
public class UserManageController {

    @Autowired
    private UserService userService;

    @ApiOperation(value="获取用户", notes="获取用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "第几页",defaultValue = "0",required = false,paramType ="query"),
            @ApiImplicitParam(name = "pageSize", value = "页大小",defaultValue = "10",required = false,paramType ="query")
    })
    @GetMapping
    public ServerResponse<Page<User>> getUser(@RequestParam(value = "pageNum",defaultValue ="0") int pageNum,
                                              @RequestParam(value = "pageSize",defaultValue = "10") int pageSize){
        Pageable pageable=new PageRequest(pageNum,pageSize);
        Page<User> page=userService.findAll(pageable);
        return ServerResponse.createBySuccess(page);
    }

}
