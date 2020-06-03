package com.vaxtomis.valumhomeservice.mqtt.component;

import com.vaxtomis.valumhomeservice.entity.MqttPushPayload;
import com.vaxtomis.valumhomeservice.mqtt.MqttHeartBeatMap;
import com.vaxtomis.valumhomeservice.mqtt.MqttServiceClient;
import com.vaxtomis.valumhomeservice.service.impl.DeviceServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class MqttAsyncExecutor {
    private static final Logger log = LoggerFactory.getLogger(MqttAsyncExecutor.class);
    @Autowired
    private DeviceServiceImpl deviceService;


    @Async("taskExecutor")
    //处理获得的Payload类
    public void MqttAsyncExecute(MqttPushPayload payload) {
        //log.info("Execute Async Start.");
        try {
            //线程
            MethodSelect(payload);
        }catch (Exception e){
            e.printStackTrace();
        }
        //log.info("Execute Async Finish.");
    }

    //根据发送人、接受者和标题调用对应的运行函数
    private void MethodSelect(MqttPushPayload payload){
        if (payload.getReceiver().equals("valumhomeservice")){
            switch (payload.getTitle()){
                case "State":
                    StateChange(payload.getSender(),payload.getContent());
                    break;
                case "Info":
                    InfoChange(payload.getSender(),payload.getContent());
                    break;
                case "RES_Alive":
                    AliveRegister(payload.getSender(),payload.getContent());
            }
        }
    }


    private void StateChange(String sender,String content){
        if(deviceService.isDeviceExist(sender)){
            int code = deviceService.updateDeviceStateBySign(sender,Integer.parseInt(content));
            MqttServiceClient.publish(1,false,"mqtt_managers",
                    new MqttPushPayload.Builder()
                            .setSender("valumhomeservice")
                            .setReceiver(sender)
                            .setTitle("RES_STATE")
                            .setContent(String.valueOf(code)).build());
        }
    }
    private void InfoChange(String sender,String content){
        if(deviceService.isDeviceExist(sender)){
            int code = deviceService.updateDeviceInfoBySign(sender,content);
            MqttServiceClient.publish(1,false,"mqtt_managers",
                    new MqttPushPayload.Builder()
                            .setSender("valumhomeservice")
                            .setReceiver(sender)
                            .setTitle("RES_INFO")
                            .setContent(String.valueOf(code)).build());
        }

    }

    private void AliveRegister(String sender,String deviceState) {
        if (MqttHeartBeatMap.getInstance().judge(sender)){
            deviceService.updateDeviceStateBySign(sender,Integer.parseInt(deviceState));
        }
    }


}
