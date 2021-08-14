package pers.xinhaojie.shopshare.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import pers.xinhaojie.shopshare.entity.User;

/**
 * @author xin haojie
 * @create 2021-07-05-9:14
 */
@Controller
public class MainController {

    /**
     * the main page
     * */
    @PostMapping("/main")
    public String toMainPage(User user, Model model){
        model.addAttribute("user",user);
        return "main";
    }
}
