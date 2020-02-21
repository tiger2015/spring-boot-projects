package com.tiger.rabbitmq.dao;

import com.tiger.rabbitmq.entity.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class GeoHashDao {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Value("${geo.hashkey}")
    private String geoKey;

    public long addPosition(Position position) {
        return redisTemplate.opsForGeo().add(geoKey, new Point(position.getLongitude(), position.getLatitude()), position.getName());
    }

    public long addPositions(Position... positions) {
        List<RedisGeoCommands.GeoLocation<String>> geoLocations = new ArrayList<>();
        for (Position position : positions) {
            RedisGeoCommands.GeoLocation<String> geoLocation = new RedisGeoCommands.GeoLocation<>(position.getName(), new Point(position.getLongitude(), position.getLatitude()));
        }
        return redisTemplate.opsForGeo().add(geoKey, geoLocations);
    }

    public List<Point> getPosition(String... names) {
        return redisTemplate.opsForGeo().position(geoKey, names);
    }

}
