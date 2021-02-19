package org.example.spring.config;

import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAspectJAutoProxy
@EnableAsync//如果不启动支持异步, 会在调用线程中排队执行.
@ComponentScan({"org.example.spring"})
@Import({MyBatisConfig.class})
public class SpringConfig {

    /**
     * 等同于: @PropertySource({"classpath:jdbc.properties"})
     * 解决引入配置文件的问题
     * <context:property-placeholder location="classpath:com/something/jdbc.properties"/>
     * PropertyPlaceholderConfig (过时) ->  PropertySourcesPlaceholderConfigurer
     */
    // @Bean
    @SuppressWarnings("unused")
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {

        PropertySourcesPlaceholderConfigurer placeholderConfigurer = new PropertySourcesPlaceholderConfigurer();

        placeholderConfigurer.setLocation(new ClassPathResource("jdbc.properties"));
        return placeholderConfigurer;

    }

    /**
     * 自定义TaskExecutor, 用于异步任务的执行.
     * 默认情况下, @EnableAsync之后, getDefaultExecutor
     * {@link org.springframework.aop.interceptor.AsyncExecutionInterceptor
     * determineAsyncExecutor/getDefaultExecutor 方法获取Executor(默认:SimpleAsyncTaskExecutor).
     *
     * @return TaskExecutor
     */
    @Bean("myAsyncTaskExecutor")
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(2);
        taskExecutor.setAllowCoreThreadTimeOut(true);
        //默认使用Bean名称-为做为ThreadNamePrefix
        //taskExecutor.setThreadNamePrefix("MyAsyncTaskExecutor-");
        return taskExecutor;
    }
}
