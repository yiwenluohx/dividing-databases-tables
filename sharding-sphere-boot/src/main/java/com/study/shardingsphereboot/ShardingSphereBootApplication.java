package com.study.shardingsphereboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author luohongxiao
 * shardingsphere使用注意细节（不支持的语句）
 * select * from coursedb.course //会忽略schema（coursedb）
 * select * from course union all select * from course //后面一个course不会被改写
 * select max(cid) from course group by user_id having user_id='1001' //不支持having
 */
@SpringBootApplication
@MapperScan("com.study.shardingsphereboot.dao")
public class ShardingSphereBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShardingSphereBootApplication.class, args);
    }

}
