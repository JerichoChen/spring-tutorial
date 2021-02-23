package org.example.spring.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Slf4j
@Aspect// 声明这是一个切面类
public class ApiLogTimeAspect {

    /**
     * "execution(public * *(..))" 任意public方法
     * "@within(logTime)"     在{@link LogTime}注解的类中的所有方法
     * "@annotation(logTime)" 在{@link LogTime}注解的方法中.
     * "@args(logTime,..)" 方法第一个参数带有{@link LogTime}注解的方法中.
     * "args(name, age)" 有两个参数的方法. (类型由通知方法的参数类型决定)
     *
     * @param pjp     ProceedingJoinPoint
     * @param logTime {@link LogTime}注解
     * @return 代理方法的返回值
     * @throws Throwable 被增强的方法执行中可能出现的异常.  或者本通知方法统一处理之后抛出的异常.
     */
    @Around("execution(public * *(..)) && @annotation(logTime)")
    public Object logAround(ProceedingJoinPoint pjp, LogTime logTime) throws Throwable {
        //前置方法
        log.info("====[START]-[{}]====", System.currentTimeMillis());
        //被增加的方法本身
        Object retVal;
        try {
            retVal = pjp.proceed(pjp.getArgs());
        } finally {
            //后置方法
            log.info("====[ END ]-[{}]====", System.currentTimeMillis());
        }
        return retVal;
    }

}
