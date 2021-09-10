package pers.xinhaojie.shopshare.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author xin haojie
 * @create 2021-09-10-17:15
 */
@Controller
public class AdminController {

    @RequestMapping(value = "back/admin")
    public String admin(){
        return "admin";
    }
}
