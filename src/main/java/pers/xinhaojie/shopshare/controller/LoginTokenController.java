package pers.xinhaojie.shopshare.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.sun.org.apache.regexp.internal.RE;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pers.xinhaojie.shopshare.entity.User;
import pers.xinhaojie.shopshare.enums.StatusCode;
import pers.xinhaojie.shopshare.response.ResponseData;
import pers.xinhaojie.shopshare.service.ResponseService;
import pers.xinhaojie.shopshare.service.TokenService;
import pers.xinhaojie.shopshare.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author xin haojie
 * @create 2021-07-05-8:44
 */
@RestController
@RequestMapping(value = "login")
public class LoginTokenController {

    @Autowired
    UserService userService;

    @Autowired
    TokenService tokenService;

    @Autowired
    ResponseService responseService;


    //login with email and password
    @RequestMapping(value = "token/email/pass", method = RequestMethod.POST)
    public ResponseData<Object> loginAndSetToken(@RequestParam String email, @RequestParam String password, HttpServletResponse response) {
        if (StringUtils.isBlank(email) || StringUtils.isBlank(password)) {
            return new ResponseData<>(StatusCode.UsernamePasswordNotBlank);
        }
        ResponseData<Object> responseData = new ResponseData<>(StatusCode.Success);
        try {
            String accessToken = tokenService.loginAndCreateToken(email, password);
            responseService.setKeyValueInHeader(response,"accessToken", accessToken);
            //test in postman
            responseData.setData(accessToken);
        } catch (Exception e) {
            responseData = new ResponseData<>(StatusCode.Fail);
        }
        return responseData;
    }

    //get access to the important resources with token
    @RequestMapping(value = "token/pass", method = RequestMethod.GET)
    public ResponseData<Object> surfWithToken() {
        ResponseData<Object> response = new ResponseData<>(StatusCode.Success);
        try {
            String warningMsg = "valid token and get access to resources";
            response.setData(warningMsg);

        } catch (Exception e) {
            response = new ResponseData<>(StatusCode.Fail.getCode(), e.getMessage());
        }
        return response;
    }
}
