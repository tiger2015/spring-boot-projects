package com.tiger.kafka.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @Author Zenghu
 * @Date 2022年11月20日 16:43
 * @Description
 * @Version: 1.0
 **/
@Component
@Slf4j
public class BeanTest implements BeanNameAware, BeanClassLoaderAware, ApplicationContextAware, InitializingBean, DisposableBean {

    private String name;

    public void setName(String name) {
        log.info("setter");
        this.name = name;
    }

    @PostConstruct
    public void init(){
        log.info("custom init");
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        log.info("setBeanClassLoader");
    }

    @Override
    public void setBeanName(String name) {
      log.info("setBeanName:{}", name);
    }

    @Override
    public void destroy() throws Exception {
        log.info("destroy");
    }

    @PreDestroy
    public void customDestroy(){
        log.info("custom destroy");
    }


    @Override
    public void afterPropertiesSet() throws Exception {

        log.info("afterPropertiesSet");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        log.info("setApplicationContext");
    }
}
