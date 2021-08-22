package pers.xinhaojie.shopshare.enums;

/**
 * Unified response status code
 * @author xin haojie
 * @create 2021-08-12-20:51
 */
public enum StatusCode {

    Success(200,"Success"),
    Fail(404,"Failure"),
    InvalidParams(400,"Invalid parameters"),

    RegisterInformationNotBlank(4001,"Username/password/email required"),
    UsernamePasswordNotBlank(4002,"Username and password required"),
    AccessTokenNotBlank(4003,"AccessToken required"),
    TitleNotBlank(4004, "Title required"),
    DescriptionNotBlank(4005, "Description required"),
    DeadlineNotBlank(4006, "Deadline required"),


    EmailNotExisted(4049, "Email does not existed"),
    EmailExisted(4050, "Email has been used"),
    WrongPassword(4051, "Wrong password"),
    PasswordNotSame(4052, "Two passwords are different"),

    TokenValidateExpireToken(4100,"Token expired"),
    TokenValidateCheckFail(4101,"Token check not pass"),
    AccessTokenNotExist(4200,"Token did not exist, please login again"),
    AccessTokenInvalidate(42001,"Invalid Token"),


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
