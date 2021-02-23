package org.example.spring.aspect;

import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 开启接口耗时计算功能
 * 使用@Import
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(ApiLogTimeAspect.class)
public @interface EnableApiLogTime {
}
