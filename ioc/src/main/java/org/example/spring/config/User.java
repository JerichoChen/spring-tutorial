package org.example.spring.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 用来测试属性占位符值的获取
 * 以及自定义converter解决 ${xx}找不到时默认as-is赋值情况.
 */
@Data
@Component
@Slf4j
public class User {

    @Value("${user.id}")
    private Integer userId;

    @Value("${user.name}")
    private String userName;

    @Value("${user.email}")
    private String email;
}
