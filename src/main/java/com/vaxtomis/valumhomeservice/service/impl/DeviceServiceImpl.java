package com.vaxtomis.valumhomeservice.service.impl;

import com.vaxtomis.valumhomeservice.entity.Device;
import com.vaxtomis.valumhomeservice.entity.Updevice;
import com.vaxtomis.valumhomeservice.repository.DeviceRepository;
import com.vaxtomis.valumhomeservice.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeviceServiceImpl implements DeviceService {
    @Autowired
    private DeviceRepository deviceRepository;

    @Override
    public int addDevice(String deviceName,String deviceSign,String deviceInfo,String deviceOwner,String deviceType) {
        Device device = deviceRepository.selectDeviceBySign(deviceSign);
        if (device!=null){
            return -1003;
        }else {
            if(deviceRepository.insertDevice(deviceName,deviceSign,deviceInfo,deviceOwner,deviceType)==1){
                if (!deviceType.equals("NULL")){
                    device = deviceRepository.selectDeviceBySign(deviceSign);
                    deviceRepository.insertUpdevice(device.getDeviceId());
                }
                return  200;
            }
            return  -1004;
        }
    }

    @Override
    public Device getDeviceById(int deviceId) {
        return deviceRepository.selectDeviceById(deviceId);
    }

    @Override
    public Device getDeviceBySign(String deviceSign) {
        return deviceRepository.selectDeviceBySign(deviceSign);
    }

    @Override
    public Device getDeviceByName(String deviceName) {
        return deviceRepository.selectDeviceByName(deviceName);
    }

    @Override
    public List<Device> getAllDevices() {
        return deviceRepository.selectAllDevice();
    }

    @Override
    public List<Updevice> getAllDevicesInHome(int homeId) {
        return deviceRepository.selectAllDeviceInHome(homeId);
    }

    @Override
    public List<Updevice> getAllDevicesByOwner(String deviceOwner) {
        return deviceRepository.selectAllDeviceByOwner(deviceOwner);
    }

    @Override
    public int updateDeviceStateById(int deviceId, int deviceState) {
        int code = -1021;
        if (deviceRepository.updateDeviceStateById(deviceId,deviceState)>0){
            code = 200;
        }
        return code;
    }

    @Override
    public int updateDeviceStateBySign(String deviceSign, int deviceState) {
        int code = -1021;
        if (deviceRepository.updateDeviceStateBySign(deviceSign,deviceState)>0){
            code = 200;
        }
        return code;
    }

    @Override
    public int updateDeviceInfoBySign(String deviceSign, String deviceInfo) {
        int code = -1021;
        if (deviceRepository.updateDeviceInfoBySign(deviceSign,deviceInfo)>0){
            code = 200;
        }
        return code;
    }

    @Override
    public boolean isDeviceExist(String deviceSign) {
        Device device = deviceRepository.selectDeviceBySign(deviceSign);
        return device != null;
    }

    @Override
    public int deleteDeviceBySign(String deviceSign) {
        int code;
        if(deviceRepository.deleteDeviceBySign(deviceSign)>0){
            code =200;
        }else {
            code = -1022;
        }
        return code;
    }

    @Override
    public int updateDeviceBrightnessBySign(String deviceSign, int brightness) {
        int code;
        Device device = deviceRepository.selectDeviceBySign(deviceSign);
        if (deviceRepository.updateDeviceBrightnessById(device.getDeviceId(),brightness)>0){
            code = 200;
        }else{
            code = -1041;
        }
        return code;
    }

    @Override
    public void updateDeviceInArray(ArrayList<String> array) {
        deviceRepository.updateDeviceInArray(array);
    }
}
