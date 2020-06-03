package com.vaxtomis.valumhomeservice.service;

import com.vaxtomis.valumhomeservice.entity.Device;
import com.vaxtomis.valumhomeservice.entity.Updevice;

import java.util.ArrayList;
import java.util.List;

public interface DeviceService {
    int addDevice(String deviceName,String deviceSign,String deviceInfo,String deviceOwner,String deviceType);

    Device getDeviceById(int deviceId);

    Device getDeviceBySign(String deviceSign);

    Device getDeviceByName(String deviceName);

    List<Device> getAllDevices();

    List<Updevice> getAllDevicesInHome(int homeId);

    List<Updevice> getAllDevicesByOwner(String deviceOwner);

    int updateDeviceStateById(int deviceId,int deviceState);

    int updateDeviceBrightnessBySign(String deviceSign,int brightness);

    int updateDeviceStateBySign(String deviceSign,int deviceState);

    int updateDeviceInfoBySign(String deviceSign,String deviceInfo);

    void updateDeviceInArray(ArrayList<String> array);

    boolean isDeviceExist(String deviceSign);

    int deleteDeviceBySign(String deviceSign);


}
