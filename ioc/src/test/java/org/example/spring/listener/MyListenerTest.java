package org.example.spring.listener;

import lombok.extern.slf4j.Slf4j;
import org.example.spring.aware.SpringUtil;
import org.example.spring.config.SpringConfig;
import org.example.spring.model.StudentEvent;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Slf4j
public class MyListenerTest {
    private AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

    @Test
    public void listener1() throws InterruptedException {
        StudentEvent studentEvent = new StudentEvent();
        SpringUtil.getApplicationContext().publishEvent(studentEvent);
        log.info("------------[TEST END]------------");
        //等待异步任务完成
        Thread.sleep(3000);
        context.stop();
    }
}