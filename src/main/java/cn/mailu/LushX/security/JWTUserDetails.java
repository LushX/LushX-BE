package cn.mailu.LushX.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @Author: NULL
 * @Description:实现UserDetail接口
 * @Date: Create in 2017/11/5 23:24
 */
@ApiModel
public class JWTUserDetails implements UserDetails {

    @ApiModelProperty(hidden = true)
    private String userId;
    @ApiModelProperty(hidden = true)
    private String username;
    @ApiModelProperty(hidden = true)
    private String password;
    @ApiModelProperty(hidden = true)
    private String headImg;
    @ApiModelProperty(hidden = true)
    private Collection<? extends GrantedAuthority> authorities;

    public JWTUserDetails(String userId, String username, String password,String headImg, Collection<? extends GrantedAuthority> authorities) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.headImg=headImg;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }


    public String getUserId() {
        return userId;
    }


    public String getHeadImg() {
        return headImg;
    }
}

