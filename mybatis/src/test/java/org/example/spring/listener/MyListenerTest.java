package org.example.spring.listener;

import lombok.extern.slf4j.Slf4j;
import org.example.spring.aware.SpringUtil;
import org.example.spring.config.SpringConfig;
import org.example.spring.model.StudentEvent;
import org.example.spring.model.User;
import org.example.spring.service.UserService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

@Slf4j
public class MyListenerTest {
    private AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

    private UserService service = context.getBean(UserService.class);

    @Test
    public void listener1() throws InterruptedException {
        List<User> users = service.listAll();
        StudentEvent studentEvent = new StudentEvent();
        users.stream()
                .limit(1)
                .forEach(user -> SpringUtil.getApplicationContext().publishEvent(studentEvent));

        log.info("------------[TEST END]------------");
        //等待异步任务完成
        Thread.sleep(3000);
        context.stop();
    }
}