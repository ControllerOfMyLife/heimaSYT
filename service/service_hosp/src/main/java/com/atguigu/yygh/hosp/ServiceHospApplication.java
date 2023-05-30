package com.atguigu.yygh.hosp;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: LiZhanHong
 * @Date: 2023/05/16/9:59
 * @Description:
 */

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(value = "com.atguigu.yygh")
@MapperScan("com.atguigu.yygh.hosp.mapper")
public class ServiceHospApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceHospApplication.class, args);
    }
}
