package org.example.spring;

import org.example.spring.config.JdbcConfig;
import org.example.spring.config.SpringConfig;
import org.example.spring.config.User;
import org.example.spring.model.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {SpringConfig.class})
public class AppTest {
    @Autowired
    private ApplicationContext context;

    @Autowired
    private User user;

    @Autowired
    private JdbcConfig jdbcConfig;

    @Test
    public void test() {
        String[] names = context.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println(name);
        }
    }

    @Test
    public void testUser() {
        System.out.println("user = " + user);
    }

    @Test
    public void testJdbcConfig() {
        System.out.println("JdbcConfig = " + jdbcConfig);
    }

    @Autowired
    private Student stu;

    @Test
    public void testStudent() {
        System.out.println("stu = " + stu);
    }
}
