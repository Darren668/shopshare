package pers.xinhaojie.shopshare.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pers.xinhaojie.shopshare.enums.StatusCode;
import pers.xinhaojie.shopshare.response.ResponseData;
import pers.xinhaojie.shopshare.service.TokenService;
import pers.xinhaojie.shopshare.service.UserService;

/**
 * @author xin haojie
 * @create 2021-07-05-8:44
 */
@RestController
@RequestMapping(value = "login")
public class LoginController {

    @Autowired
    UserService userService;

    @Autowired
    TokenService tokenService;

    //login with email and password
    @RequestMapping(value = "email/password", method = RequestMethod.POST)
    public ResponseData<Object> login(@RequestParam String email, @RequestParam String password) {
        if (StringUtils.isBlank(email) || StringUtils.isBlank(password)) {
            return new ResponseData<>(StatusCode.UserNamePasswordNotBlank);
        }
        ResponseData<Object> responseData = new ResponseData<>(StatusCode.Success);
        try {
            responseData.setData(tokenService.loginAndCreateToken(email, password));
        } catch (Exception e) {
            responseData = new ResponseData<>(StatusCode.Fail);
        }
        return responseData;
    }

    //get access to the important resources with token
    @RequestMapping(value = "token", method = RequestMethod.GET)
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
