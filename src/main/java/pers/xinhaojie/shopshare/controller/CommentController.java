package pers.xinhaojie.shopshare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.xinhaojie.shopshare.dto.CommentDTO;
import pers.xinhaojie.shopshare.entity.Comment;
import pers.xinhaojie.shopshare.service.CommentService;
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
    @ResponseBody
    @RequestMapping("send/comment")
    public Object sendComment(@RequestBody CommentDTO commentDTO){
        Comment comment = new Comment(commentDTO.getParentId(),commentDTO.getType(), commentDTO.getCommenterId(), commentDTO.getContent());
        checkService.checkComment(comment);
        commentService.save(comment);
        return "I got the data";
    }

}
