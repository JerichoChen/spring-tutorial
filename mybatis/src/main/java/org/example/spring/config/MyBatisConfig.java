package org.example.spring.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

@MapperScan("org.example.spring.dao")
@PropertySource({"classpath:jdbc.properties"})
public class MyBatisConfig {

    @Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.username}")
    private String username;
    @Value("${jdbc.password}")
    private String password;
    @Value("${jdbc.driver}")
    private String driver;


    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        factory.setDataSource(dataSource());
        factory.setTypeAliasesPackage("org.example.spring.model");
        factory.setConfiguration(conf());
        return factory.getObject();
    }

    /**
     * mybatis的全局配置<settings>
     */
    @Bean
    public Configuration conf() {
        Configuration conf = new Configuration();
        conf.setMapUnderscoreToCamelCase(true);
        conf.setCacheEnabled(true);
        conf.setLogPrefix("MY");
        return conf;
    }

    /**
     * 数据源配置 Druid
     */
    @Bean(initMethod = "init", destroyMethod = "close")
    public DruidDataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();

        dataSource.setUrl(url);
        dataSource.setDriverClassName(driver);
        dataSource.setPassword(password);
        dataSource.setUsername(username);
        dataSource.setMaxActive(5);

        return dataSource;
    }

    /**
     * 效果同: @MapperScan("org.example.spring.dao")
     */
    //@Bean
    @SuppressWarnings("unused")
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer scanner = new MapperScannerConfigurer();
        scanner.setBasePackage("org.example.spring.dao");
        return scanner;
    }
}