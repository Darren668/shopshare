package pers.xinhaojie.shopshare.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.xinhaojie.shopshare.dto.CommentDTO;
import pers.xinhaojie.shopshare.entity.Comment;

/**
 * @author xin haojie
 * @create 2021-08-25-17:01
 */
@Controller
@RequestMapping("auth")
public class CommentController {


    @RequestMapping("send/comment")
    public String sendComment(@RequestBody CommentDTO commentDTO){
        Comment comment = new Comment();
        return null;
    }

}
