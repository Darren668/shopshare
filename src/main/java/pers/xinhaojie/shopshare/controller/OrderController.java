package pers.xinhaojie.shopshare.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.UpdateChainWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pers.xinhaojie.shopshare.dto.SharedOrderDTO;
import pers.xinhaojie.shopshare.entity.SharedOrder;
import pers.xinhaojie.shopshare.entity.User;
import pers.xinhaojie.shopshare.service.OrderService;
import pers.xinhaojie.shopshare.service.UserService;

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

    @Autowired
    UserService userService;
    //initiator manage order

    @RequestMapping(value = "user/orders")
    public String managerOrder(HttpServletRequest request, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, Model model){
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        //Pagination
        PageHelper.startPage(pageNum, 3);
        //find our the orders of initiator
        QueryWrapper<SharedOrder> wrapper = new QueryWrapper<>();
        wrapper.eq("initiator_id", user.getId());
        wrapper.orderByDesc("create_time");
        List<SharedOrder> list = orderService.list(wrapper);
        PageInfo<SharedOrder> pageInfo = new PageInfo<>(list);
        model.addAttribute("pageInfo", pageInfo);
        return "manager";
    }



    //details show of each order

    @RequestMapping(value = "order/{orderId}")
    public String showOrder(@PathVariable(value = "orderId") Integer orderId, Model model){
        //find the order
        QueryWrapper<SharedOrder> orderQueryWrapper = new QueryWrapper<>();
        orderQueryWrapper.eq("id",orderId);
        SharedOrder sharedOrder = orderService.getOne(orderQueryWrapper);
        //update view count in database
        //sharedOrder.setViewCount(sharedOrder.getViewCount()+1);
        UpdateWrapper<SharedOrder> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",orderId);
        updateWrapper.set("view_count", sharedOrder.getViewCount()+1);
        orderService.update(updateWrapper);
        //find the initiator
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("id", sharedOrder.getInitiatorId());
        User initiator = userService.getOne(userQueryWrapper);
        //add the sharedOrderDTO into model
        SharedOrderDTO sharedOrderDTO = new SharedOrderDTO(sharedOrder, initiator);
        model.addAttribute("sharedOrderDTO", sharedOrderDTO);
        return "order";
    }
}
