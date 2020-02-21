package com.tiger.rabbitmq.entity;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * 调用了BeanPostProcessor方法后，spring容器中所有的bean的初始化都生效
 */
//@Component
public class BeanLife implements BeanNameAware, BeanFactoryAware, ApplicationContextAware, BeanPostProcessor,InitializingBean{


    public BeanLife() {
        System.out.println("======bean的构造方法");
    }


    @Override
    public void setBeanName(String name) {
        System.out.println("=======调用setBeanName方法,bean name:" + name);
    }


    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("=======调用setBeanFactory方法，bean factory");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("======调用setApplicationContext方法");
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("======调用postProcessBeforeInitialization方法，bean name:" + beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("=======调用postProcessAfterInitialization方法，bean name:" + beanName);
        return bean;
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("=======调用afterPropertiesSet方法");
    }


    @PostConstruct
    public void init(){
        System.out.println("======调用自定义的init方法");
    }


    @PreDestroy
    public void destroy(){
        System.out.println("========调用自定义的destroy方法");
    }
}
