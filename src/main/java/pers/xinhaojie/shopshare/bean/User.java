package pers.xinhaojie.shopshare.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xin haojie
 * @create 2021-07-05-9:30
 */
@Data
@AllArgsConstructor
@TableName("tbl_user")
public class User {

    String email;
    String password;
    String name;
    byte[] orderId;
    char gender;

}
