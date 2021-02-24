package org.example.spring.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

@Slf4j
@Data
@Component
public class JdbcConfig implements InitializingBean {
    /*
     * 使用@Value时, 对于找不到的表达式,  默认会as-is做为属性的值.
     * 这样对于非String类型的会报类型不匹配.
     * 对于String类型的, 得到不是自己想要的结果.
     * ${} -> 属性占位符
     * #{} -> EL表达式
     * */
    @Value("${jdbc.username}")
    private String userName;

    @Value("${jdbc.password}")
    private Integer password;

    @Value("${jdbc.url}")
    private String url;

    @Value("${jdbc.driver}")
    private String driver;

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("afterPropertiesSet");
    }

    @PreDestroy
    public void beforeDestroy() throws Exception {
        log.info("beforeDestroy");
    }
}
