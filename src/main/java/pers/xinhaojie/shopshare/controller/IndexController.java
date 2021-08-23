package pers.xinhaojie.shopshare.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pers.xinhaojie.shopshare.entity.SharedOrder;
import pers.xinhaojie.shopshare.service.OrderService;
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
    public String toIndexPage(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,  Model model){
        PageHelper.startPage(pageNum, 3);
        QueryWrapper<SharedOrder> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        List<SharedOrder> orderList = orderService.list(wrapper);
        //model.addAttribute("orderList", orderList);
        PageInfo<SharedOrder> pageInfo = new PageInfo<>(orderList);
        model.addAttribute("pageInfo", pageInfo);
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
