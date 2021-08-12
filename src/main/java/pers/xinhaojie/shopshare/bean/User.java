package pers.xinhaojie.shopshare.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * @author xin haojie
 * @create 2021-07-05-9:30
 */
@Data
@AllArgsConstructor
@TableName("tbl_user")
public class User {

    private Integer id;
    @NotBlank(message = "username required")
    private String username;
    @NotBlank(message = "password required")
    private String password;
    @NotBlank(message = "email required")
    private String email;

    private Byte isActive = 1;
    private Date createTime;
    private Date updateTime;

}
