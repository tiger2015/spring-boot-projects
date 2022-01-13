package com.tiger.consumer.redis;

import com.tiger.consumer.redis.entity.User;
import com.tiger.consumer.redis.service.UserServiceImpl;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.util.Assert;

@SpringBootApplication
@EnableScheduling
public class RedisApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(RedisApplication.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);

      //  UserServiceImpl userService = RedisSpringContext.getBean(UserServiceImpl.class);

        // User user = userService.getUser("1");

       // Assert.isNull(user, "用户为空");
        // 如果上面抛出异常，则后面不会执行
      //  Assert.isNull(user.getName(), "用户名为空");

        // 由于SpringBoot中优先加载lettuce的配置，当已经注入了LettuceConnectionFactory后不再注入JedisConnectionFactory
        // 如果不想用Lettuce，可以将Lettuce的依赖删除，这样在找不到Lettuce相关依赖后，就可以加载JedisConnectionFactory
        //JedisConnectionFactory bean2 = RedisSpringContext.getBean(JedisConnectionFactory.class);
        //Assert.notNull(bean2, "jedis null");





    }
}
