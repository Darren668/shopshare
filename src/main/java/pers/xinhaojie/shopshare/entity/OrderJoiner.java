package pers.xinhaojie.shopshare.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xin haojie
 * @create 2021-08-29-23:22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderJoiner {
    @TableId(value = "id", type = IdType.AUTO)
    private int id;
    private int orderId;
    private int joinerId;
}
