package com.jinfukeji.shuntupinche.bean;

/**
 * Created by "于志渊"
 * 时间:"11:24"
 * 包名:com.jinfukeji.shuntupinche.bean
 * 描述:注册实例
 */

public class ZhuceBean {

    /**
     * message : 您已经注册成功，请完善您的信息！
     * status : ok
     * data : {"id":"89d29c58-76c6-4ec6-b29d-a6e7fb3a6d05","telephone":"18196645936","password":"c8e411d4aa6df86a46e8a109d5cea33d","username":"JHV043436","sex":null,"balance":null}
     */

    private String message;
    private String status;
    /**
     * id : 89d29c58-76c6-4ec6-b29d-a6e7fb3a6d05
     * telephone : 18196645936
     * password : c8e411d4aa6df86a46e8a109d5cea33d
     * username : JHV043436
     * sex : null
     * balance : null
     */

    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String id;
        private String telephone;
        private String password;
        private String username;
        private Object sex;
        private Object balance;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public Object getSex() {
            return sex;
        }

        public void setSex(Object sex) {
            this.sex = sex;
        }

        public Object getBalance() {
            return balance;
        }

        public void setBalance(Object balance) {
            this.balance = balance;
        }
    }
}
