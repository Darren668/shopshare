package pers.xinhaojie.shopshare.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pers.xinhaojie.shopshare.entity.Comment;
import pers.xinhaojie.shopshare.entity.SharedOrder;
import pers.xinhaojie.shopshare.entity.User;
import pers.xinhaojie.shopshare.enums.CommentTypeEnum;
import pers.xinhaojie.shopshare.enums.StatusCode;
import pers.xinhaojie.shopshare.exception.CustomizeException;
import pers.xinhaojie.shopshare.service.CommentService;
import pers.xinhaojie.shopshare.service.OrderService;
import pers.xinhaojie.shopshare.service.UserService;

/**
 * @author xin haojie
 * @create 2021-08-13-22:45
 */
@Component
public class CheckParamUtil {

    @Autowired
    UserService userService;

    @Autowired
    CommentService commentService;

    @Autowired
    OrderService orderService;

    /**check if the email or password is validate*/
    public User checkLoginInformation(String email, String password) throws Exception{
        if(StringUtils.isBlank(email) || StringUtils.isBlank(password)){
            throw new RuntimeException(StatusCode.UsernamePasswordBlank.getMsg());
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", email);
        User user = userService.getOne(queryWrapper);
        if(user == null){
            throw new RuntimeException(StatusCode.EmailNotExisted.getMsg());
        }
        if(!password.equals(user.getPassword())){
            throw new RuntimeException(StatusCode.WrongPassword.getMsg());
        }
        return user;
    }

    /**check if the email has been registered*/
    public void checkRegisterInfo(String username, String email, String password) throws Exception{
        if(StringUtils.isBlank(email) || StringUtils.isBlank(password) ||StringUtils.isBlank(username)){
            throw new RuntimeException(StatusCode.RegisterInformationBlank.getMsg());
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", email);
        User user = userService.getOne(queryWrapper);
        if(user != null){
            throw new RuntimeException(StatusCode.EmailExisted.getMsg());
        }
    }

    /**check if the password is the same */
    public void checkNewPassword(String email, String password1, String password2) throws Exception{
        if(StringUtils.isBlank(email) || StringUtils.isBlank(password1) ||StringUtils.isBlank(password2)){
            throw new RuntimeException(StatusCode.RegisterInformationBlank.getMsg());
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", email);
        User user = userService.getOne(queryWrapper);
        if(user == null){
            throw new RuntimeException(StatusCode.EmailNotExisted.getMsg());
        }
        if(!password1.equals(password2)){
            throw new RuntimeException(StatusCode.PasswordNotSame.getMsg());
        }
    }

    /**check the param of shared order*/
    public void checkSharedOrder(String title, String description ) throws Exception{
        if(StringUtils.isBlank(title)){
            throw new RuntimeException(StatusCode.TitleBlank.getMsg());
        }
        if(StringUtils.isBlank(description)){
            throw new RuntimeException(StatusCode.DescriptionBlank.getMsg());
        }
    }

    /**check the param of comment with customized exceptions*/
    public void checkComment(Comment comment) {
        //if the commnet parentId is not valid, throw exception, this would be caught by customized handler
        if(comment.getParentId() == null || comment.getParentId() == 0){
            throw new CustomizeException(StatusCode.TargetCommentBlank);
        }
        if(comment.getType() == null || !CommentTypeEnum.containsType(comment.getType()) ){
            throw new CustomizeException(StatusCode.CommentTypeNotValid);
        }
        //二级回复，所以根据请求的type分类判断，如果评论order那就去查找order，如果是评论其他人评论，那就去查找评论
        if(comment.getType().equals(CommentTypeEnum.ORDER.getType()) ){
            //comment to order
            SharedOrder targetOrder = orderService.getOne(new QueryWrapper<SharedOrder>().eq("id", comment.getParentId()));
            if(targetOrder == null){
                throw new CustomizeException(StatusCode.TargetOrderNotFount);
            }
        }else{
            //comment to comment
            //find the parent comment by id and check
            Comment parentComment = commentService.getOne(new QueryWrapper<Comment>().eq("id", comment.getParentId()));
            if(parentComment == null){
                throw new CustomizeException(StatusCode.TargetCommentNotFound);
            }
        }
        //check the commenterId
        if(comment.getCommenterId() == null){
            throw new CustomizeException(StatusCode.CommenterIdBlank);
        }
        //check the content
        if(StringUtils.isBlank(comment.getContent()) || "".equals(comment.getContent())){
            throw new CustomizeException(StatusCode.CommentContentBlank);
        }
    }

}
