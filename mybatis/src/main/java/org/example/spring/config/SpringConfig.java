package org.example.spring.config;

import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan({"org.example.spring.service"})
@Import({MyBatisConfig.class})
public class SpringConfig {

    /**
     * 解决引入配置文件的问题
     * <context:property-placeholder location="classpath:com/something/jdbc.properties"/>
     * PropertyPlaceholderConfig (过时) ->  PropertySourcesPlaceholderConfigurer
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {

        PropertySourcesPlaceholderConfigurer placeholderConfigurer = new PropertySourcesPlaceholderConfigurer();

        placeholderConfigurer.setLocation(new ClassPathResource("jdbc.properties"));
        return placeholderConfigurer;

    }
}
