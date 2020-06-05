package com.vaxtomis.valumhomeservice.repository;

import com.vaxtomis.valumhomeservice.entity.Device;
import com.vaxtomis.valumhomeservice.entity.Updevice;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface DeviceRepository {
    //增加一个设备
    int insertDevice(String deviceName,String deviceSign,String deviceInfo,String deviceOwner,String deviceType);
    //增加一个up设备
    int insertUpdevice(int deviceId);
    //查询一个设备（通过Id获取Device）
    Device selectDeviceById(Integer deviceId);
    //查询一个设备（通过Sign获取Device）
    Device selectDeviceBySign(String deviceSign);
    //查询一个设备（通过Name获取Device）
    Device selectDeviceByName(String deviceName);
    //查询所有设备
    List<Device> selectAllDevice();
    //查询家庭内的设备
    List<Updevice> selectAllDeviceInHome(int deviceHome);
    //查询所有个人拥有的设备
    List<Updevice> selectAllDeviceByOwner(String deviceOwner);
    //更改设备状态（Id）
    Integer updateDeviceStateById(int deviceId,int deviceState);
    //更改设备状态（Sign）
    Integer updateDeviceStateBySign(String deviceSign,int deviceState);
    //更改设备信息（Sign）
    Integer updateDeviceInfoBySign(String deviceSign,String deviceInfo);
    //修改设备的亮度（Sign）
    Integer updateDeviceBrightnessById(int deviceId,int brightness);
    //修改设备的家庭号
    Integer updateDeviceHomeIdBySign(int deviceHome,String deviceSign);
    //删除一个设备（Sign）
    Integer deleteDeviceBySign(String deviceSign);
    //批量更新
    Integer updateDeviceInArray(ArrayList<String> array);

}
