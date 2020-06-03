package com.vaxtomis.valumhomeservice.mqtt;

import com.vaxtomis.valumhomeservice.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
public class MqttHeartBeatMap {

    ConcurrentHashMap<String, Integer> liveMap;
    private volatile static MqttHeartBeatMap mqttHeartBeatMap;
    private MqttHeartBeatMap(){
        liveMap = new ConcurrentHashMap<>();
    }
    public static MqttHeartBeatMap getInstance(){
        if (mqttHeartBeatMap==null){
            mqttHeartBeatMap =new MqttHeartBeatMap();
        }
        return mqttHeartBeatMap;
    }
    public void cutAll(){
        for (Map.Entry<String, Integer> entry : liveMap.entrySet()) {
            liveMap.replace(entry.getKey(), entry.getValue(), entry.getValue() - 1);
        }
        /*while(iterator.hasNext()){
            Map.Entry<String,Integer> entry = iterator.next();
            liveMap.replace(entry.getKey(),entry.getValue(),entry.getValue()-1);
        }*/
    }
    public ArrayList<String> remove(){
        ArrayList<String> array = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : liveMap.entrySet()) {
            if (entry.getValue() < 0) {
                array.add(entry.getKey());
                liveMap.remove(entry.getKey());
            }
        }
        /*while(iterator.hasNext()){
            Map.Entry<String,Integer> entry = iterator.next();
            if (entry.getValue()<0){
                array.add(entry.getKey());
                liveMap.remove(entry.getKey());
            }
        }*/
        return array;
    }
    public boolean judge(String device){
        if (liveMap.containsKey(device)){
            int temp = liveMap.get(device);
            liveMap.replace(device,temp,temp+1);
            return false;
        }else{
            liveMap.put(device,1);
            return true;
        }
    }
}
