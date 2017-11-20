package cn.mailu.LushX.fliter;

import cn.mailu.LushX.util.JWTUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * @Author: NULL
 * @Description:
 * @Date: Create in 2017/11/6 9:18
 */
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private static Logger logger= LoggerFactory.getLogger(JWTAuthenticationFilter.class);

    @Value("${jwt.header}")
    private String token_header;

    @Autowired
    private UserDetailsService userDetailsService;

    @Resource
    private JWTUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        logger.info("=====请求地址==="+request.getRequestURL());
        logger.info("====requestMethod{}====",request.getMethod());
        Enumeration e = request.getHeaderNames();
        while (e.hasMoreElements()){
            logger.info("====requestHeader===={}",request.getHeader((String)e.nextElement()));
        }
        String auth_token = request.getHeader(this.token_header);
        logger.info("=====请求头信息==="+auth_token);
        final String auth_token_start = "Bearer ";
        if (StringUtils.isNotEmpty(auth_token) && auth_token.startsWith(auth_token_start)) {
            auth_token = auth_token.substring(auth_token_start.length());
        } else {
            // 不按规范,不允许通过验证
            auth_token = null;
        }
        String username = jwtUtils.getUsernameFromToken(auth_token);
        logger.info(String.format("Checking authentication for user %s.", username));

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // It is not compelling necessary to load the use details from the database. You could also store the information
            // in the token and read it from it. It's up to you ;)
            // UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            UserDetails userDetails = jwtUtils.getUserFromToken(auth_token);
            logger.info(userDetails.getUsername());
            // For simple validation it is completely sufficient to just check the token integrity. You don't have to call
            // the database compellingly. Again it's up to you ;)
            if (jwtUtils.validateToken(auth_token, userDetails)) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                logger.info(String.format("Authenticated user %s, setting security context", username));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);

    }
}
