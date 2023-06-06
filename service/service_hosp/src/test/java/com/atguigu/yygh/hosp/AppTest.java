package com.atguigu.yygh.hosp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: LiZhanHong
 * @Date: 2023/06/02/10:21
 * @Description:
 */

@SpringBootTest
public class AppTest {

    @Autowired
    private MongoTemplate mongoTemplate;



}
