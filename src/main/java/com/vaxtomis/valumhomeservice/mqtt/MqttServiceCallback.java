package com.vaxtomis.valumhomeservice.mqtt;

import com.vaxtomis.valumhomeservice.mqtt.component.MqttConfiguration;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//Mqtt Client 的回调接口
public class MqttServiceCallback implements MqttCallback {
    private static final Logger log = LoggerFactory.getLogger(MqttServiceCallback.class);
    private MqttServiceClient client;
    private MqttConfiguration mqttConfiguration;

    public MqttServiceCallback(MqttServiceClient client, MqttConfiguration mqttConfiguration) {
        this.client = client;
        this.mqttConfiguration = mqttConfiguration;
    }
    @Override
    public void connectionLost(Throwable throwable) {
        if(client != null) {
            while (true) {
                try {
                    log.info("[MQTT]-Client Connection Lost.");
                    Thread.sleep(5000);
                    log.info("[MQTT]-Client Reconnect Start.");
                    MqttServiceClient mqttServiceClient = new MqttServiceClient();
                    mqttServiceClient.connect(mqttConfiguration);
                    if(MqttServiceClient.getClient().isConnected()){
                        log.info("[MQTT]-Client Reconnect Success.");
                    }
                    break;
                } catch (Exception e) {
                    log.error("[MQTT]-Reconnect Failed.");
                }
            }
        }
    }
    //payload结构为sender,receiver,title,content
    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        String sPayload = new String(message.getPayload());
        /*System.out.println("Topic:" + topic);
        System.out.println("Message.Qos:" + message.getQos());
        System.out.println("Message.payload:" + sPayload);*/
        MqttMsgQueue.getInstance().pushPayloadString(sPayload);
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        //System.out.println("DeliveryCompele:" + iMqttDeliveryToken.isComplete());
        //log.info("DeliveryCompele:" + iMqttDeliveryToken.isComplete());
    }
}
