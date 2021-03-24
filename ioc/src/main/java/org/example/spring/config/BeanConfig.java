package org.example.spring.config;

import org.example.spring.model.Student;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;

import java.util.Random;

//@Profile本质上还是@Conditional, 由@ActiveFiles启动
@Profile("dev")
@Configuration
public class BeanConfig {

    @Bean
    @Scope(scopeName = "singleton")
    public Student stuBean() {
        Student student = new Student();
        student.setId(new Random().nextInt(10000));
        return student;
    }

}
