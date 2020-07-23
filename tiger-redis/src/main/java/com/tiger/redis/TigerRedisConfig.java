package com.tiger.redis;

import io.lettuce.core.RedisURI;
import io.lettuce.core.cluster.RedisClusterClient;
import io.lettuce.core.cluster.api.StatefulRedisClusterConnection;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.*;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @Auther: Zeng Hu
 * @Date: 2020/7/21 21:39
 * @Description:
 * @Version: 1.0
 **/
@Configuration
@Slf4j
public class TigerRedisConfig {

    @Autowired
    private RedisProperties properties;

    @Bean
    public StringRedisTemplate redisTemplate(RedisConnectionFactory connectionFactory) {
        StringRedisTemplate template = new StringRedisTemplate(connectionFactory);
        return template;
    }


    @Bean
    public RedisConnectionFactory connectionFactory(LettuceClientConfiguration clientConfiguration,
                                                    RedisConfiguration redisConfiguration) {
        RedisConnectionFactory factory = new LettuceConnectionFactory(redisConfiguration, clientConfiguration);
        ((LettuceConnectionFactory) factory).afterPropertiesSet();
        return factory;
    }


    @Bean
    @Profile("standalone")
    public RedisStandaloneConfiguration standaloneConfiguration() {
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        configuration.setHostName(properties.getHost());
        configuration.setDatabase(properties.getDatabase());
        configuration.setPassword(properties.getPassword());
        return configuration;
    }

    @Bean
    @Profile("sentinel")
    public RedisSentinelConfiguration sentinelConfiguration() {
        RedisSentinelConfiguration configuration =
                new RedisSentinelConfiguration(properties.getSentinel().getMaster(),
                        new HashSet<>(properties.getSentinel().getNodes()));
        configuration.setPassword(properties.getPassword());
        configuration.setDatabase(properties.getDatabase());
        return configuration;
    }


    @Bean
    @Profile("cluster")
    public RedisClusterConfiguration clusterConfiguration() {
        RedisClusterConfiguration configuration = new RedisClusterConfiguration(properties.getCluster().getNodes());
        configuration.setPassword(properties.getPassword());
        configuration.setMaxRedirects(properties.getCluster().getMaxRedirects());
        return configuration;
    }

    @Bean
    public LettuceClientConfiguration clientConfiguration() {
        LettucePoolingClientConfiguration configuration = LettucePoolingClientConfiguration.defaultConfiguration();
        configuration.getPoolConfig().setMaxIdle(properties.getLettuce().getPool().getMaxIdle());
        configuration.getPoolConfig().setMaxTotal(properties.getLettuce().getPool().getMaxActive());
        configuration.getPoolConfig().setMinIdle(properties.getLettuce().getPool().getMinIdle());
        configuration.getPoolConfig().setMaxWaitMillis(properties.getLettuce().getPool().getMaxWait().toMillis());
        return configuration;
    }


    @Bean
    @Profile("cluster")
    public RedisClusterClient redisClusterClient() {
        List<String> nodes = properties.getCluster().getNodes();
        List<RedisURI> redisURIS = new ArrayList<>();
        for (String node : nodes) {
            RedisURI.Builder builder = RedisURI.builder();
            String[] split = node.split(":");
            builder.withHost(split[0]);
            builder.withPort(Integer.parseInt(split[1]));
            builder.withPassword(properties.getPassword());
            builder.withDatabase(properties.getDatabase());
            redisURIS.add(builder.build());
        }
        RedisClusterClient redisClusterClient = RedisClusterClient.create(redisURIS);
        return redisClusterClient;
    }

}
