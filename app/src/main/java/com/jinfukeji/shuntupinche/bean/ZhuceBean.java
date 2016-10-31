package com.jinfukeji.shuntupinche.bean;

/**
 * Created by "于志渊"
 * 时间:"11:24"
 * 包名:com.jinfukeji.shuntupinche.bean
 * 描述:注册实例
 */

public class ZhuceBean {
    private String message;
    private String status;
    private String data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "SmsMessage{" +
                "message='" + message + '\'' +
                ", status='" + status + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
