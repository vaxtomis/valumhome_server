package com.vaxtomis.valumhomeservice.entity;

public class Updevice extends Device{
    private int updeviceId;
    private String brightness;
    private String temperature;
    private String humidity;
    private String duration;
    private String autoStartTime;
    private String autoEndTime;

    public int getUpdeviceId() {
        return updeviceId;
    }

    public void setUpdeviceId(int updeviceId) {
        this.updeviceId = updeviceId;
    }

    public String getBrightness() {
        return brightness;
    }

    public void setBrightness(String brightness) {
        this.brightness = brightness;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getAutoStartTime() {
        return autoStartTime;
    }

    public void setAutoStartTime(String autoStartTime) {
        this.autoStartTime = autoStartTime;
    }

    public String getAutoEndTime() {
        return autoEndTime;
    }

    public void setAutoEndTime(String autoEndTime) {
        this.autoEndTime = autoEndTime;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }
}
