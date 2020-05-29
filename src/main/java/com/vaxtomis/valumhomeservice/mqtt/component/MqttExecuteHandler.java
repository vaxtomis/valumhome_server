package com.vaxtomis.valumhomeservice.mqtt.component;


import com.vaxtomis.valumhomeservice.entity.MqttPushPayload;
import com.vaxtomis.valumhomeservice.mqtt.MqttMsgQueue;
import com.vaxtomis.valumhomeservice.mqtt.MqttServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
//由MQ中取出消息分发到线程池
public class MqttExecuteHandler {
    private static final Logger log = LoggerFactory.getLogger(MqttExecuteHandler.class);
    private HandlerThread handlerThread;
    @Autowired
    private MqttAsyncExecutor mqttAsyncExecutor;

    public void startExecutorLoop(){
        log.info("[MqttHandler]-Start Executor Loop.");
        /*while (true){
            if(!MqttMsgQueue.getInstance().isEmpty()){
                MqttPushPayload payload = MqttMsgQueue.getInstance().pollPayload();
                if (payload != null){
                    mqttAsyncExecutor.executeAsync(payload);
                }
            }
        }*/
        handlerThread = new HandlerThread(mqttAsyncExecutor);
        handlerThread.start();
    }

    public void endExecutorLoop(){
        log.info("[MqttHandler]-End Executor Loop.");
        handlerThread.interrupt();
    }

    public void restartExecutorLoop(){
        log.info("[MqttHandler]-Restart Executor Loop.");
        if (handlerThread.isAlive()){
            handlerThread.interrupt();
        }
        handlerThread = new HandlerThread(mqttAsyncExecutor);
        handlerThread.start();
    }

    public void startAliveLoop(){
        log.info("[MqttHandler]-Start Alive Loop.");
        AlvieThread aliveThread = new AlvieThread();
        aliveThread.start();
    }

    public void endAliveLoop(){
        log.info("[MqttHandler]-End Alive Loop.");
        handlerThread.interrupt();
    }


    static class HandlerThread extends Thread{
        MqttAsyncExecutor mqttAsyncExecutor;
        HandlerThread(MqttAsyncExecutor mqttAsyncExecutor){
            this.mqttAsyncExecutor = mqttAsyncExecutor;
        }
        @Override
        public void run() {
            while (true){
                if(!MqttMsgQueue.getInstance().isEmpty()){
                    MqttPushPayload payload = MqttMsgQueue.getInstance().pollPayload();
                    if (payload != null){
                        mqttAsyncExecutor.MqttAsyncExecute(payload);
                    }
                }
            }
        }
    }
    static class AlvieThread extends Thread{
        @Override
        public void run() {
            while (true){
                try {
                    MqttServiceClient.publish("mqtt_heartbeat"
                            ,new MqttPushPayload.Builder()
                                    .setSender("valumhomeservice")
                                    .setReceiver("ALL")
                                    .setTitle("Alive").build());
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
