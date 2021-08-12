package pers.xinhaojie.shopshare.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import pers.xinhaojie.shopshare.bean.User;
import pers.xinhaojie.shopshare.mapper.LoginMapper;
import pers.xinhaojie.shopshare.service.LoginService;

/**
 * @author xin haojie
 * @create 2021-07-13-21:41
 */
@Service
public class LoginServiceImpl extends ServiceImpl<LoginMapper, User> implements LoginService {
}
