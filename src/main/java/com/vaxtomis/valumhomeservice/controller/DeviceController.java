package com.vaxtomis.valumhomeservice.controller;

import com.vaxtomis.valumhomeservice.entity.Device;
import com.vaxtomis.valumhomeservice.entity.MqttPushPayload;
import com.vaxtomis.valumhomeservice.entity.Updevice;
import com.vaxtomis.valumhomeservice.mqtt.MqttServiceClient;
import com.vaxtomis.valumhomeservice.service.impl.DeviceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
    public int addDevice(String deviceName,String deviceSign,String deviceInfo,String deviceOwner,String deviceType){
        return deviceServiceImpl.addDevice(deviceName,deviceSign,deviceInfo,deviceOwner,deviceType);
    }

    @RequestMapping(value = "/getAllDevices")
    @ResponseBody
    public List<Device> getAllDevices(){
        return deviceServiceImpl.getAllDevices();
    }
    @RequestMapping(value = "/getAllDevicesInHome")
    @ResponseBody
    public List<Updevice> getAllDevicesInHome(int homeId){
        return deviceServiceImpl.getAllDevicesInHome(homeId);
    }

    @RequestMapping(value = "/getAllDevicesByOwner")
    @ResponseBody
    public List<Updevice> getAllDevicesByOwner(String deviceOwner){
        return  deviceServiceImpl.getAllDevicesByOwner(deviceOwner);
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

    @RequestMapping(value = "/deleteDeviceBySign")
    public int deleteDeviceBySign(String deviceSign){
        return deviceServiceImpl.deleteDeviceBySign(deviceSign);
    }

    @RequestMapping(value = "/updateDeviceBrightnessBySign")
    public int updateDeviceBrightness(String deviceSign,int brightness){
        int code = deviceServiceImpl.updateDeviceBrightnessBySign(deviceSign,brightness);
        if(code==200){
            MqttServiceClient.publish(1,false,"mqtt_managers"
                    ,new MqttPushPayload.Builder()
                            .setSender("valumhomeservice")
                            .setReceiver(deviceSign).setTitle("REQ_BRIGHTNESS")
                            .setContent(String.valueOf(brightness)).build());
        }
        return code;
    }
}
