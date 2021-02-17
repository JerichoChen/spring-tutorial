package org.example.spring;

import lombok.extern.slf4j.Slf4j;
import org.example.spring.aware.SpringUtil;
import org.example.spring.model.Student;
import org.example.spring.service.IStuService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Unit test for simple App.
 */
@Slf4j
public class AppTest {
    private String APPLICATION_CONFIG = "application-config.xml";
    private ApplicationContext context = new ClassPathXmlApplicationContext(APPLICATION_CONFIG);
    private IStuService stuService = SpringUtil.getBean("stuService", IStuService.class);

    {
        log.info("{}", stuService.getClass().getName());
    }

    @Test
    public void testBefore() {
        Student student = stuService.getStudent("张三", 18);
        log.info("student = " + student);
    }

    @Test
    public void testAfterReturning() {
        Student student = stuService.getStudentAfterReturning("张三", 18);
        log.info("student = " + student);
    }

    @Test
    public void testAfterThrowing() {
        Student student = null;
        try {
            student = stuService.getStudentAfterThrowing("张三", 18);
        } catch (Exception e) {
            log.error("Opps..");
        } finally {
            log.info("------");
        }
        log.info("student = " + student);
    }

    @Test
    public void testAround() {
        Student student = stuService.getStudentAround("李四", 20);
        log.info("student = " + student);
    }

}
