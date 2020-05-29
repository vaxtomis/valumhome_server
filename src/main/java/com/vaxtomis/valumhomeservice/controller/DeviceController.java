package com.vaxtomis.valumhomeservice.controller;

import com.vaxtomis.valumhomeservice.entity.Device;
import com.vaxtomis.valumhomeservice.entity.MqttPushPayload;
import com.vaxtomis.valumhomeservice.mqtt.MqttServiceClient;
import com.vaxtomis.valumhomeservice.service.impl.DeviceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/device")
public class DeviceController {
    @Autowired
    private DeviceServiceImpl deviceServiceImpl;
    @RequestMapping(value = "/addDevice")
    public int addDevice(String deviceName,String deviceSign,String deviceInfo){
        return deviceServiceImpl.addDevice(deviceName,deviceSign,deviceInfo);
    }

    @RequestMapping(value = "/getAllDevices")
    @ResponseBody
    public List<Device> getAllDevices(){
        return deviceServiceImpl.getAllDeivces();
    }

    @RequestMapping(value = "/getDeviceById")
    @ResponseBody
    public Device getDeviceById(int deviceId){
        return deviceServiceImpl.getDeviceById(deviceId);
    }

    @RequestMapping(value = "/getDeviceBySign")
    @ResponseBody
    public Device getDeviceBySign(String deviceSign){
        return deviceServiceImpl.getDeviceBySign(deviceSign);
    }

    @RequestMapping(value = "/getDeviceByName")
    @ResponseBody
    public Device getDeviceByName(String deviceName){
        return deviceServiceImpl.getDeviceByName(deviceName);
    }

    @RequestMapping(value = "/updateDeviceStateBySign")
    public int updateDeviceStateBySign(String deviceSign,int deviceState){
        int code = deviceServiceImpl.updateDeviceStateBySign(deviceSign,deviceState);
        if(code==200){
            MqttServiceClient.publish(1,false,"mqtt_managers"
                    ,new MqttPushPayload.Builder()
                            .setSender("valumhomeservice")
                            .setReceiver(deviceSign).setTitle("REQ_STATE")
                            .setContent(String.valueOf(deviceState)).build());
        }
        return code;
    }
}
