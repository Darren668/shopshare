package pers.xinhaojie.shopshare.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pers.xinhaojie.shopshare.enums.StatusCode;

/**
 * @author xin haojie
 * @create 2021-08-25-22:02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomizeException extends RuntimeException{
    private Integer code;
    private String msg;

    public CustomizeException(StatusCode statusCode) {
        this.code = statusCode.getCode();
        this.msg = statusCode.getMsg();
    }

}
