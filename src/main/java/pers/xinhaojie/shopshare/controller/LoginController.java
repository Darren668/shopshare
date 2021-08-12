package pers.xinhaojie.shopshare.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pers.xinhaojie.shopshare.bean.User;
import pers.xinhaojie.shopshare.service.LoginService;

import javax.servlet.http.HttpSession;

/**
 * @author xin haojie
 * @create 2021-07-05-8:44
 */
@Controller
public class LoginController {

    @Autowired
    LoginService loginService;
    /**
     * redirect to the login in page first
     * */
    @GetMapping(value = {"/loginPage"})
    public String toLogin(){
        return "login";
    }

    @RequestMapping(value = "/login")
    public String checkLogin(String email, String password, Model model, HttpSession session){
        QueryWrapper<User> queryByEmail = new QueryWrapper<>();
        queryByEmail.eq("email",email);
        User loginUser = loginService.getOne(queryByEmail);
        if(loginUser != null){
            //store the user's information in current session
            session.setAttribute("email",email);
            session.setAttribute("name",loginUser.getName());
            if(password.equals(loginUser.getPassword())){
                return "redirect:/main.html";
            }else{
                model.addAttribute("msg","wrong password");
                return "login";
            }
        }
        else{
            //no user in database, require registeration
            model.addAttribute("msg","no such user, please check email or register first");
            return "login";
        }
    }
}
