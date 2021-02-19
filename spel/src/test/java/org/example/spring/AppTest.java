package org.example.spring;

import lombok.extern.slf4j.Slf4j;
import org.example.spring.aware.SpringUtil;
import org.example.spring.config.SpringConfig;
import org.example.spring.model.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.SpelParserConfiguration;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.List;
import java.util.Map;

@Slf4j
public class AppTest {
    private ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
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

    @Test
    public void test4() {
        String expressionString = "T(java.lang.Math).random() * 100.0";
        Double value = parser.parseExpression(expressionString).getValue(Double.class);
        System.out.println(expressionString + " = " + value);
    }

    @Test
    public void test5() {
        String expressionString = "'null'";
        Object value = parser.parseExpression(expressionString).getValue();
        System.out.println("value==null : " + (value == null));
        System.out.println(expressionString + " = " + value);
    }

    @Test
    public void test6() {
        List<Double> list = (List<Double>) parser.parseExpression("{1,2,3,4}").getValue();
        List<List<String>> listOfLists = (List<List<String>>) parser.parseExpression("{{'a','b'},{'x','y'}}").getValue();

        System.out.println("list = " + list);
        System.out.println("listOfLists = " + listOfLists);


        Map inventorInfo = (Map) parser.parseExpression("{name:'Nikola',dob:'10-July-1856'}").getValue();
        Map mapOfMaps = (Map) parser.parseExpression("{name:{first:'Nikola',last:'Tesla'},dob:{day:10,month:'July',year:1856}}").getValue();

        System.out.println("inventorInfo = " + inventorInfo);
        System.out.println("mapOfMaps = " + mapOfMaps);
    }

    @Test
    public void test7() {
        String expressionString = " #aaa + #bbb * #ccc + (#ddd?:5)";
//        SimpleEvaluationContext context = SimpleEvaluationContext.forReadWriteDataBinding().build();
        StandardEvaluationContext context = new StandardEvaluationContext();
        context.setVariable("aaa", 1);
        context.setVariable("bbb", 2);
        context.setVariable("ccc", 3);

        Object value = parser.parseExpression(expressionString).getValue(context);
        System.out.println(expressionString + " = " + value);
    }

    @Test
    public void test8() {
        StandardEvaluationContext context = new StandardEvaluationContext();
        BeanFactoryResolver resolver = new BeanFactoryResolver(applicationContext);
        context.setBeanResolver(resolver);

        Object value1 = parser.parseExpression("@springUtil").getValue(context);
        System.out.println("value1 = " + value1);
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


    @Test
    public void test3() {
        class Demo {
            public List<SpringUtil> list;
        }
        /* Turn on:
            - auto null reference initialization
            - auto collection growing
            要求集合中的元素要有无参构造.
        */
        SpelParserConfiguration config = new SpelParserConfiguration(true, true);
        ExpressionParser parser1 = new SpelExpressionParser(config);
        Expression expression = parser1.parseExpression("list[3]");
        Demo demo = new Demo();

        log.info("demo.list = {}", demo.list);

        Object o = expression.getValue(demo);
        log.info("o = " + o + "|||");

        log.info("demo.list.size() = {}", demo.list.size());

    }

}
