package pers.xinhaojie.shopshare.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author xin haojie
 * @create 2021-08-23-11:37
 */
@Controller
public class OrderController {

    @RequestMapping(value = "order/detail")
    public String showOrder(){

        return "order";
    }
}
