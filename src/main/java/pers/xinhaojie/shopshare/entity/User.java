package pers.xinhaojie.shopshare.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.sql.Timestamp;


/**
 * @author xin haojie
 * @create 2021-07-05-9:30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    @NotBlank(message = "username required")
    private String username;
    @NotBlank(message = "password required")
    private String password;
    @NotBlank(message = "email required")
    private String email;

    private Byte isActive = 1;
    private Timestamp createTime;
    private Timestamp updateTime;

}
