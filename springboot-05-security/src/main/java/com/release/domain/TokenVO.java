package com.release.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yancheng
 * @since 2023/2/1
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenVO {

    /**
     * 用户token
     */
    private String token;
}
