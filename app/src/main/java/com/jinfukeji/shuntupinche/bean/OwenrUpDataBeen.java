package com.jinfukeji.shuntupinche.bean;

/**
 * Created by "于志渊"
 * 时间:"16:00"
 * 包名:com.jinfukeji.shuntupinche.bean
 * 描述:司机修改记录实例
 */

public class OwenrUpDataBeen {
    /**
     * message : 修改成功
     * status : ok
     * data : {"id":"8842e260-d350-43d4-bb53-2f1d106ace47","oid":null,"time":1480435200000,"startPlace":"合肥","destination":"淮北","number":"2","telephone":"18196645936","date":null}
     */

    private String message;
    private String status;
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
        /**
         * id : 8842e260-d350-43d4-bb53-2f1d106ace47
         * oid : null
         * time : 1480435200000
         * startPlace : 合肥
         * destination : 淮北
         * number : 2
         * telephone : 18196645936
         * date : null
         */

        private String id;
        private Object oid;
        private long time;
        private String startPlace;
        private String destination;
        private String number;
        private String telephone;
        private Object date;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Object getOid() {
            return oid;
        }

        public void setOid(Object oid) {
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

        public Object getDate() {
            return date;
        }

        public void setDate(Object date) {
            this.date = date;
        }
    }
}
