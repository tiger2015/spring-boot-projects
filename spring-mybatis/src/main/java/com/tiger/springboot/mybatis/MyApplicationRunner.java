package com.tiger.springboot.mybatis;

import com.tiger.springboot.mybatis.dao.StudentMapper;
import com.tiger.springboot.mybatis.model.po.StudentPo;
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
//@Component
public class MyApplicationRunner implements ApplicationRunner, ApplicationContextAware {
    private static ApplicationContext applicationContext;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("application runner run");
        // session级别的缓存
        SqlSession sqlSession1 = applicationContext.getBean(SqlSession.class);
        StudentMapper studentDao1 = sqlSession1.getMapper(StudentMapper.class);
        select(studentDao1);
       // sqlSession1.commit();


        // 更新数据
        SqlSession sqlSession2 = applicationContext.getBean(SqlSession.class);
        StudentMapper studentDao2 = sqlSession2.getMapper(StudentMapper.class);
        sqlSession2.commit();

        select(studentDao1);


    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        MyApplicationRunner.applicationContext = applicationContext;
        log.info("init application context");
    }

    private void select(StudentMapper studentDao) {
        StudentPo student = studentDao.selectById(4);
        log.info("======select:{}", student);
    }
}
