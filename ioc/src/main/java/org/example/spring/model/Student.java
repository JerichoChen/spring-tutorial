package org.example.spring.model;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
@Slf4j
public class Student {
    /*
     * 使用@Value时, 对于找不到的表达式,  默认会as-is做为属性的值.
     * 这样对于非String类型的会报类型不匹配.
     * 对于String类型的, 得到不是自己想要的结果.
     * ${} -> 属性占位符
     * #{} -> EL表达式
     * */
    @Value("${user.id}")
    private Integer id;

    @Value("${user.name}")
    private String name;

    public void init() throws Exception {
        log.info("[Student] HELLO, THIS IS {}!", name);
    }

    public void initialize() throws Exception {
        log.info("[Student] HELLO, THIS IS {}!", name);
    }

    public void despose() throws Exception {
        log.info("[Student] BYE-BYE FROM {}!", name);
    }
}
