package org.example.spring.service;

import org.example.spring.config.SpringConfig;
import org.example.spring.model.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class UserServiceTest {
    private ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
    private UserService service = context.getBean(UserService.class);

    @Test
    public void listAll() {
        List<User> users = service.listAll();
        users.forEach(user -> System.out.println("user = " + user));
    }
}