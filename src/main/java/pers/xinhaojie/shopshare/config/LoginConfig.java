package pers.xinhaojie.shopshare.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pers.xinhaojie.shopshare.interceptor.LoginTokenInterceptor;

/**
 * @author xin haojie
 * @create 2021-07-14-0:22
 */
@Configuration
public class LoginConfig implements WebMvcConfigurer {

    @Autowired
    LoginTokenInterceptor loginTokenInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration ir = registry.addInterceptor(loginTokenInterceptor);
        ir.addPathPatterns("/auth/**");
    }





}
