package cn.mailu.LushX.exception;

import cn.mailu.LushX.common.ResponseCode;
import cn.mailu.LushX.common.ServerResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: NULL
 * @Description:全局异常处理
 * @Date: Create in 2017/11/29 17:35
 */
@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

    private Logger logger= LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = NoHandlerFoundException.class)
    public ServerResponse noHandlerFoundExceptionHandler(HttpServletRequest req, Exception e) throws Exception{
        return ServerResponse.createByErrorCodeMessage(ResponseCode.NOT_FOUND.getCode(),ResponseCode.NOT_FOUND.getDesc());
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    public ServerResponse defaultExceptionHandler(HttpServletRequest req, Exception e) throws Exception{
        return ServerResponse.createByErrorCodeMessage(ResponseCode.INTERNAL_SERVER_ERROR.getCode(),ResponseCode.INTERNAL_SERVER_ERROR.getDesc());
    }

}
