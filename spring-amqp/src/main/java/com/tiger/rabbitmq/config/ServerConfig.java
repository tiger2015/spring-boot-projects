package com.tiger.rabbitmq.config;

import com.tiger.rabbitmq.entity.RedisNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@ConfigurationProperties(prefix = "redis")
public class ServerConfig {
    private List<RedisNode> nodes;

    public List<RedisNode> getNodes() {
        return nodes;
    }

    public void setNodes(List<RedisNode> nodes) {
        this.nodes = nodes;
    }
}
