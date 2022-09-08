package com.release.redis.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yancheng
 * @since 2022/9/8
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String username;

    private int age;
}
