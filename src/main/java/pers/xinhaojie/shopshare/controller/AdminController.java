package pers.xinhaojie.shopshare.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pers.xinhaojie.shopshare.entity.SharedOrder;
import pers.xinhaojie.shopshare.entity.User;
import pers.xinhaojie.shopshare.service.OrderService;
import pers.xinhaojie.shopshare.service.UserService;

import java.util.HashMap;
import java.util.List;

/**
 * @author xin haojie
 * @create 2021-09-10-17:15
 */
@Controller
public class AdminController {

    @Autowired
    UserService userService;

    @Autowired
    OrderService orderService;

    @RequestMapping(value = "admin")
    public String adminUser(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,  Model model){
        //store the information in map
        HashMap<String, String> map = new HashMap<>();
        map.put("tableName","User Table");
        map.put("key1","username");
        map.put("key2","email");
        model.addAttribute("map",map);
        PageHelper.startPage(pageNum,5);
        List<User> userList = userService.list();
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        model.addAttribute("pageInfo",pageInfo);
        return "admin";
    }

    @RequestMapping(value = "ban/user")
    public String banUser(@RequestParam(value="userId") Integer userId){
        userService.update(new UpdateWrapper<User>().eq("id",userId).set("is_active",0));
        return "redirect:/admin";
    }


    @RequestMapping(value = "unban/user")
    public String unbanUser(@RequestParam(value="userId") Integer userId){
        userService.update(new UpdateWrapper<User>().eq("id",userId).set("is_active",1));
        return "redirect:/admin";
    }


    @RequestMapping(value = "admin/order")
    public String adminOrder(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,  Model model){
        //store the information in map
        HashMap<String, String> map = new HashMap<>();
        map.put("tableName","Order Table");
        map.put("key1","title");
        map.put("key2","deadline");
        model.addAttribute("map",map);
        PageHelper.startPage(pageNum,5);
        List<SharedOrder> orderList = orderService.list(new QueryWrapper<SharedOrder>().orderByDesc("create_time"));
        PageInfo<SharedOrder> pageInfo = new PageInfo<>(orderList);
        model.addAttribute("pageInfo",pageInfo);
        return "admin";
    }

    @RequestMapping(value = "admin/delete/order")
    public String adminOrder(@RequestParam(value = "orderId") Integer orderId){
        orderService.remove(new QueryWrapper<SharedOrder>().eq("id",orderId));
        return "redirect:/admin/order";
    }
}
