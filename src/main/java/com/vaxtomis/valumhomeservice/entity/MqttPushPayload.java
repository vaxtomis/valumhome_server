package com.vaxtomis.valumhomeservice.entity;


import com.google.gson.Gson;

public class MqttPushPayload {
    //发布者
    private String sender;
    //接收者
    private String receiver = null;
    //标题
    private String title;
    //内容
    private String content;


    public MqttPushPayload(String sender, String receiver, String title, String content) {
        this.sender = sender;
        this.receiver = receiver;
        this.title = title;
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    //构造类
    public static class Builder{
        private String sender;
        private String receiver;
        private String title;
        private String content;

        public String getSender() {
            return sender;
        }

        public Builder setSender(String sender) {
            this.sender = sender;
            return this;
        }

        public String getReceiver() {
            return receiver;
        }

        public Builder setReceiver(String receiver) {
            this.receiver = receiver;
            return this;
        }

        public String getTitle() {
            return title;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public String getContent() {
            return content;
        }

        public Builder setContent(String content) {
            this.content = content;
            return this;
        }
        public MqttPushPayload build(){
            return new MqttPushPayload(sender,receiver,title,content);
        }
    }
    public static Builder getBuilder(){
        return new Builder();
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
