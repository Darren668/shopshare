package pers.xinhaojie.shopshare.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pers.xinhaojie.shopshare.interceptor.LoginTokenInterceptor;
import pers.xinhaojie.shopshare.interceptor.UserStatusIntercepter;

/**
 * @author xin haojie
 * @create 2021-07-14-0:22
 */
@Configuration
public class LoginConfig implements WebMvcConfigurer {

    @Autowired
    LoginTokenInterceptor loginTokenInterceptor;

    @Autowired
    UserStatusIntercepter userStatusIntercepter;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration userAuthIr = registry.addInterceptor(loginTokenInterceptor);
        userAuthIr.addPathPatterns("/auth/**");

        InterceptorRegistration userStatusIr = registry.addInterceptor(userStatusIntercepter);
        userStatusIr.addPathPatterns("/");
    }
}
