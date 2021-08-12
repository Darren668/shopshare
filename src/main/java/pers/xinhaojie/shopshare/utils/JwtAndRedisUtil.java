package pers.xinhaojie.shopshare.utils;

import io.jsonwebtoken.*;
import org.apache.shiro.codec.Base64;
import org.joda.time.DateTime;
import pers.xinhaojie.shopshare.enums.ConstantValue;
import pers.xinhaojie.shopshare.enums.StatusCode;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

/**
 * make use of jwt and Redis(expire api) to create or check token
 *
 * @author xin haojie
 * @create 2021-08-12-21:22
 */
public class JwtAndRedisUtil {
    /**
     * create the secret key
     */
    public static SecretKey createSecretKey() {
        byte[] encodedKey = Base64.decode(ConstantValue.JWT_SECRET);
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }

    /**
     * create the token
     */
    public static String createTokenByJwt(String id, String subject) {
        //define the algorithm for generating the signature
        SignatureAlgorithm algorithm = SignatureAlgorithm.HS256;
        //define the key to generate the signature
        SecretKey key = createSecretKey();

        Date now = DateTime.now().toDate();
        //use api of JWT to build the token
        JwtBuilder builder = Jwts.builder()
                //id
                .setId(id)
                //body
                .setSubject(subject)
                //issuer
                .setIssuer(ConstantValue.TOKEN_ISSUER)
                //time
                .setIssuedAt(now)
                //algorithms  and secret key
                .signWith(algorithm, key);
        return builder.compact();
    }

    //verify token
    public static Claims resolveAndVerifyToken(String accessToken) throws Exception {
        Claims claims = null;
        try {
            //resolve and get the body of jwt token
            claims = parseJWT(accessToken);
        } catch (Exception e) {
            throw new RuntimeException(StatusCode.TokenValidateCheckFail.getMsg());
        }
        //return the body of jwt token in Json
        return claims;
    }

    //resolve
    public static Claims parseJWT(String accessToken) throws Exception {
        SecretKey key = createSecretKey();
        return Jwts.parser().setSigningKey(key).parseClaimsJws(accessToken).getBody();
    }
}
