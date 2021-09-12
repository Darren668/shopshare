package pers.xinhaojie.shopshare.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pers.xinhaojie.shopshare.entity.OrderJoiner;
import pers.xinhaojie.shopshare.service.OrderJoinerService;
import pers.xinhaojie.shopshare.utils.CheckParamUtil;
import pers.xinhaojie.shopshare.utils.ShowOrderUtil;

/**
 * @author xin haojie
 * @create 2021-08-30-8:49
 */
@Controller
@RequestMapping(value = "auth")
public class JoinController {

    @Autowired
    OrderJoinerService orderJoinerService;

    @Autowired
    CheckParamUtil checkParamUtil;

    @Autowired
    ShowOrderUtil showOrderUtil;

    @RequestMapping(value = "user/join")
    public String userJoin(@RequestParam(value = "orderId") Integer orderId, @RequestParam(value = "joinerId") Integer joinerId, Model model) {
        //check if the user has joined before
        try {
            checkParamUtil.checkJoinParam(orderId, joinerId);
        } catch (Exception e) {
            model.addAttribute("msg", e.getMessage());
            showOrderUtil.showOrder(orderId,model);
            return "order";
        }
        //save the new joiner into join table
        //catch之后model中存放的值找不到,抽离出来展示方法
        orderJoinerService.save(new OrderJoiner(orderId, joinerId));
        //the logic of order show
        showOrderUtil.showOrder(orderId,model);
        //add the warning msg
        return "order";
    }

    @RequestMapping(value = "user/quit")
    public String userQuit(@RequestParam(value = "orderId") Integer orderId, @RequestParam(value = "joinerId") Integer joinerId, Model model) {
        //check if the user has joined before
        try {
            checkParamUtil.checkQuitParam(orderId, joinerId);
        } catch (Exception e) {
            model.addAttribute("msg", e.getMessage());
            showOrderUtil.showOrder(orderId,model);
            return "order";
        }
        //save the new joiner into join table
        //catch之后model中存放的值找不到,抽离出来展示方法
        orderJoinerService.remove(new QueryWrapper<OrderJoiner>().eq("joiner_id",joinerId).eq("order_id",orderId));
        //the logic of order show
        showOrderUtil.showOrder(orderId,model);
        //add the warning msg
        return "order";
    }

}
