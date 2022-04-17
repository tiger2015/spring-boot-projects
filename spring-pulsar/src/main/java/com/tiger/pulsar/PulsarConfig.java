package com.tiger.pulsar;

import org.apache.pulsar.client.api.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * @Author Zenghu
 * @Date 2022/3/20
 * @Description
 * @Version: 1.0
 **/
@Configuration
@ConfigurationProperties(prefix = "pulsar")
public class PulsarConfig {

    private String serviceUrl;
    private Duration connectionTimeout;
    private String topic;
    private String tenant;
    private String namespace;

    private String subscriptionName;


    public String getServiceUrl() {
        return serviceUrl;
    }

    public void setServiceUrl(String serviceUrl) {
        this.serviceUrl = serviceUrl;
    }

    public Duration getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(Duration connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }


    public String getTenant() {
        return tenant;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getSubscriptionName() {
        return subscriptionName;
    }

    public void setSubscriptionName(String subscriptionName) {
        this.subscriptionName = subscriptionName;
    }

    @Bean(destroyMethod = "close")
    public PulsarClient pulsarClient() throws PulsarClientException {
        return PulsarClient.builder().serviceUrl(serviceUrl)
                .connectionTimeout((int) connectionTimeout.toMillis(), TimeUnit.MICROSECONDS)
                .build();
    }

    @Bean(destroyMethod = "close")
    public Producer<byte[]> pulsarProducer(PulsarClient client) throws PulsarClientException {
        return client.newProducer().topic(" persistent://"+tenant + "/" + namespace + "/" + topic).create();
    }

    @Bean(destroyMethod = "close", name = "c1")
    public Consumer<byte[]> pulsarConsumer(PulsarClient client) throws PulsarClientException {
        return client.newConsumer()
                .ackTimeout(5, TimeUnit.SECONDS)
                .topic("persistent://" + tenant + "/" + namespace + "/" + topic)
                .subscriptionName(subscriptionName)
                .subscriptionType(SubscriptionType.Exclusive)
                .messageListener(new MyMessageListener<>())
                .subscribe();
    }

    @Bean(destroyMethod = "close", name = "c2")
    public Consumer<byte[]> pulsarConsumer2(PulsarClient client) throws PulsarClientException {
        return client.newConsumer()
                .ackTimeout(5, TimeUnit.SECONDS)
                .topic(topic)
                .subscriptionName(subscriptionName)
                .subscriptionType(SubscriptionType.Exclusive)
                .messageListener(new MyMessageListener<>())
                .subscribe();
    }
}
