package com.dongbao.portal.web.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author zhangjq
 * @version 1.0
 * @Description com.dongbao.portal.web.interceptor
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor())
                .addPathPatterns("/**");
        // .excludePathPatterns("/ums/login")
        // .excludePathPatterns("/ums/register")
        // .excludePathPatterns("/myVerify/**");
    }

    @Bean
    public AuthInterceptor authInterceptor() {
        return new AuthInterceptor();
    }
}
