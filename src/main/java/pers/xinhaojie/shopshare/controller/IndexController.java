package pers.xinhaojie.shopshare.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author xin haojie
 * @create 2021-08-14-22:53
 */
@Controller
public class IndexController {

//    @RequestMapping("/")
//    public String toMainPage(){
//        return "main";
//    }

    @RequestMapping("loginPage")
    public String toLoginPage(){
        return "login";
    }

    @RequestMapping("registerPage")
    public String toRegisterPage(){
        return "register";
    }

    //update the password
    @RequestMapping(value = "forgetPasswordPage")
    public String updatePassword(){
        return "updatePass";
    }
}
