package cn.mailu.LushX.service.impl;

import cn.mailu.LushX.entity.User;
import cn.mailu.LushX.repository.UserRepository;
import cn.mailu.LushX.util.MD5Util;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.junit.Assert.*;

/**
 * @Author: NULL
 * @Description:
 * @Date: Create in 2017/11/6 17:10
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testRegister() throws Exception {
        User user = new User();
        user.setUsername("test");
        user.setPassword(MD5Util.MD5EncodeUtf8("test"));
        user.setGender("n");
        user.setMd5(MD5Util.MD5EncodeUtf8("test"));
        user.setUserId("23131");
        user.setRole("ROLE_USER");
        userRepository.save(user);
    }
}