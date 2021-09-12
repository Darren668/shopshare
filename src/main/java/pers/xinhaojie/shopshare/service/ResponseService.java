package pers.xinhaojie.shopshare.service;

import javax.servlet.http.HttpServletResponse;

/**
 * @author xin haojie
 * @create 2021-08-12-22:22
 */
public interface ResponseService {

    /**define the status and message of success response*/
    public void response(HttpServletResponse response, Object message);

    /**set token in header*/
    public void setKeyValueInHeader(HttpServletResponse response, String key, String value);



}
