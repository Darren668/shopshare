package pers.xinhaojie.shopshare;

import jdk.nashorn.internal.runtime.ScriptRuntime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import pers.xinhaojie.shopshare.entity.User;
import pers.xinhaojie.shopshare.service.UserService;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * @author xin haojie
 * @create 2021-07-13-22:13
 */
@SpringBootTest
public class UserTest {

    @Autowired
    JdbcTemplate jdbcTemplate;
    //test insert new user into database

    @Autowired
    UserService userService;

    @Test
    public void insertUser(){
        Timestamp createTime = new Timestamp(System.currentTimeMillis());
        Timestamp updateTime = new Timestamp(System.currentTimeMillis());

        User user = new User(1,"bob","123456",
                "bob@163.com", (byte) 1,createTime,updateTime);
        //List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from user");
        //maps.forEach((e)->{System.out.println(e.toString());});
        //jdbcTemplate.execute("insert into user values(null,'bob','123456','bob@163.com', '1',null,null);");
        jdbcTemplate.execute("update `user` set username = 'bob2' where email = 'bob@163.com';");
    }

    @Test
    public void testUserService(){
        List<User> list = userService.list();
        User user = list.get(0);
        System.out.println(user);
        Timestamp createTime = user.getCreateTime();
        System.out.println(createTime);
    }
}
