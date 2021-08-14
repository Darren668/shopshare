package pers.xinhaojie.shopshare.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pers.xinhaojie.shopshare.enums.StatusCode;

import java.io.Serializable;

/**
 * unified the response data model
 * @author xin haojie
 * @create 2021-08-12-21:07
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseData <T> implements Serializable {
    private Integer code;
    private String msg;
    private T data;

    public ResponseData(StatusCode statusCode, T data) {
        this.code = statusCode.getCode();
        this.msg = statusCode.getMsg();
        this.data = data;
    }

    public ResponseData(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseData(StatusCode statusCode) {
        this.code = statusCode.getCode();
        this.msg = statusCode.getMsg();
    }
}
