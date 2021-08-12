package pers.xinhaojie.shopshare.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author xin haojie
 * @create 2021-07-05-12:43
 */
@Controller
public class AdminController {
    @GetMapping("/admin")
    public String toAdmin(){
        return "admin";
    }
}
