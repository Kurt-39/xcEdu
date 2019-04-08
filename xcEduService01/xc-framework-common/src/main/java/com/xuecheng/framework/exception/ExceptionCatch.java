package com.xuecheng.framework.exception;

import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.framework.model.response.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;



@ControllerAdvice
public class ExceptionCatch {
   private static final Logger LOGGER =  LoggerFactory.getLogger(ExceptionCatch.class);
   //捕获CustomException
    @ExceptionHandler
    @ResponseBody
    public ResponseResult customException(CustomException e){
        LOGGER.error(e.getMessage());
        ResultCode resultCode = e.getResultCode();
        ResponseResult responseResult = new ResponseResult(resultCode);
        return responseResult;
    }
}
