package com.vaxtomis.valumhomeservice.service;

import com.vaxtomis.valumhomeservice.entity.Device;

import java.util.List;

public interface DeviceService {
    int addDevice(String deviceName,String deviceSign,String deviceInfo);

    Device getDeviceById(int deviceId);

    Device getDeviceBySign(String deviceSign);

    Device getDeviceByName(String deviceName);

    List<Device> getAllDeivces();

    int updateDeviceStateById(int deviceId,int deviceState);

    int updateDeviceStateBySign(String deviceSign,int deviceState);

    int updateDeviceInfoBySign(String deviceSign,String deviceInfo);

    boolean isDeviceExist(String deviceSign);
}
