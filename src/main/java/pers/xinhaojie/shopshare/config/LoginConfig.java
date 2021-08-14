package pers.xinhaojie.shopshare.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author xin haojie
 * @create 2021-07-14-0:22
 */
@Configuration
public class LoginConfig implements WebMvcConfigurer {
    //防止后退或者刷新导致重新提交表单
    //    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/main.html");
//    }
}
