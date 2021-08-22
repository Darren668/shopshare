package pers.xinhaojie.shopshare.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * @author xin haojie
 * @create 2021-08-22-20:53
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "shared_order")
public class SharedOrder {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String title;
    private String description;
    private String photo;
    private String tags;
    private String deadline;
    private Integer userId;
    private Timestamp createTime;
    private Timestamp updateTime;
}
