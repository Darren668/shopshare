package pers.xinhaojie.shopshare.service.serviceImpl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import pers.xinhaojie.shopshare.entity.User;
import pers.xinhaojie.shopshare.enums.ConstantValue;
import pers.xinhaojie.shopshare.enums.StatusCode;
import pers.xinhaojie.shopshare.response.ResponseData;
import pers.xinhaojie.shopshare.service.TokenService;
import pers.xinhaojie.shopshare.utils.JwtAndRedisUtil;

import java.util.concurrent.TimeUnit;

/**
 * @author xin haojie
 * @create 2021-08-13-20:46
 */
@Service
@Slf4j
public class TokenServiceImpl implements TokenService {

    @Autowired
    CheckServiceImpl checkService;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public String loginAndCreateToken(User user){
        if (user != null) {
            //create token
            String token = JwtAndRedisUtil.createTokenByJwt(user.getId().toString(), user.getEmail());
            //store token in redis and set the expire time
            ValueOperations<String, String> redisOperation = stringRedisTemplate.opsForValue();
            redisOperation.set(ConstantValue.JWT_TOKEN_REDIS_KEY_PREFIX + user.getId(), token, ConstantValue.ACCESS_TOKEN_EXPIRE, TimeUnit.SECONDS);
            //return the token
            log.info("login and create the token successfully");
            return token;
        }
        return null;
    }

    @Override
    public ResponseData resolveAndCheckToken(String token) throws Exception{
        try {
            //check the token with jwt
            Claims claims = JwtAndRedisUtil.getTokenClaims(token);
            if (claims == null || StringUtils.isBlank(claims.getId())) {
                return new ResponseData(StatusCode.AccessTokenInvalidate);
            }
            //check if token has expired with redis
            String key = ConstantValue.JWT_TOKEN_REDIS_KEY_PREFIX + claims.getId();
            if (!stringRedisTemplate.hasKey(key)) {
                return new ResponseData(StatusCode.AccessTokenNotExist);
            }
            //check the token
            ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
            String redisToken = valueOperations.get(key);
            if (!token.equals(redisToken)) {
                return new ResponseData(StatusCode.AccessTokenInvalidate);
            }
            //validate successfully
            return new ResponseData(StatusCode.Success,claims.getId());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    @Override
    public void removeTokenByKey(String key) {
        redisTemplate.delete(key);
    }
}
