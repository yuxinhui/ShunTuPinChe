package com.jinfukeji.shuntupinche.bean;

/**
 * Created by "于志渊"
 * 时间:"11:24"
 * 包名:com.jinfukeji.shuntupinche.bean
 * 描述:注册实例
 */

public class LoginBean {

    /**
     * message : 登录成功
     * identity : passenger
     * status : ok
     * data : {"id":"82222231-3e67-4142-8471-234a78b60da5","telephone":"18196645936","password":"670b14728ad9902aecba32e22fa4f6bd","username":"JGP726352","sex":"1","balance":"0"}
     */

    private String message;
    private String identity;
    private String status;
    /**
     * id : 82222231-3e67-4142-8471-234a78b60da5
     * telephone : 18196645936
     * password : 670b14728ad9902aecba32e22fa4f6bd
     * username : JGP726352
     * sex : 1
     * balance : 0
     */

    private DataBean data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
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
        private String sex;
        private String balance;

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

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }
    }
}
