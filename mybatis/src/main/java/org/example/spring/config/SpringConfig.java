package org.example.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan({"org.example.spring.service"})
@Import({MyBatisConfig.class})
public class SpringConfig {

    /**
     * 等同于: @PropertySource({"classpath:jdbc.properties"})
     * 解决引入配置文件的问题
     * <context:property-placeholder location="classpath:com/something/jdbc.properties"/>
     * PropertyPlaceholderConfig (过时) ->  PropertySourcesPlaceholderConfigurer
     */
    // @Bean
    @SuppressWarnings("unused")
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {

        PropertySourcesPlaceholderConfigurer placeholderConfigurer = new PropertySourcesPlaceholderConfigurer();

        placeholderConfigurer.setLocation(new ClassPathResource("jdbc.properties"));
        return placeholderConfigurer;

    }
}
