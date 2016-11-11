package com.jinfukeji.shuntupinche.bean;

import java.util.List;

/**
 * Created by "于志渊"
 * 时间:"15:22"
 * 包名:com.jinfukeji.shuntupinche.bean
 * 描述:乘客查询实例
 */

public class PassengerChaXunBean {
    /**
     * message : 查询成功
     * status : ok
     * data : [{"id":"033368bc-f0e6-4a4f-8d24-061203b4720a","oid":"bd8525a3-a714-4bcc-be5f-d8e66ce08f87","time":1480435200000,"startPlace":"合肥","destination":"宿州","number":"2","telephone":"18196645936","date":1478756989000}]
     */

    private String message;
    private String status;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 033368bc-f0e6-4a4f-8d24-061203b4720a
         * oid : bd8525a3-a714-4bcc-be5f-d8e66ce08f87
         * time : 1480435200000
         * startPlace : 合肥
         * destination : 宿州
         * number : 2
         * telephone : 18196645936
         * date : 1478756989000
         */

        private String id;
        private String oid;
        private long time;
        private String startPlace;
        private String destination;
        private String number;
        private String telephone;
        private long date;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getOid() {
            return oid;
        }

        public void setOid(String oid) {
            this.oid = oid;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public String getStartPlace() {
            return startPlace;
        }

        public void setStartPlace(String startPlace) {
            this.startPlace = startPlace;
        }

        public String getDestination() {
            return destination;
        }

        public void setDestination(String destination) {
            this.destination = destination;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public long getDate() {
            return date;
        }

        public void setDate(long date) {
            this.date = date;
        }
    }
}
