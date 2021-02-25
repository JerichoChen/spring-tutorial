package org.example.spring.listener;

import lombok.extern.slf4j.Slf4j;
import org.example.spring.config.User;
import org.example.spring.model.StudentEvent;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 实现接口 {@link org.springframework.context.ApplicationListener}
 * 或者通用方法注解 {@link EventListener}
 * 见{@link org.springframework.context.event.SimpleApplicationEventMulticaster}
 * 1. 对于注解的方式{@link EventListener}需要注意:
 * <ul>
 * <li>属性classes可以写一个或多个类, 表示被监听的事件(可以是任意Object或者ApplicationEvent的实例)</li>
 * <li>被注解的方法的参数最多只能有一个. 如果classes属性有多个值, 则方法不能有参数.</li>
 * </ul>
 * <p>
 * 2. 使用{@link Order}对同一事件的多个监听器进行排序. order越小越优先.
 * 3. 监听方法可以有返回值, 返回值会被做为新的事件发布.
 * 4. 可异步执行.
 */
@Slf4j
@Component
public class MyListener {
    /**
     * 可同时监听两种事件类型, 但是无法在方法中获取到监听的具体事件
     */
    @Order(0)
    @EventListener(classes = {User.class})
    public void listener1() {
        log.warn("listener-1监听到[User,SearchUserDTO]事件");
    }

    /**
     * classes属性可以省略, 只保留方法的参数(只能有一个), 代表监听到的事件.
     * 异步@Async("myAsyncTaskExecutor") 指定由哪个executor运行任务
     *
     * @param user 被监听的事件
     */
    @Order(2)
    @Async("myAsyncTaskExecutor")
    @EventListener(classes = {User.class})
    public void listener2(User user) throws InterruptedException {
        log.info(Thread.currentThread().getName());
        TimeUnit.SECONDS.sleep(2);
        log.warn("listener-2监听到User事件: {}", user);
    }

    /**
     * 监听方法可以有返回值, 返回值会被做为新的事件发布.
     * 如果返回的是Array或者Collection, 则里面的每一个元素都会被单独发布.
     * note: 一定要注意避免死循环, 会造成stackOverflow
     *
     * @return User 返回User
     */
    @Order(1)
    @EventListener
    public User listener3(StudentEvent stu) {
        log.warn("listener-3监听到StudentEvent事件: {}", stu);

        User user = new User();
        user.setUserId(1111);
        user.setUserName("测试");
        user.setEmail("abc@spring.io");
        return user;
    }

    @EventListener
    public void contextStopListener(ContextStoppedEvent contextStoppedEvent) {
        log.info("监听到ContextStoppedEvent事件: {}", contextStoppedEvent);
    }
}
