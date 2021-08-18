package pers.xinhaojie.shopshare.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pers.xinhaojie.shopshare.interceptor.LoginInterceptor;
import pers.xinhaojie.shopshare.interceptor.LoginTokenInterceptor;
import pers.xinhaojie.shopshare.interceptor.RegisterInterceptor;

/**
 * @author xin haojie
 * @create 2021-07-14-0:22
 */
@Configuration
public class LoginConfig implements WebMvcConfigurer {
//
//    @Autowired
//    LoginInterceptor loginInterceptor;
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//
//        //set path pattern to intercept
//        // the / is necessary
//        String[] afterLoginPaths = new String[]{"/login"};
//        registry.addInterceptor(loginInterceptor)
//                .addPathPatterns(afterLoginPaths);
//        String[] registerPaths = new String[]{"/register"};
//        registry.addInterceptor(gerRegisterInterceptor())
//                .addPathPatterns(registerPaths);
//    }
//
//    @Bean
//    public LoginTokenInterceptor getLoginTokenInterceptor(){
//        return new LoginTokenInterceptor();
//    }
//
//    @Bean
//    public RegisterInterceptor gerRegisterInterceptor(){
//        return new RegisterInterceptor();
//    }

}
