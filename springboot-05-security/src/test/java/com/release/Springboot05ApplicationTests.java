package com.release;

import com.release.mapper.MenuMapper;
import com.release.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
@Slf4j
class Springboot05ApplicationTests {

    @Resource
    private IUserService studentService;

    @Resource
    private BCryptPasswordEncoder bcrypt;

    @Resource
    private MenuMapper menuMapper;

    @Test
    void contextLoads() {
//        StudentVO studentInfo = studentService.getStudentInfo();
//        log.info("contextLoads: {}", studentInfo);
//
//        String encode = bcrypt.encode("123");
//        String encode1 = bcrypt.encode("123");
//        log.info("encode: {}", encode);
//        log.info("encode1: {}", encode1);
//
//        boolean matches = bcrypt.matches("123", "$2a$10$QvUb0uO030w9jQonrbknE.0IWDPYOt3DelkuByBhJvRxQ1OZb3OUK");
//        log.info("matches: {}", matches);

//        String token = JwtTokenUtil.getToken(123L);
//        log.info("token: {}", token);
//        Long studentId = JwtTokenUtil.parseJWT("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdHVkZW50SWQiOjQwNDIyMjE5OTczODczMjU2NiwiZXhwIjoxNjc1ODIzMDk3LCJpYXQiOjE2NzUyMTgyOTd9.mIpyBSdslRScZOXt5W9B2Z5rQcfR4edsuvoby_VnLj");
//        log.info("token: {}", studentId);

        List<String> list = menuMapper.selectPermsByUserId(404222199738732544L);
        log.info("menu: {}", list);
    }

}
