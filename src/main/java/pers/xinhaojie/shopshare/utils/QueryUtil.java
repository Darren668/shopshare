package pers.xinhaojie.shopshare.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import pers.xinhaojie.shopshare.dto.CommentUserDTO;
import pers.xinhaojie.shopshare.entity.Comment;
import pers.xinhaojie.shopshare.entity.User;
import pers.xinhaojie.shopshare.enums.CommentTypeEnum;
import pers.xinhaojie.shopshare.service.CommentService;
import pers.xinhaojie.shopshare.service.OrderService;
import pers.xinhaojie.shopshare.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author xin haojie
 * @create 2021-08-26-22:36
 */
@Component
public class QueryUtil {
    @Autowired
    CommentService commentService;

    @Autowired
    OrderService orderService;

    @Autowired
    UserService userService;

    public List<CommentUserDTO> getCommentUserDtoList(Integer orderId){
        //find the comments by parentId = orderId
        List<Comment> commentList = commentService.list(new QueryWrapper<Comment>().eq("type", CommentTypeEnum.ORDER.getType()).
                eq("parent_id", orderId));

        if(commentList.size() == 0){
            return new ArrayList<CommentUserDTO>();
        }
        //make use of stream api to remove the same commenter in these comments
        Set<Integer> commenterSet = commentList.stream().map(Comment::getCommenterId).collect(Collectors.toSet());

        //find the users by commenterId and save it as a map
        List<User> userList = userService.list(new QueryWrapper<User>().in("id", commenterSet));
        Map<Integer, User> userMap = userList.stream().collect(Collectors.toMap(User::getId, user -> user));

        //pair the comment and user with the above map
        List<CommentUserDTO> commentUserDTOList = commentList.stream().map(comment -> {
            CommentUserDTO commentUserDTO = new CommentUserDTO(comment, userMap.get(comment.getCommenterId()));
            return commentUserDTO;
        }).collect(Collectors.toList());
        return commentUserDTOList;
    }
}
