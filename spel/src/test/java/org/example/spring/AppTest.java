package org.example.spring;

import org.example.spring.config.SpringConfig;
import org.example.spring.model.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;

public class AppTest {
    private ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
    private SpelExpressionParser parser = new SpelExpressionParser();

    /**
     * 'Hello world'.concat('!!!')
     * new String('hello world').toUpperCase()
     * 1 + 1
     * 1 > 0
     */
    @Test
    public void test1() {
        String expressionString = "new String('hello world').toUpperCase()";
        String value = parser.parseExpression(expressionString).getValue(String.class);
        System.out.println(expressionString + " = " + value);
    }

    /**
     * rootObject
     */
    @Test
    public void test2() {
        User rootObject = User.builder().userId(10086).build();

        String expressionString = "userId - 76";
        Expression expression = parser.parseExpression(expressionString);
        Integer value = expression.getValue(rootObject, Integer.class);
        System.out.println(expressionString + " = " + value);
    }


}
