package org.example.spring.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.spring.model.Student;
import org.example.spring.service.IStuService;
import org.springframework.stereotype.Service;

@Slf4j
@Service("stuService")
public class StuServiceImpl implements IStuService {
    @Override
    public Student getStudent(String name, Integer age) {
        /*通过容器获取到的是代理对象*/
//        SpringUtil.getBean("stuService", IStuService.class).hello(name);
        /*通过this获取到的是被代理对象本身., 也就不会有增强效果*/
        //this.hello(name);
        return Student.builder()
                .name(name)
                .age(age)
                .build();
    }

    @Override
    public void hello(String name) {
        log.info("HELLO [{}]!", name);
    }
}
