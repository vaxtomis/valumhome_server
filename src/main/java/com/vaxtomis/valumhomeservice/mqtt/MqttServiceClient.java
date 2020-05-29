package com.vaxtomis.valumhomeservice.mqtt;

import com.vaxtomis.valumhomeservice.entity.MqttPushPayload;
import com.vaxtomis.valumhomeservice.mqtt.component.MqttConfiguration;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MqttServiceClient {
    private static final Logger log = LoggerFactory.getLogger(MqttServiceClient.class);
    private static MqttClient client;

    public static MqttClient getClient() {
        return client;
    }

    public static void setClient(MqttClient client) {
        MqttServiceClient.client = client;
    }

    private MqttConnectOptions getOption(int outTime, int KeepAlive) {
        MqttConnectOptions option = new MqttConnectOptions();
        //设置是否清空session,false表示服务器会保留客户端的连接记录，true表示每次连接到服务器都以新的身份连接
        option.setCleanSession(false);
        //设置连接的用户名
        //option.setUserName(userName);
        //设置连接的密码
        //option.setPassword(password.toCharArray());
        //设置超时时间 单位为秒
        option.setConnectionTimeout(outTime);
        //设置会话心跳时间 单位为秒 服务器会每隔(1.5*keepTime)秒的时间向客户端发送个消息判断客户端是否在线，但这个方法并没有重连的机制
        option.setKeepAliveInterval(KeepAlive);
        //setWill方法，如果项目中需要知道客户端是否掉线可以调用该方法。设置最终端口的通知消息
        //option.setWill(topic, "close".getBytes(), 2, true);
        option.setMaxInflight(1000);
        return option;
    }
    public void connect(MqttConfiguration mqttConfiguration){
        MqttClient client;
        try {
            client = new MqttClient(mqttConfiguration.getHost(), mqttConfiguration.getClientid(), new MemoryPersistence());
            MqttConnectOptions options = getOption(mqttConfiguration.getTimeout(),mqttConfiguration.getKeepalive());
            MqttServiceClient.setClient(client);
            try {
                client.setCallback(new MqttServiceCallback(this,mqttConfiguration));
                if (!client.isConnected()) {
                    client.connect(options);
                    log.info("[MQTT]-Initial Connect Success.");
                    //订阅主题
                    subscribe(mqttConfiguration.getTopic(),mqttConfiguration.getQos());

                }else {
                    //断开重新连接。
                    client.disconnect();
                    client.connect(options);
                    log.info("[MQTT]-Reconnect Success.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //发布，qos为0
    public static void publish(String topic, MqttPushPayload payload){
        publish(0, false, topic, payload);
    }
    //发布，qos为1
    public static void publish(int qos,boolean retained,String topic,MqttPushPayload payload){
        MqttMessage message = new MqttMessage();
        message.setQos(qos);
        message.setRetained(retained);
        message.setPayload(payload.toString().getBytes());
        MqttTopic mqttTopic = MqttServiceClient.getClient().getTopic(topic);
        if(null == mqttTopic){
            log.error("[MQTT]-Topic Not Exist.");
        }
        MqttDeliveryToken token;
        try {
            token = mqttTopic.publish(message);
            token.waitForCompletion();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    //订阅
    public void subscribe(String[] topic,int[] qos){
        try {
            MqttServiceClient.getClient().unsubscribe(topic);
            MqttServiceClient.getClient().subscribe(topic, qos);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
