package com.jinfukeji.shuntupinche.bean;

/**
 * Created by "于志渊"
 * 时间:"14:03"
 * 包名:com.jinfukeji.shuntupinche.bean
 * 描述:车主发布的实体例
 */

public class OwenrRecordBean {

    /**
     * message : 添加成功
     * status : ok
     * data : {"id":"ca2eb96c-7235-47d8-a8a0-7139e5ee52b2","oid":"bd8525a3-a714-4bcc-be5f-d8e66ce08f87","time":1480089600000,"startPlace":"蚌埠","destination":"合肥","number":"4","telephone":"18196645936","date":1478671684968}
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
         * id : ca2eb96c-7235-47d8-a8a0-7139e5ee52b2
         * oid : bd8525a3-a714-4bcc-be5f-d8e66ce08f87
         * time : 1480089600000
         * startPlace : 蚌埠
         * destination : 合肥
         * number : 4
         * telephone : 18196645936
         * date : 1478671684968
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

        @Override
        public String toString() {
            return "DataBean{" +
                    "id='" + id + '\'' +
                    ", oid='" + oid + '\'' +
                    ", time=" + time +
                    ", startPlace='" + startPlace + '\'' +
                    ", destination='" + destination + '\'' +
                    ", number='" + number + '\'' +
                    ", telephone='" + telephone + '\'' +
                    ", date=" + date +
                    '}';
        }
    }
}
