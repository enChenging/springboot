package com.release;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author yancheng
 * @since 2023/1/31
 */
@SpringBootApplication
public class Springboot06Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Springboot06Application.class, args);
//        log.info("main: {}", run);
    }

}
