package pers.xinhaojie.shopshare;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import pers.xinhaojie.shopshare.entity.Order;
import pers.xinhaojie.shopshare.entity.SharedOrder;
import pers.xinhaojie.shopshare.service.OrderService;

/**
 * @author xin haojie
 * @create 2021-08-22-21:27
 */
@SpringBootTest
public class SharedOrderTest {
    @Autowired
    OrderService orderService;

    @Test
    public void testInsert(){
        //String sql = "insert into shared_order(id, title, description, photo, tags,
        // deadline, user_id, create_time, update_time) value
        // (null, 'waitrose 100-20', 'need students to',
        // 'images/waitrose.jpg', 'supermarket' , '2021-09-01 00:00:00', 1, null, null);";
        SharedOrder sharedOrder = new SharedOrder(null, "waitrose 100-20", "need students", "images/waitrose.jpg", "supermarket", null, 1, null, null);
        SharedOrder sharedOrder2 = new SharedOrder(null, "waitrose 100-20", "need students", "images/waitrose.jpg", "supermarket", "", 1, null, null);
        orderService.save(sharedOrder2);
    }
    
    @Test
    public void testQuery(){
        QueryWrapper<SharedOrder> wrapper = new QueryWrapper<>();
        wrapper.eq("id","1");
        SharedOrder order = orderService.getOne(wrapper);
        System.out.println("==========================================");
        System.out.println(order.getDeadline());
        System.out.println("==========================================");
    }
}
