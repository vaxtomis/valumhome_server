package com.vaxtomis.valumhomeservice.mqtt;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.vaxtomis.valumhomeservice.entity.MqttPushPayload;

import java.util.concurrent.ConcurrentLinkedQueue;
//由Client接收的信息传入此MQ中，由
public class MqttMsgQueue {
    private ConcurrentLinkedQueue<String> payloads;
    private volatile static MqttMsgQueue msgQueue;
    private MqttMsgQueue(){
        payloads = new ConcurrentLinkedQueue<>();
    }
    //使用ConcurrentLinkedQueue，同步出入队
    public static MqttMsgQueue getInstance(){
        if (msgQueue==null){
                msgQueue =new MqttMsgQueue();
        }
        return msgQueue;
    }

    public void pushPayloadString(String payload){
        payloads.add(payload);
    }
    //拉取格式为String
    public String pollPayloadString(){
        return payloads.poll();
    }

    //拉取格式为MqttPushPayload
    public MqttPushPayload pollPayload(){
        if (!payloads.isEmpty()){
            Gson gson = new Gson();
            String payload = payloads.poll();
            if (isJSON(payload)){
                return gson.fromJson(payload,new TypeToken<MqttPushPayload>(){}.getType());
            }
        }
        return null;
    }
    public boolean isEmpty(){
        return payloads.isEmpty();
    }
    //判断是否为Json格式，避免收到错误格式程序中断
    public boolean isJSON(String str){
        try {
            Gson gson = new Gson();
            gson.fromJson(str,Object.class);
            return true;
        }catch (JsonSyntaxException e){
            return false;
        }
    }
}
