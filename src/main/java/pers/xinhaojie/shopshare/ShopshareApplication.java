package pers.xinhaojie.shopshare;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("pers.xinhaojie.shopshare.mapper")
@SpringBootApplication
public class ShopshareApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopshareApplication.class, args);
    }
}
