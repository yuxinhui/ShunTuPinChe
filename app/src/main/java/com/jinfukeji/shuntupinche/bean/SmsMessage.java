package com.jinfukeji.shuntupinche.bean;

/**
 * Created by "于志渊"
 * 时间:"11:24"
 * 包名:com.jinfukeji.shuntupinche.bean
 * 描述:验证码实例
 */

public class SmsMessage {

    /**
     * message : 短信已发送到您的手机，请注意查收！
     * status : ok
     * code : 556187
     */

    private String message;
    private String status;
    private String code;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "SmsMessage{" +
                "message='" + message + '\'' +
                ", status='" + status + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
