package org.example.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAspectJAutoProxy
@EnableAsync//如果不启动支持异步, 会在调用线程中排队执行.
@ComponentScan({"org.example.spring"})
@Import({MyBatisConfig.class})
public class SpringConfig {

}
