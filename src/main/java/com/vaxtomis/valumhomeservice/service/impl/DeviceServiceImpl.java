package com.vaxtomis.valumhomeservice.service.impl;

import com.vaxtomis.valumhomeservice.entity.Device;
import com.vaxtomis.valumhomeservice.repository.DeviceRepository;
import com.vaxtomis.valumhomeservice.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceServiceImpl implements DeviceService {
    @Autowired
    private DeviceRepository deviceRepository;

    @Override
    public int addDevice(String deviceName,String deviceSign,String deviceInfo) {
        Device device = deviceRepository.selectDeviceBySign(deviceSign);
        if (device!=null){
            return 1003;
        }else {
            if(deviceRepository.insertDevice(deviceName,deviceSign,deviceInfo)==1){
                return 200;
            }
            return 1004;
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
    public List<Device> getAllDeivces() {
        return deviceRepository.selectAllDevice();
    }

    @Override
    public int updateDeviceStateById(int deviceId, int deviceState) {
        int code = 200;
        Integer result = deviceRepository.updateDeviceStateById(deviceId,deviceState);
        if (result==null){
            code = 1021;
        }
        return code;
    }

    @Override
    public int updateDeviceStateBySign(String deviceSign, int deviceState) {
        int code = 200;
        Integer result = deviceRepository.updateDeviceStateBySign(deviceSign,deviceState);
        if (result==null){
            code = 1021;
        }
        return code;
    }

    @Override
    public int updateDeviceInfoBySign(String deviceSign, String deviceInfo) {
        int code = 200;
        Integer result = deviceRepository.updateDeviceInfoBySign(deviceSign,deviceInfo);
        if (result==null){
            code = 1021;
        }
        return code;
    }

    @Override
    public boolean isDeviceExist(String deviceSign) {
        Device device = deviceRepository.selectDeviceBySign(deviceSign);
        return device != null;
    }
}
