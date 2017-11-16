package cn.mailu.LushX.config;

import cn.mailu.LushX.fliter.JWTAuthenticationFilter;
import cn.mailu.LushX.security.RestAuthenticationAccessDeniedHandler;
import cn.mailu.LushX.security.RestAuthenticationEntryPoint;
import cn.mailu.LushX.util.JWTUtils;
import cn.mailu.LushX.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

/**
 * @Author: NULL
 * @Description:sercurity配置类
 * @Date: Create in 2017/11/5 22:47
 */

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Resource
    private JWTUtils jwtUtils;

    @Override
    public void configure(WebSecurity web) throws Exception {
        //放行swagger
        web.ignoring().antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources/**", "/configuration/**", "/swagger-ui.html", "/webjars/**");
    }

    //配置http的安全配置
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // 由于使用的是JWT，我们这里不需要csrf
                .csrf().disable()
                // 基于token，所以不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                // 所有 / 的所有请求 都放行
                .antMatchers("/*").permitAll()
                .antMatchers("/article/**").permitAll()
                .antMatchers("/video/**").permitAll()
                .antMatchers("/api/**").permitAll()
                .antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources/**", "/configuration/**", "/swagger-ui.html", "/webjars/**")
                .permitAll()
                .antMatchers("/manage/**").hasRole("ADMIN") // 需要相应的角色才能访问
                 //允许对于网站静态资源的无授权访问
                .antMatchers(
                        HttpMethod.GET,
                        "/",
                        "/*.html",
                        "/favicon.ico",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js"
                ).permitAll()

                // 对于获取token的rest api要允许匿名访问
                //.antMatchers("/auth/**").permitAll()
                // 除上面外的所有请求全部需要鉴权认证
                .anyRequest().authenticated();

        // 禁用缓存
        http.headers().cacheControl();
        // 添加JWT filter
        http.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
        //添加未授权处理
        http.exceptionHandling().authenticationEntryPoint(getAuthenticationEntryPoint());
        //全新不足处理
        http.exceptionHandling().accessDeniedHandler(getAccessDeniedHandler());

    }

    @Bean
    public JWTAuthenticationFilter authenticationTokenFilterBean() throws Exception {
        return new JWTAuthenticationFilter();
    }



    @Bean
    public AuthenticationEntryPoint getAuthenticationEntryPoint(){
        return new RestAuthenticationEntryPoint();
    }

    @Bean
    public AccessDeniedHandler getAccessDeniedHandler(){
        return new RestAuthenticationAccessDeniedHandler();
    }

    //使用MD5加密
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return MD5Utils.MD5EncodeUtf8((String)charSequence);
            }

            @Override
            public boolean matches(CharSequence charSequence, String encodedPassword) {
                return encodedPassword.equals(MD5Utils.MD5EncodeUtf8((String)charSequence));
            }
        });
    }
}
