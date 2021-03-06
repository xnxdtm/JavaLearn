package com.dongbao.portal.web.interceptor;

import com.dongbao.common.base.JwtUtil;
import com.dongbao.common.base.annotations.TokenCheck;
import com.dongbao.portal.web.exception.TokenException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author zhangjq
 * @version 1.0
 * @Description com.dongbao.portal.web.AuthInterceptor
 */
public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        // 如果方法上添加 @TokenCheck(required = false) 则不校验token
        if (method.isAnnotationPresent(TokenCheck.class)) {
            TokenCheck tokenCheck = method.getAnnotation(TokenCheck.class);
            if (!tokenCheck.required()) {
                return true;
            }
        }
        // 校验 token
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) throw new TokenException("token为空");
        try {
            JwtUtil.parseToken(token);
            return true;
        } catch (Exception e) {
            throw new TokenException("token异常");
        }
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {
    }
}
