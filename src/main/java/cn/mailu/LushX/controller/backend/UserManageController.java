package cn.mailu.LushX.controller.backend;

import cn.mailu.LushX.common.ServerResponse;
import cn.mailu.LushX.entity.User;
import cn.mailu.LushX.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
@PreAuthorize("hasAuthority('ROLE_ADMIN')")   //管理员权限才能操作
@EnableSwagger2
public class UserManageController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    public ServerResponse<Page<User>> getUser(@RequestParam(value = "pageNum",defaultValue ="0") int pageNum,
                                              @RequestParam(value = "pageSize",defaultValue = "10") int pageSize){
        Pageable pageable=new PageRequest(pageNum,pageSize);
        Page<User> page=userService.findAll(pageable);
        return ServerResponse.createBySuccess(page);
    }

}
