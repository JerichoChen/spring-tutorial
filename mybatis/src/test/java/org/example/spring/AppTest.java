package org.example.spring;

import org.example.spring.config.SpringConfig;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppTest {
    private ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

    @Test
    public void test() {
        String[] names = context.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println(name);
        }
    }
}
