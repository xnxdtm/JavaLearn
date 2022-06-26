package com.dongbao.portal.web.advice;

import com.dongbao.common.base.enums.StateCodeEnum;
import com.dongbao.common.base.result.ResultWrapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @Auther zhangjq
 * @Date 2022/6/21
 * @Description com.dongbao.portal.web.advice
 * @Version 1.0
 */
@ControllerAdvice
public class ValidateHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        String msg = "";
        for (FieldError fieldError :
                ex.getBindingResult().getFieldErrors()) {
            msg = fieldError.getDefaultMessage();
            break;
        }

        ResultWrapper<Object> res = ResultWrapper.builder().code(StateCodeEnum.PARAMS_ERROR.getCode())
                .msg(msg).build();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
