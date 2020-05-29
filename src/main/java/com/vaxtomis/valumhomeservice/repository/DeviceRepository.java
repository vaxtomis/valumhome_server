package com.vaxtomis.valumhomeservice.repository;

import com.vaxtomis.valumhomeservice.entity.Device;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceRepository {
    //增加一个设备
    int insertDevice(String deviceName,String deviceSign,String deviceInfo);
    //查询一个设备（通过Id获取Device）
    Device selectDeviceById(Integer deviceId);
    //查询一个设备（通过Sign获取Device）
    Device selectDeviceBySign(String deviceSign);
    //查询一个设备（通过Name获取Device）
    Device selectDeviceByName(String deviceName);
    //查询所有设备
    List<Device> selectAllDevice();
    //更改设备状态（Id）
    Integer updateDeviceStateById(int deviceId,int deviceState);
    //更改设备状态（Sign）
    Integer updateDeviceStateBySign(String deviceSign,int deviceState);
    //更改设备信息（Sign）
    Integer updateDeviceInfoBySign(String deviceSign,String deviceInfo);

}
