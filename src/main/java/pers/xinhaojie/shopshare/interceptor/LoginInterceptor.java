package pers.xinhaojie.shopshare.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import pers.xinhaojie.shopshare.entity.User;
import pers.xinhaojie.shopshare.enums.StatusCode;
import pers.xinhaojie.shopshare.response.ResponseData;
import pers.xinhaojie.shopshare.service.ResponseService;
import pers.xinhaojie.shopshare.service.TokenService;
import pers.xinhaojie.shopshare.service.serviceImpl.CheckServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author xin haojie
 * @create 2021-08-14-10:38
 */
@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    ResponseService responseService;

    @Autowired
    TokenService tokenService;

    @Autowired
    CheckServiceImpl checkService;
    //判断逻辑写在controller中，提示信息也可以放在model中，解耦合
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        session.setAttribute("email", email);
        if (StringUtils.isBlank(email) || StringUtils.isBlank(password)) {
            log.info("invalid login information");
            request.setAttribute("msg", StatusCode.UsernamePasswordNotBlank.getMsg());
            return false;
        }
        try {
            User user = checkService.checkLoginInformation(email, password);
        } catch (Exception e) {
            request.setAttribute("msg", e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
