package pers.xinhaojie.shopshare.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pers.xinhaojie.shopshare.entity.SharedOrder;
import pers.xinhaojie.shopshare.entity.User;
import pers.xinhaojie.shopshare.service.OrderService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xin haojie
 * @create 2021-07-05-9:14
 */
@Controller
public class SearchController {

    @Autowired
    OrderService orderService;
    /**
     * the order list by search
     * */
    @RequestMapping ("/search")
    public String searchOrders(@RequestParam String searchTarget, @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum, Model model){
        if(StringUtils.isBlank(searchTarget)){
            return "redirect:/";
        }
        model.addAttribute("searchTarget",searchTarget);
        //remove the + changed by springmvc
        String[] targets = searchTarget.replaceAll(" +", " ").split(" ");
        PageHelper.startPage(pageNum, 3);
        QueryWrapper<SharedOrder> orderQueryWrapper = new QueryWrapper<>();
        for(String key : targets){
            orderQueryWrapper.like("title", key);
        }
        orderQueryWrapper.orderByDesc("create_time");
        List<SharedOrder> orderList = orderService.list(orderQueryWrapper);
        PageInfo<SharedOrder> pageInfo = new PageInfo<>(orderList);
        model.addAttribute("pageInfo", pageInfo);
        return "search";
    }
}
