package pers.xinhaojie.shopshare.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pers.xinhaojie.shopshare.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author xin haojie
 * @create 2021-08-14-22:53
 */
@Controller
public class IndexController {

    @RequestMapping("/")
    public String toIndexPage(HttpServletRequest request){
        //check the accessToken
        //        HttpSession session = request.getSession();
        //        User user = (User)session.getAttribute("user");
        //        if(user != null){
        //            session.setAttribute("user",user);
        //        }
        return "index";
    }
    @RequestMapping("main")
    public String toMainPage(){
        return "main";
    }
    @RequestMapping("loginPage")
    public String toLoginPage(){
        return "login";
    }

    @RequestMapping("registerPage")
    public String toRegisterPage(){
        return "register";
    }

    //update the password
    @RequestMapping(value = "updatePassPage")
    public String updatePassword(){
        return "updatePass";
    }

    //public page
    @RequestMapping(value = "publishPage")
    public String toPublishPage(){
        return "publish";
    }
}
