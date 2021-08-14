package pers.xinhaojie.shopshare.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import pers.xinhaojie.shopshare.enums.StatusCode;
import pers.xinhaojie.shopshare.response.ResponseData;
import pers.xinhaojie.shopshare.service.ResponseService;
import pers.xinhaojie.shopshare.service.TokenService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author xin haojie
 * @create 2021-08-14-10:38
 */

@Slf4j
public class LoginTokenInterceptor implements HandlerInterceptor {
    @Autowired
    ResponseService responseService;

    @Autowired
    TokenService tokenService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            //get the token in header
            String accessToken = request.getHeader("accessToken");
            if (StringUtils.isBlank(accessToken)) {
                //token blank, then response the warning message
                log.error("no access token in the request");
                ResponseData<Object> responseData = new ResponseData<>(StatusCode.Fail.getCode(), "you have to login first to get the access token");
                responseService.response(response, responseData);
            } else {
                //resolve and check the token
                log.info("start the validation process of token");
                ResponseData checkResult = tokenService.resolveAndCheckToken(accessToken);
                if (StatusCode.Success.getCode().equals(checkResult.getCode())) {
                    return true;
                } else {
                    responseService.response(response, checkResult);
                    return false;
                }
            }
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
