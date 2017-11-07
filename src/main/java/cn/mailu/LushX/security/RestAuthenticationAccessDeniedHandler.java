package cn.mailu.LushX.security;

import cn.mailu.LushX.common.ResponseCode;
import cn.mailu.LushX.common.ServerResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: NULL
 * @Description:权限不足返回
 * @Date: Create in 2017/11/7 11:52
 */
public class RestAuthenticationAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        ObjectMapper mapper=new ObjectMapper();
        response.getWriter().print(mapper.writeValueAsString(ServerResponse.createByErrorCodeMessage(ResponseCode.ACCESS_DENIED.getCode(),"权限不足")));
    }
}
