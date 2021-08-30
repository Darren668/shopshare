package pers.xinhaojie.shopshare.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sun.org.apache.xpath.internal.operations.Or;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.sql.Timestamp;

/**
 * @author xin haojie
 * @create 2021-08-29-23:22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "joiner")
public class OrderJoiner {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private Integer orderId;
    private Integer joinerId;
    private Timestamp createTime;
    private Timestamp updateTime;

    public OrderJoiner(Integer orderId, Integer joinerId){
        this.orderId = orderId;
        this.joinerId = joinerId;
    }
}
