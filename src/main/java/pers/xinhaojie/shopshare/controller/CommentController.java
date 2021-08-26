package pers.xinhaojie.shopshare.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.xinhaojie.shopshare.dto.CommentDTO;
import pers.xinhaojie.shopshare.entity.Comment;
import pers.xinhaojie.shopshare.entity.SharedOrder;
import pers.xinhaojie.shopshare.enums.CommentTypeEnum;
import pers.xinhaojie.shopshare.service.CommentService;
import pers.xinhaojie.shopshare.service.OrderService;
import pers.xinhaojie.shopshare.utils.CheckParamUtil;

/**
 * @author xin haojie
 * @create 2021-08-25-17:01
 */
@Controller
//@RequestMapping("auth")
public class CommentController {

    @Autowired
    CommentService commentService;

    @Autowired
    CheckParamUtil checkService;

    @Autowired
    OrderService orderService;

    @ResponseBody
    @RequestMapping("send/comment")
    public Object sendComment(@RequestBody CommentDTO commentDTO){
        Comment comment = new Comment(commentDTO.getParentId(),commentDTO.getType(), commentDTO.getCommenterId(), commentDTO.getContent());
        checkService.checkComment(comment);
        //there are two update/save/delete operation, one failed, the rollback
        //comment save fail, the use transaction rollback, do not execute the below codes
        commentService.save(comment);
        //update the comment count of one order
        if(comment.getType().equals(CommentTypeEnum.ORDER.getType())){
            int orderId = comment.getParentId();
            SharedOrder targetOrder = orderService.getOne(new QueryWrapper<SharedOrder>().eq("id", orderId));
            orderService.update(new UpdateWrapper<SharedOrder>().eq("id",orderId).set("comment_count",targetOrder.getCommentCount()+1));
        }

        return "save the comment";
    }

}
