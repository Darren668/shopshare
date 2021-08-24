package pers.xinhaojie.shopshare.interceptor;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import pers.xinhaojie.shopshare.entity.User;
import pers.xinhaojie.shopshare.enums.StatusCode;
import pers.xinhaojie.shopshare.response.ResponseData;
import pers.xinhaojie.shopshare.service.ResponseService;
import pers.xinhaojie.shopshare.service.TokenService;
import pers.xinhaojie.shopshare.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author xin haojie
 * @create 2021-08-24-18:19
 */
@Component
public class UserStatusIntercepter implements HandlerInterceptor {
    @Autowired
    ResponseService responseService;

    @Autowired
    TokenService tokenService;

    @Autowired
    UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //get the token in header
        Cookie[] cookies = request.getCookies();
        ResponseData checkResult = null;
        String token = null;
        for (Cookie cookie : cookies) {
            if ("accessToken".equals(cookie.getName())) {
                //get the cookie value and resolve
                token = cookie.getValue();
                checkResult = tokenService.resolveAndCheckToken(token);
                break;
            }
        }
        HttpSession session = request.getSession();
        //resolve token and add the user into session if the token is still valid
        User user = (User) session.getAttribute("user");
        if (checkResult != null && StatusCode.Success.getCode().equals(checkResult.getCode()) && user == null) {
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("id", Integer.valueOf((String) checkResult.getData()));
            user = userService.getOne(queryWrapper);
            session.setAttribute("user", user);
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
