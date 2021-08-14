package pers.xinhaojie.shopshare.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.xinhaojie.shopshare.entity.User;
import pers.xinhaojie.shopshare.enums.StatusCode;
import pers.xinhaojie.shopshare.service.UserService;

/**
 * @author xin haojie
 * @create 2021-08-13-22:45
 */
@Service
public class CheckServiceImpl {

    @Autowired
    UserService userService;

    /**check if the username or password is validate*/
    public User checkUser(String email, String password) throws Exception{
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
}
