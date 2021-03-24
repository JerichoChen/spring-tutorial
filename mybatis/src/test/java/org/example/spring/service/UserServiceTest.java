package org.example.spring.service;

import org.example.spring.config.SpringConfig;
import org.example.spring.model.SearchUserDTO;
import org.example.spring.model.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;
import java.util.List;

public class UserServiceTest {
    private ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
    private UserService service = context.getBean(UserService.class);

    @Test
    public void listAll() {
        List<User> users = service.listAll();
        users.forEach(user -> System.out.println("user = " + user));
    }

    @Test
    public void findByNameOrAge() {
        SearchUserDTO dto = new SearchUserDTO();
        dto.setUserName("Jack");
        dto.setUserAge(18);
        List<User> users = service.findByNameOrAge(dto);
        users.forEach(user -> System.out.println("user = " + user));
    }

    @Test
    public void findByIds() {
        List<Integer> ids = Arrays.asList(1, 2, 5);
        List<User> users = service.findByIds(ids);
        users.forEach(user -> System.out.println("user = " + user));
    }

    @Test
    public void findByIdsOrNames() {
        List<Integer> ids = Arrays.asList(5);
        List<String> names = Arrays.asList("Jone", "Jack");
        List<User> users = service.findByIdsOrNames(ids, names);
        users.forEach(user -> System.out.println("user = " + user));
    }

    @Test
    public void findByIdsOtherwiseNames() {
        List<Integer> ids = Arrays.asList();
        List<String> names = Arrays.asList("Jone", "Jack");
        List<User> users = service.findByIdsOtherwiseNames(ids, names);
        users.forEach(user -> System.out.println("user = " + user));
    }
}