package pers.xinhaojie.shopshare.enums;

/**
 * define some constant values
 * @author xin haojie
 * @create 2021-08-12-21:57
 */
public class ConstantValue {

    //secret code
    public static final String JWT_SECRET = "8677df7fc3a34e26a61c034d5ec8245d";

    public static final String TOKEN_ISSUER="Darren";

    //token expire time：
    public static final Long ACCESS_TOKEN_EXPIRE=60000L;

    //token authorized key
    public static final String TOKEN_AUTH_KEY="e2bd6cee47e0402db80862a09ff4dfd6";

    //prefix of redis key for token
    public static final String JWT_TOKEN_REDIS_KEY_PREFIX="UserAuth:JWT:Key:";
}
