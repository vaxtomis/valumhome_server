package com.vaxtomis.valumhomeservice.mqtt.component;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
@Component
@ConfigurationProperties(prefix = "mqtt")
//用来获取yml配置中预设的各种信息
public class MqttConfiguration {
    private String host;
    private String username;
    private String clientid;
    private int timeout;
    private int keepalive;
    private String[] topic;
    private int[] qos;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getClientid() {
        return clientid;
    }

    public void setClientid(String clientid) {
        this.clientid = clientid;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int getKeepalive() {
        return keepalive;
    }

    public void setKeepalive(int keepalive) {
        this.keepalive = keepalive;
    }

    public String[] getTopic() {
        return topic;
    }

    public void setTopic(String[] topic) {
        this.topic = topic;
    }

    public int[] getQos() {
        return qos;
    }

    public void setQos(int[] qos) {
        this.qos = qos;
    }
}
