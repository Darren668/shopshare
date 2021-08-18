package pers.xinhaojie.shopshare.interceptor;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.HandlerInterceptor;
import pers.xinhaojie.shopshare.entity.User;
import pers.xinhaojie.shopshare.enums.StatusCode;
import pers.xinhaojie.shopshare.response.ResponseData;
import pers.xinhaojie.shopshare.service.ResponseService;
import pers.xinhaojie.shopshare.service.UserService;
import pers.xinhaojie.shopshare.service.serviceImpl.CheckServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author xin haojie
 * @create 2021-08-14-20:56
 */

@Slf4j
public class RegisterInterceptor implements HandlerInterceptor {
    @Autowired
    ResponseService responseService;

    @Autowired
    UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        session.setAttribute("email", email);
        session.setAttribute("username", username);
        if (StringUtils.isBlank(email) || StringUtils.isBlank(password) || StringUtils.isBlank(username)) {
            log.info("invalid register information");
            //ResponseData<Object> responseData = new ResponseData<>(StatusCode.RegisterInformationNotBlank);
            //responseService.response(response, responseData);
            request.setAttribute("msg", StatusCode.RegisterInformationNotBlank.getMsg());
            return false;
        }
        //check if there is the same email
        User user = userService.getOne(new QueryWrapper<User>().eq("email", email));
        if(user != null){
            request.setAttribute("msg", StatusCode.EmailExisted);
            return false;
        }
        return true;
    }
}
