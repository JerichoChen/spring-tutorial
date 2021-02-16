package org.example.spring.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LogAspect {

    @Before(value = "execution(* *..service..*(..))")
    public void logBefore(JoinPoint joinPoint) {
        log.info("开始{{}}, 参数{{}}", joinPoint.getSignature().getName(), joinPoint.getArgs());
    }

    /* @After(value = "execution(* *..service..getStudent*(..)) && args(name, age)", argNames = "joinPoint,name,age")
       public void logAfter(JoinPoint joinPoint, String name, Integer age) {
               log.info("结束{{}}, 参数{{}}", joinPoint.getSignature().getName(), joinPoint.getArgs());
      }
   */
    @After(value = "execution(* *..service..getStudent*(..))")
    public void logAfter(JoinPoint joinPoint) {
        log.info("结束{{}}", joinPoint.getSignature().getName());
    }


}
