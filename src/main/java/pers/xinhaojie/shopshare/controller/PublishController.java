package pers.xinhaojie.shopshare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pers.xinhaojie.shopshare.entity.SharedOrder;
import pers.xinhaojie.shopshare.entity.User;
import pers.xinhaojie.shopshare.service.OrderService;
import pers.xinhaojie.shopshare.utils.CheckParamUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author xin haojie
 * @create 2021-08-22-20:34
 */
@Controller
@RequestMapping("auth")
public class PublishController {

    @Autowired
    CheckParamUtil checkParamUtil;

    @Autowired
    OrderService orderService;


    @RequestMapping(value = "publish" )
    public String publishOrder(@RequestParam(value = "title") String title,
                               @RequestParam(value = "description") String description,
                               @RequestParam(value = "photo") String photo,
                               @RequestParam(value = "tags") String tags,
                               @RequestParam(value = "deadline") String deadline,
                               HttpServletRequest request,
                               HttpServletResponse response,
                               Model model){
        //copy the param
        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("photo", photo);
        model.addAttribute("tags", tags);
        model.addAttribute("deadline", deadline);
        //check the param
        try {
            checkParamUtil.checkSharedOrder(title, description, deadline);
        } catch (Exception e) {
            model.addAttribute("msg", e.getMessage());
            return "publish";
        }
        HttpSession session = request.getSession();
        //new sharedorder
        User user = (User)session.getAttribute("user");
        SharedOrder sharedOrder = new SharedOrder(null, title.trim(), description.trim(), photo.trim(), tags.trim(), deadline.trim(), user.getId(), null, null);
        //insert new order into database
        orderService.save(sharedOrder);
        return "redirect:/";
    }
}
