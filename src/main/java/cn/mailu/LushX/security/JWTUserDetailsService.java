package cn.mailu.LushX.security;

import cn.mailu.LushX.entity.User;
import cn.mailu.LushX.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @Author: NULL
 * @Description:实现UserDetailsService接口
 * @Date: Create in 2017/11/5 23:33
 */

@Service
public class JWTUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepository.findByUsername(username);
        if(user==null){
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        }else{
            return JWTUserFactory.create(user);
        }
    }
}
