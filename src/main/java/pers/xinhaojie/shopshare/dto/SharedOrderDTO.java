package pers.xinhaojie.shopshare.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pers.xinhaojie.shopshare.entity.SharedOrder;
import pers.xinhaojie.shopshare.entity.User;

/**
 * @author xin haojie
 * @create 2021-08-24-16:55
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SharedOrderDTO {
    private SharedOrder sharedOrder;
    private User initiator;
}
