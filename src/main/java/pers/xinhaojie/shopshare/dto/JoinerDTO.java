package pers.xinhaojie.shopshare.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xin haojie
 * @create 2021-08-30-9:23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JoinerDTO {
    private Integer userId;
    private String username;
    private String email;
}
