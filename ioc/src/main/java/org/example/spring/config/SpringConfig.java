package org.example.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FullyQualifiedAnnotationBeanNameGenerator;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.io.ClassPathResource;

@Configuration
@ComponentScan(basePackages = {"org.example.spring"}
        , nameGenerator = FullyQualifiedAnnotationBeanNameGenerator.class
)
//@PropertySource("classpath:/jdbc.properties")
public class SpringConfig {
    @Bean
    public static PropertySourcesPlaceholderConfigurer configurer() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        configurer.setIgnoreUnresolvablePlaceholders(true);
        configurer.setLocation(new ClassPathResource("jdbc.properties"));
        configurer.setLocalOverride(true); //${user.name} systemProperties已经有了.
        return configurer;
    }

    /**
     * 自定义 converter
     *
     * @return ConversionService
     */
    @Bean
    public static ConversionService conversionService() {
        //DefaultFormattingConversionService.
        DefaultConversionService conversionService = new
                DefaultConversionService();
        //使用Lambda会报错.
        //Unable to determine source type <S> and target type <T> for your Converter
        //does the class parameterize those types?
        conversionService.addConverter(new Converter<String, Integer>() {
            @Override
            public Integer convert(String source) {
                Integer res = null;
                try {
                    res = Integer.valueOf(source);
                } catch (NumberFormatException e) {
//                    e.printStackTrace();
                }
                return res;
            }
        });
        return conversionService;
    }
}
