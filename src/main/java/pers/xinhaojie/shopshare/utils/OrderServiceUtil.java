package pers.xinhaojie.shopshare.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import pers.xinhaojie.shopshare.entity.Comment;
import pers.xinhaojie.shopshare.entity.SharedOrder;
import pers.xinhaojie.shopshare.enums.CommentTypeEnum;
import pers.xinhaojie.shopshare.service.CommentService;
import pers.xinhaojie.shopshare.service.OrderService;

/**
 * @author xin haojie
 * @create 2021-08-26-10:35
 */
@Component
public class OrderServiceUtil {
    @Autowired
    OrderService orderService;

    @Autowired
    CommentService commentService;

    @Transactional(rollbackFor = RuntimeException.class)
    public void saveAndAddCommentCount(Comment comment){
        //comment save fail, the use transaction rollback, do not execute the below codes
        commentService.save(comment);
        //update the comment count of one order
        if(comment.getType().equals(CommentTypeEnum.ORDER.getType())){
            int orderId = comment.getParentId();
            SharedOrder targetOrder = orderService.getOne(new QueryWrapper<SharedOrder>().eq("id", orderId));
            orderService.update(new UpdateWrapper<SharedOrder>().eq("id",orderId).set("comment_count",targetOrder.getCommentCount()+1));
        }
    }
}
