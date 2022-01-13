package com.tiger;

import com.google.common.base.Joiner;
import com.tiger.consumer.redis.RedisApplication;
import com.tiger.consumer.redis.entity.Job;
import com.tiger.consumer.redis.entity.User;
import com.tiger.consumer.redis.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.tiger.consumer.redis.util.Object2MapUtil;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {RedisApplication.class})
@Slf4j
public class Test {

    @Autowired
    private UserService userService;

    @org.junit.Test
    public void test() throws IllegalAccessException {
        User user = new User();
        user.setName("test");
        user.setAge(23);
        Job job = new Job();
        job.setName("coder");
        job.setSalary(1000D);
        user.setJob(job);
        user.setFavorites(new HashSet<>(Arrays.asList("film", "ball")));
       // Map<String, Object> stringObjectMap = Object2MapUtil.object2Map(user);
        Map<String, Object> stringObjectMap = Object2MapUtil.objectToMapByJson(user, User.class);
        log.info("result:{}", stringObjectMap);

      //  User other = Object2MapUtil.mapToObjectByJson(stringObjectMap, User.class);

      //  log.info("{}", other.getFavorites());
    }

    @org.junit.Test
   public void insertUser(){
        User user = new User();
        user.setName("test");
        user.setAge(23);
        user.setFavorites(new HashSet<>(Arrays.asList("film", "ball")));
        Job job = new Job();
        job.setName("coder");
        job.setSalary(1000D);
        user.setJob(job);

        userService.addUser(user);
   }

   @org.junit.Test
   public void getUser(){
       User user = userService.getUser("test");
       String join = Joiner.on(",").join(user.getName(), user.getAge(), user.getFavorites());
       log.info(join);


   }

}
