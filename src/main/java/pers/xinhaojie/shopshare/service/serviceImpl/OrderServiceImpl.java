package pers.xinhaojie.shopshare.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pers.xinhaojie.shopshare.entity.SharedOrder;
import pers.xinhaojie.shopshare.mapper.OrderMapper;
import pers.xinhaojie.shopshare.service.OrderService;

/**
 * @author xin haojie
 * @create 2021-08-22-21:42
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, SharedOrder> implements OrderService {
}
