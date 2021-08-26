package pers.xinhaojie.shopshare.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pers.xinhaojie.shopshare.entity.Comment;
import pers.xinhaojie.shopshare.entity.User;

/**
 * @author xin haojie
 * @create 2021-08-26-22:25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentUserDTO {

    private  Comment comment;
    private User user;
}
