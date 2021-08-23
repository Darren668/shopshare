package pers.xinhaojie.shopshare.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pers.xinhaojie.shopshare.entity.User;
import pers.xinhaojie.shopshare.enums.StatusCode;
import pers.xinhaojie.shopshare.service.UserService;

/**
 * @author xin haojie
 * @create 2021-08-13-22:45
 */
@Component
public class CheckParamUtil {

    @Autowired
    UserService userService;

    /**check if the email or password is validate*/
    public User checkLoginInformation(String email, String password) throws Exception{
        if(StringUtils.isBlank(email) || StringUtils.isBlank(password)){
            throw new RuntimeException(StatusCode.UsernamePasswordNotBlank.getMsg());
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", email);
        User user = userService.getOne(queryWrapper);
        if(user == null){
            throw new RuntimeException(StatusCode.EmailNotExisted.getMsg());
        }
        if(!password.equals(user.getPassword())){
            throw new RuntimeException(StatusCode.WrongPassword.getMsg());
        }
        return user;
    }

    /**check if the email has been registered*/
    public void checkRegisterInfo(String username, String email, String password) throws Exception{
        if(StringUtils.isBlank(email) || StringUtils.isBlank(password) ||StringUtils.isBlank(username)){
            throw new RuntimeException(StatusCode.RegisterInformationNotBlank.getMsg());
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", email);
        User user = userService.getOne(queryWrapper);
        if(user != null){
            throw new RuntimeException(StatusCode.EmailExisted.getMsg());
        }
    }

    /**check if the password is the same */
    public void checkNewPassword(String email, String password1, String password2) throws Exception{
        if(StringUtils.isBlank(email) || StringUtils.isBlank(password1) ||StringUtils.isBlank(password2)){
            throw new RuntimeException(StatusCode.RegisterInformationNotBlank.getMsg());
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", email);
        User user = userService.getOne(queryWrapper);
        if(user == null){
            throw new RuntimeException(StatusCode.EmailNotExisted.getMsg());
        }
        if(!password1.equals(password2)){
            throw new RuntimeException(StatusCode.PasswordNotSame.getMsg());
        }
    }

    /**check the param of shared order*/
    public void checkSharedOrder(String title, String description ) throws Exception{
        if(StringUtils.isBlank(title)){
            throw new RuntimeException(StatusCode.TitleNotBlank.getMsg());
        }
        if(StringUtils.isBlank(description)){
            throw new RuntimeException(StatusCode.DescriptionNotBlank.getMsg());
        }

    }


}
