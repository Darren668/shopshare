package pers.xinhaojie.shopshare.enums;

/**
 * Unified response status code
 * @author xin haojie
 * @create 2021-08-12-20:51
 */
public enum StatusCode {

    Success(200,"Success"),
    Fail(404,"Failure"),
    InvalidParams(400,"invalid parameters"),

    UserNamePasswordNotBlank(4001,"Username and password required"),
    AccessTokenNotBlank(4002,"AccessToken required"),

    TokenValidateExpireToken(4003,"Token expired"),
    TokenValidateCheckFail(4004,"Token check not pass"),

    AccessTokenNotExist(4005,"Token did not exist, please login again"),
    AccessTokenInvalidate(4006,"Invalid Token"),


    LoginFail(5000,"Login failed！"),
    UserHasNoPermission(5001,"The user has no right to get access resources"),
    NoUserLogin(5002,"No user, please login first");

    private Integer code;
    private String msg;


    StatusCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
