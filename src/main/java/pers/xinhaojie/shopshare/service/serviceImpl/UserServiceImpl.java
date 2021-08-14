package pers.xinhaojie.shopshare.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import pers.xinhaojie.shopshare.entity.User;
import pers.xinhaojie.shopshare.mapper.UserMapper;
import pers.xinhaojie.shopshare.service.UserService;

/**
 * @author xin haojie
 * @create 2021-07-13-21:41
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
