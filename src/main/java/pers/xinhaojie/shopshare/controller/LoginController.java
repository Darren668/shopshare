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

import javax.servlet.http.Cookie;
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
    public String login(@RequestParam @Validated String email, @RequestParam @Validated String password, Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
        //store the email
        model.addAttribute("email", email);
        //check the information
        User user;
        try {
            user = checkService.checkLoginInformation(email, password);
            //set the accessToken into cookie
            String token = tokenService.loginAndCreateToken(user);
            Cookie cookie = new Cookie("accessToken", token);
            cookie.setMaxAge(ConstantValue.COOKIE_EXPIRE_TIME);
            //表示当前项目下都携带这个cookie
            cookie.setPath(request.getContextPath());
            response.addCookie(cookie);
        } catch (Exception e) {
            model.addAttribute("msg", e.getMessage());
            return "login";
        }
        HttpSession session = request.getSession();
        //login successfully then remove the warning message of token
        session.removeAttribute("tokenMsg");
        session.setAttribute("user",user);
        return "redirect:/";
    }

    //register
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String register(@RequestParam String username, @RequestParam String email, @RequestParam String password, HttpServletRequest request, Model model, HttpServletResponse response) {
        model.addAttribute("username", username);
        model.addAttribute("email", email);
        try {
            checkService.checkRegisterInfo(username, email, password);
        } catch (Exception e) {
            model.addAttribute("msg", e.getMessage());
            return "register";
        }
        //insert the new user into database
        User user = new User(null, username, password, email, (byte) 1, null, null);
        userService.save(user);
        //set the accessToken into cookie
        String token = tokenService.loginAndCreateToken(user);
        Cookie cookie = new Cookie("accessToken", token);
        cookie.setMaxAge(ConstantValue.COOKIE_EXPIRE_TIME);
        //表示当前项目下都携带这个cookie
        cookie.setPath(request.getContextPath());
        response.addCookie(cookie);
        //store user information into session
        request.getSession().setAttribute("user",user);
        return "redirect:/";
    }

    @RequestMapping(value = "update/password")
    public String updatePassword(@RequestParam String email, @RequestParam String password1, @RequestParam String password2, Model model) {
        model.addAttribute("email", email);
        try {
            checkService.checkNewPassword(email, password1, password2);
        } catch (Exception e) {
            model.addAttribute("msg", e.getMessage());
            return "updatePass";
        }
        //update the password
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("email", email).set("password", password1);
        userService.update(updateWrapper);
        //reset the status of token
        return "redirect:/loginPage";
    }

    @RequestMapping(value = "logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        //clear the information in session
        User user = (User)session.getAttribute("user");
        session.removeAttribute("user");
        //clear and reset the cookie of token
        Cookie emptyCookie = new Cookie("accessToken", "");
        emptyCookie.setMaxAge(0);
        emptyCookie.setPath(request.getContextPath());
        response.addCookie(emptyCookie);
        //clear the token in redis
        if(user != null) {
            tokenService.removeTokenByKey(ConstantValue.JWT_TOKEN_REDIS_KEY_PREFIX + user.getId());
        }
        return "redirect:/";
    }
}
