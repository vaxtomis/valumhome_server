package com.vaxtomis.valumhomeservice.runner;

import com.vaxtomis.valumhomeservice.mqtt.MqttServiceClient;
import com.vaxtomis.valumhomeservice.mqtt.component.MqttConfiguration;
import com.vaxtomis.valumhomeservice.mqtt.component.MqttExecuteHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class ApplicationRunnerImpl implements ApplicationRunner {
    @Autowired
    private MqttConfiguration mqttConfiguration;

    @Autowired
    private MqttExecuteHandler mqttExecuteHandler;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        MqttServiceClient mqttServiceClient = new MqttServiceClient();
        mqttServiceClient.connect(mqttConfiguration);

        mqttExecuteHandler.startExecutorLoop();
        //mqttExecuteHandler.endExecutorLoop();
        mqttExecuteHandler.startAliveLoop();
        //mqttExecuteHandler.endLoop();
        /*MqttMsgHandler msgHandler = new MqttMsgHandler();
        msgHandler.execute();*/
    }
}
