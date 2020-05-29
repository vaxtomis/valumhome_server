package com.vaxtomis.valumhomeservice.entity;

public class Device {
    private Integer deviceId;
    private String deviceName;
    private int deviceState;
    private String deviceSign;
    private String deviceInfo;

    public Device() {
    }

    public Device(Integer deviceId, String deviceName, String deviceInfo) {
        this.deviceId = deviceId;
        this.deviceName = deviceName;
        this.deviceInfo = deviceInfo;
    }

    public Device(Integer deviceId, String deviceName, int deviceState, String deviceSign, String deviceInfo) {
        this.deviceId = deviceId;
        this.deviceName = deviceName;
        this.deviceState = deviceState;
        this.deviceSign = deviceSign;
        this.deviceInfo = deviceInfo;
    }

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public int getDeviceState() {
        return deviceState;
    }

    public void setDeviceState(int deviceState) {
        this.deviceState = deviceState;
    }

    public String getDeviceSign() {
        return deviceSign;
    }

    public void setDeviceSign(String deviceSign) {
        this.deviceSign = deviceSign;
    }

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }
}
