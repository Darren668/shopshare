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

    //join exception status code
    RepeatJoin(2001,"you have joined once, please don't join repeatly"),
    UserNotInList(2002,"you have not joined in this order, please join first"),

    //comment status code starts with 3000
    OrderNotFound(3001,"your target order has been removed, try another?"),
    TargetCommentBlank(3002,"your target comment id required"),
    TargetCommentNotFound(3003,"you target comment has been removed, please try another"),
    CommentTypeNotValid(3004,"Invalid comment type, it must be 1 or 2"),
    TargetOrderNotFount(3005,"your target order has been removed, please try another"),
    CommenterIdBlank(3006,"commenter id required"),
    CommentContentBlank(3007,"commnet content required"),


    //login status code start with 4000
    RegisterInformationBlank(4001,"Username/password/email required"),
    UsernamePasswordBlank(4002,"Username and password required"),
    AccessTokenBlank(4003,"AccessToken required"),
    TitleBlank(4004, "Title required"),
    DescriptionBlank(4005, "Description required"),
    DeadlineBlank(4006, "Deadline required"),


    EmailNotExisted(4049, "Email does not existed"),
    EmailExisted(4050, "Email has been used"),
    WrongPassword(4051, "Wrong password"),
    PasswordNotSame(4052, "Two passwords are different"),


    TokenValidateExpireToken(4100,"Token expired"),
    TokenValidateCheckFail(4101,"Token check not pass"),
    AccessTokenNotExist(4102,"Token did not exist, please login again"),
    AccessTokenInvalidate(4103,"Invalid Token"),


    LoginFail(4200,"Login failed！"),
    UserHasNoPermission(4201,"The user has no right to get access resources"),
    NoUserLogin(4202,"No user, please login first"),

    //exception code starts with 5000
    ServerNotWork(5001,"the server runs away, please try again"),
    URLRequestNotValid(5002,"your request is invalid, please check it");

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
