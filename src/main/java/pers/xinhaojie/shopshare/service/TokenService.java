package pers.xinhaojie.shopshare.service;

import pers.xinhaojie.shopshare.response.ResponseData;

/**
 * @author xin haojie
 * @create 2021-08-13-20:19
 */
public interface TokenService {


    /**check login information and create token with email*/
    public String loginAndCreateToken(String email, String password) throws Exception;

    /**resolve and check token with jwt*/
    public ResponseData resolveAndCheckToken(String token) throws Exception;


}
