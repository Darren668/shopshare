package pers.xinhaojie.shopshare.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pers.xinhaojie.shopshare.entity.User;
import pers.xinhaojie.shopshare.enums.StatusCode;
import pers.xinhaojie.shopshare.response.ResponseData;
import pers.xinhaojie.shopshare.service.TokenService;
import pers.xinhaojie.shopshare.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
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

    //login
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(@RequestParam String email, @RequestParam String password) throws IOException {

        return "main";
    }



    //register
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String register(@RequestParam String username, @RequestParam String password, @RequestParam String email) throws IOException {

        //insert the new user into database
        User user = new User(null, username, password, email, (byte) 1, null, null);
        userService.save(user);
        //after register and then redirect to login automatically
        return "redirect:login";
    }
}
