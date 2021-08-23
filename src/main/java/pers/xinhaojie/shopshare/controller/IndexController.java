package pers.xinhaojie.shopshare.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pers.xinhaojie.shopshare.entity.SharedOrder;
import pers.xinhaojie.shopshare.entity.User;
import pers.xinhaojie.shopshare.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author xin haojie
 * @create 2021-08-14-22:53
 */
@Controller
public class IndexController {

    @Autowired
    OrderService orderService;

    @RequestMapping("/")
    public String toIndexPage(Model model){
        List<SharedOrder> orderList = orderService.list();
        model.addAttribute("orderList", orderList);
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
