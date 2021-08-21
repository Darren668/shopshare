package pers.xinhaojie.shopshare.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pers.xinhaojie.shopshare.entity.User;
import pers.xinhaojie.shopshare.enums.ConstantValue;
import pers.xinhaojie.shopshare.enums.StatusCode;
import pers.xinhaojie.shopshare.response.ResponseData;
import pers.xinhaojie.shopshare.service.TokenService;
import pers.xinhaojie.shopshare.service.UserService;
import pers.xinhaojie.shopshare.service.serviceImpl.CheckServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.io.IOException;

/**
 * @author xin haojie
 * @create 2021-08-14-20:52
 */
@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @Autowired
    TokenService tokenService;

    @Autowired
    CheckServiceImpl checkService;

    //login
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(@RequestParam @Validated String email, @RequestParam @Validated String password, Model model, HttpServletRequest request) throws IOException {
        //store the email
        model.addAttribute("email", email);
        //check the information
        User user;
        try {
            user = checkService.checkLoginInformation(email, password);
        } catch (Exception e) {
            model.addAttribute("msg",e.getMessage());
            return "login";
        }
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        //set expire time of session is 1 hour
        session.setMaxInactiveInterval(ConstantValue.SESSION_EXPIRE_TIME);
        return "index";
    }

    //register
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String register( @RequestParam String username,  @RequestParam String email, @RequestParam String password,HttpServletRequest request, Model model){
        model.addAttribute("username", username);
        model.addAttribute("email", email);
        try {
            checkService.checkRegisterInfo(username, email, password );
        } catch (Exception e) {
            model.addAttribute("msg",e.getMessage());
            return "register";
        }
        //insert the new user into database
        User user = new User(null, username, password, email, (byte) 1, null, null);
        userService.save(user);
        //after register and then redirect to main
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        //set expire time of session is 1 hour
        session.setMaxInactiveInterval(ConstantValue.SESSION_EXPIRE_TIME);
        return "index";
    }

    @RequestMapping(value = "update/password")
    public String updatePassword(@RequestParam String email, @RequestParam String password1, @RequestParam String password2, Model model){
        model.addAttribute("email", email);
        try {
            checkService.checkNewPassword(email, password1, password2);
        } catch (Exception e) {
            model.addAttribute("msg",e.getMessage());
            return "updatePass";
        }
        //update the password
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("email", email).set("password", password1);
        userService.update(updateWrapper);
        return "login";
    }
}
