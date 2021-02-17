package org.example.spring.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.example.spring.model.Student;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LogAspect {

    @Before(value = "execution(* *..service..*(..))")
    public void logBefore(JoinPoint joinPoint) {
        log.info("开始{{}}, 参数{{}}", joinPoint.getSignature().getName(), joinPoint.getArgs());
    }

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
    @Around("execution(public * *(..)) && @args(logTime,..)")
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

    /**
     * 1.通过 @AfterReturning的属性returning定义返回值名字,
     * 2.在通知方法签名中使用同名参数获取返回的值,
     * 3.对于引用类型, 可以对值进行修改。
     */
    @AfterReturning(
            value = "execution(* *..service..getStudentAfterReturning(..))",
            returning = "retVal")
    public void logAfterReturn(JoinPoint joinPoint, Student retVal) {
        retVal.setName(retVal.getName() + "**");
        log.info("结束{{}}", joinPoint.getSignature().getName());
    }


    /**
     * "@AfterThrowing" 对于异常退出的情况进行增强,  可以获取到抛出的异常, 但不能消除异常;
     * 可做日志记录或者对异常进行重新包装之后再抛出.
     */
    @AfterThrowing(
            value = "execution(* *..service..getStudentAfterThrowing(..))",
            throwing = "ex")
    public void logAfterThrowing(RuntimeException ex) {
        log.error("异常了~~~~~");
        throw new RuntimeException("自己写的异常", ex);
    }

    @After(value = "execution(* *..service..*(..))")
    public void logAfter() {
        log.info("结束Finally");
    }


}
