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
import pers.xinhaojie.shopshare.entity.User;
import pers.xinhaojie.shopshare.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author xin haojie
 * @create 2021-08-23-11:37
 */
@Controller
public class OrderController {

    @Autowired
    OrderService orderService;

    //initiator manage order

    @RequestMapping(value = "user/orders")
    public String managerOrder(HttpServletRequest request, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, Model model){
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        //Pagination
        PageHelper.startPage(pageNum, 3);
        //find our the orders of initiator
        QueryWrapper<SharedOrder> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", user.getId());
        wrapper.orderByDesc("create_time");
        List<SharedOrder> list = orderService.list(wrapper);
        PageInfo<SharedOrder> pageInfo = new PageInfo<>(list);
        model.addAttribute("pageInfo", pageInfo);
        return "manager";
    }



    //details show of each order

    @RequestMapping(value = "order/detail")
    public String showOrder(){

        return "order";
    }
}
