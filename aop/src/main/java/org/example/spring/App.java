package org.example.spring;

import lombok.extern.slf4j.Slf4j;
import org.example.spring.aware.SpringUtil;
import org.example.spring.model.Student;
import org.example.spring.service.IStuService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 */
@Slf4j
public class App {
    public static void main(String[] args) {
        String APPLICATION_CONFIG = "application-config.xml";
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(APPLICATION_CONFIG);

        IStuService stuService = SpringUtil.getBean("stuService", IStuService.class);
        Student student = stuService.getStudent("张三", 18);
        log.info("student = " + student);
    }
}
