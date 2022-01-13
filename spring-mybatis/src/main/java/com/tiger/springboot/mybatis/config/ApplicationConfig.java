package com.tiger.springboot.mybatis.config;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: ZengHu
 * @Date: 2020/11/29 13:27
 * @Description:
 * @Version: 1.0
 **/
@Configuration()
public class ApplicationConfig {

    @Value("${mybatis.config}")
    private String mybatisConfig;

    @Bean
    SqlSessionFactory sqlSessionFactory() {
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(mybatisConfig);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            return sqlSessionFactory;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Bean(destroyMethod = "close")
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    SqlSession sqlSession(SqlSessionFactory sqlSessionFactory) {
        // 自动提交事务
        return sqlSessionFactory.openSession(true);
    }
}
