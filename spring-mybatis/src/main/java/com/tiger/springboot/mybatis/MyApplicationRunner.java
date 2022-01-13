package com.tiger.springboot.mybatis;

import com.tiger.springboot.mybatis.dao.ConsumptionDao;
import com.tiger.springboot.mybatis.dao.StudentDao;
import com.tiger.springboot.mybatis.model.ConsumptionType;
import com.tiger.springboot.mybatis.model.Student;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.BeansException;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Author: ZengHu
 * @Date: 2020/11/29 17:24
 * @Description:
 * @Version: 1.0
 **/
@Slf4j
@Component
public class MyApplicationRunner implements ApplicationRunner, ApplicationContextAware {
    private static ApplicationContext applicationContext;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("application runner run");
        // session级别的缓存
        SqlSession sqlSession1 = applicationContext.getBean(SqlSession.class);
        StudentDao studentDao1 = sqlSession1.getMapper(StudentDao.class);
        select(studentDao1);
       // sqlSession1.commit();


        // 更新数据
        SqlSession sqlSession2 = applicationContext.getBean(SqlSession.class);
        StudentDao studentDao2 = sqlSession2.getMapper(StudentDao.class);
        update(studentDao2);
        sqlSession2.commit();

        select(studentDao1);


    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        MyApplicationRunner.applicationContext = applicationContext;
        log.info("init application context");
    }

    private void select(StudentDao studentDao) {
        Student student = studentDao.selectById(4);
        log.info("======select:{}", student);
    }

    private void add(StudentDao studentDao) {
        log.info("=====insert");
        Student student = new Student();
        student.setName("乔");
        student.setAge((short) 23);
        studentDao.insert(student);
    }

    private void update(StudentDao studentDao) {
        log.info("update");
        Student student = new Student();
        student.setId(4);
        student.setName("凯伦");
        studentDao.update(student);
    }

}
