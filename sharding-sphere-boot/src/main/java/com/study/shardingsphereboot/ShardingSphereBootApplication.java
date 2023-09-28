package com.study.shardingsphereboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author luohongxiao
 */
@SpringBootApplication
@MapperScan("com.study.shardingsphereboot.dao")
public class ShardingSphereBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShardingSphereBootApplication.class, args);
    }

}
