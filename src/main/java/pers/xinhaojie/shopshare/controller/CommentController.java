package pers.xinhaojie.shopshare.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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
import pers.xinhaojie.shopshare.utils.OrderServiceUtil;

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

    @Autowired
    OrderServiceUtil orderServiceUtil;

    @ResponseBody
    @RequestMapping("send/comment")
    @Transactional
    public Object sendComment(@RequestBody CommentDTO commentDTO){
        Comment comment = new Comment(commentDTO.getParentId(),
                commentDTO.getType(), commentDTO.getCommenterId(), commentDTO.getContent().trim());
        checkService.checkComment(comment);
        //there are two update/save/delete operation, one failed, then rollback
        orderServiceUtil.saveAndAddCommentCount(comment);
        return "save the comment";
    }

}
