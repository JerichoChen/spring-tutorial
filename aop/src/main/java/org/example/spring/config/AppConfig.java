package org.example.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 使用纯注解的时候,  用此类代替配置文件.
 * 环境初始化:
 * ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
 */
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ComponentScan({
        "org.example.spring.service",
        "org.example.spring.aspect",
        "org.example.spring.aware"})
public class AppConfig {
}
