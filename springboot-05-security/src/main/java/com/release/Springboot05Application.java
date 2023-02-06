package com.release;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author yancheng
 * @since 2023/1/31
 */
@SpringBootApplication
@MapperScan("com.release.mapper")
@Slf4j
public class Springboot05Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Springboot05Application.class, args);
//        log.info("main: {}", run);
    }

}
