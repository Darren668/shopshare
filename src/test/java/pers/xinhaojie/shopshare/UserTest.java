package pers.xinhaojie.shopshare;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pers.xinhaojie.shopshare.bean.User;
import pers.xinhaojie.shopshare.service.LoginService;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xin haojie
 * @create 2021-07-13-22:13
 */
@SpringBootTest
public class UserTest {
    @Autowired
    LoginService loginService;
    @Test
    public void databaseConnectionTest() throws IOException {
        List<User> list = loginService.list();
        System.out.println(list.toString());

    }
    @Test
    public void insertUserTest(){
        User user = new User("lily@qq.com","123456","lili",null,'1');
        loginService.save(user);

        List<User> newList = loginService.list();
        System.out.println(newList.toString());
    }

    @Test
    public void updateOrderIdColumn() throws IOException {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();

        //transfer list to byte array
        ArrayList<Integer> liliOrders = new ArrayList<>();
        liliOrders.add(1);
        liliOrders.add(2);
        ByteArrayOutputStream byt = new ByteArrayOutputStream();
        ObjectOutputStream obj = new ObjectOutputStream(byt);
        obj.writeObject(liliOrders);
        byte[] orderIdBytes = byt.toByteArray();

        updateWrapper.eq("email","lily@qq.com").set("order_id",orderIdBytes);
        loginService.update(updateWrapper);

        List<User> newList = loginService.list();
        System.out.println(newList.toString());

        byt.close();
        obj.close();
    }

    @Test
    public void queryOrderIdColumn() throws Exception {
        User lily = loginService.getOne(new QueryWrapper<User>().eq("email", "lily@qq.com"));
        byte[] orderId = lily.getOrderId();
        ByteArrayInputStream byt = new ByteArrayInputStream(orderId);
        ObjectInputStream obj = new ObjectInputStream(byt);
        List<Integer> orderList = (List)obj.readObject();
        System.out.println(orderList.toString());

        byt.close();
        obj.close();
    }
}
