package pers.xinhaojie.shopshare.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import pers.xinhaojie.shopshare.dto.CommentUserDTO;
import pers.xinhaojie.shopshare.dto.JoinerDTO;
import pers.xinhaojie.shopshare.dto.SharedOrderDTO;
import pers.xinhaojie.shopshare.entity.OrderJoiner;
import pers.xinhaojie.shopshare.entity.SharedOrder;
import pers.xinhaojie.shopshare.entity.User;
import pers.xinhaojie.shopshare.service.OrderJoinerService;
import pers.xinhaojie.shopshare.service.OrderService;
import pers.xinhaojie.shopshare.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xin haojie
 * @create 2021-08-30-12:20
 */
@Component
public class ShowOrderUtil {

    @Autowired
    UserService userService;

    @Autowired
    OrderService orderService;

    @Autowired
    QueryUtil queryUtil;

    @Autowired
    OrderJoinerService orderJoinerService;

    public void showOrder(Integer orderId, Model model){
        //find the order
        SharedOrder sharedOrder = orderService.getOne(new QueryWrapper<SharedOrder>().eq("id",orderId));
        //find the initiator
        User initiator = userService.getOne(new QueryWrapper<User>().eq("id",sharedOrder.getInitiatorId()));
        //add the sharedOrderDTO into model
        SharedOrderDTO sharedOrderDTO = new SharedOrderDTO(sharedOrder, initiator);
        model.addAttribute("sharedOrderDTO", sharedOrderDTO);
        //add the comment-user-dto
        List<CommentUserDTO> commentUserDtoList = queryUtil.getCommentUserDtoList(orderId);
        model.addAttribute("comments",commentUserDtoList);
        //add the joiners into model
        //find all the joiners by orderId
        List<OrderJoiner> orderJoinerList = orderJoinerService.list(new QueryWrapper<OrderJoiner>().eq("order_id", orderId));
        List<Integer> joinerIdList = orderJoinerList.stream().map(OrderJoiner::getJoinerId).collect(Collectors.toList());
        if(joinerIdList.size() > 0){
            List<User> joinerList = userService.list(new QueryWrapper<User>().in("id", joinerIdList));
            List<JoinerDTO> joinerDTOList = joinerList.stream().map(user -> new JoinerDTO(user.getId(), user.getUsername(), user.getEmail())).collect(Collectors.toList());
            //save it or update the joiners in model
            model.addAttribute("joiners", joinerDTOList);
        }


    }
}
