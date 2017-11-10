package cn.mailu.LushX.fliter;

import cn.mailu.LushX.common.ServerResponse;
import cn.mailu.LushX.entity.User;
import cn.mailu.LushX.util.JWTUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;

/**
 * @Author: NULL
 * @Description:处理/login
 * @Date: Create in 2017/11/6 11:28
 */
public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

    private Logger logger= LoggerFactory.getLogger(JWTLoginFilter.class);

    @Value("${jwt.header}")
    private String token_header;

    @Resource
    private JWTUtils jwtUtils;

    public JWTLoginFilter(String defaultFilterProcessesUrl, AuthenticationManager authenticationManager) {
        super(new AntPathRequestMatcher(defaultFilterProcessesUrl));
        setAuthenticationManager(authenticationManager);
    }

    // 接收并解析用户凭证
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        try {
            // 反序列化问题,前台需要post和JSON.strify
            /*User user = new ObjectMapper()
                    .readValue(request.getInputStream(), User.class);*/
            //登录ajax失效
            BufferedReader streamReader = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
            StringBuilder responseStrBuilder = new StringBuilder();
            String inputStr;
            while ((inputStr = streamReader.readLine()) != null) responseStrBuilder.append(inputStr);
            User user = new ObjectMapper().readValue(responseStrBuilder.toString(),User.class);
            logger.info(user.getPassword());
            logger.info(user.getUsername());
            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getUsername(),
                            user.getPassword(),
                            new ArrayList<>())
            );
        } catch (IOException e) {
            logger.error("登录验证异常");
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        UserDetails user=(UserDetails) authResult.getPrincipal();
        String token=jwtUtils.generateAccessToken(user);
        Map<String,Object> map= Maps.newHashMap();
        map.put(token_header,"Bearer "+token);
        map.put("info",user);
        ObjectMapper mapper=new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().print(mapper.writeValueAsString(ServerResponse.createBySuccess(map)));
        logger.info(((UserDetails) authResult.getPrincipal()).getUsername()+"验证成功，发出token");
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        ObjectMapper mapper=new ObjectMapper();
        response.getWriter().print(mapper.writeValueAsString(ServerResponse.createByErrorMessage("用户名或密码错误")));
    }
}
