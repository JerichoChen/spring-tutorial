package org.example.spring.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.example.spring.model.Student;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class MyAspect {

    @Before(value = "execution(* *..service..*(..))")
    public void logBefore(JoinPoint joinPoint) {
        log.info("开始{{}}, 参数{{}}", joinPoint.getSignature().getName(), joinPoint.getArgs());
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
