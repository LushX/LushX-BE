package cn.mailu.LushX.security;

import cn.mailu.LushX.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: NULL
 * @Description:创建JWTUser工厂类
 * @Date: Create in 2017/11/5 23:40
 */
public final class JWTUserFactory{

    private JWTUserFactory(){

    }

    public static JWTUserDetails create(User user){
        return new JWTUserDetails(
                user.getUserId(),
                user.getUsername(),
                user.getPassword(),
                user.getHeadImg(),
                mapToGrantedAuthorities(user.getRole())
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(String role){
        List<GrantedAuthority> authorities = new ArrayList<>();
        SimpleGrantedAuthority authority;
        authority = new SimpleGrantedAuthority(role.toString());
        authorities.add(authority);
        return authorities;
    }

}