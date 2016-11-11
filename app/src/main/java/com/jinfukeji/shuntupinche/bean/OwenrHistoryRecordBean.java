package com.jinfukeji.shuntupinche.bean;

import java.util.List;

/**
 * Created by "于志渊"
 * 时间:"14:29"
 * 包名:com.jinfukeji.shuntupinche.bean
 * http://192.168.0.194:8080/carpool/info/selectByOid?oid=bd8525a3-a714-4bcc-be5f-d8e66ce08f87
 * 描述:车主发布历史记录的查询实例
 */

public class OwenrHistoryRecordBean {

    /**
     * message : 查询成功
     * status : ok
     * data : [{"id":"5594b80f-bd0e-4864-b1f6-2093f23d58b5","oid":"bd8525a3-a714-4bcc-be5f-d8e66ce08f87","time":1481212800000,"startPlace":"界首","destination":"合肥","number":"4","telephone":"18196645936","date":1478671985000},{"id":"c7ebba92-ad4c-4dfe-91c2-40e7e0b716c0","oid":"bd8525a3-a714-4bcc-be5f-d8e66ce08f87","time":1481212800000,"startPlace":"界首","destination":"淮北","number":"4","telephone":"18196645936","date":1478671979000},{"id":"3889d711-2378-4aed-bb5a-757d20a4e7d8","oid":"bd8525a3-a714-4bcc-be5f-d8e66ce08f87","time":1481212800000,"startPlace":"固镇","destination":"淮北","number":"4","telephone":"18196645936","date":1478671972000},{"id":"c3179dda-a3d0-4973-ae23-6cab62d31291","oid":"bd8525a3-a714-4bcc-be5f-d8e66ce08f87","time":1481212800000,"startPlace":"固镇","destination":"合肥","number":"4","telephone":"18196645936","date":1478671962000},{"id":"db69de47-af14-4fd7-aa2a-42cb7addd764","oid":"bd8525a3-a714-4bcc-be5f-d8e66ce08f87","time":1481212800000,"startPlace":"淮北","destination":"合肥","number":"4","telephone":"18196645936","date":1478671953000},{"id":"1743ebfc-0c32-4d5b-87b1-0e99b24174cd","oid":"bd8525a3-a714-4bcc-be5f-d8e66ce08f87","time":1480003200000,"startPlace":"淮北","destination":"合肥","number":"4","telephone":"18196645936","date":1478671945000},{"id":"5ca08499-1363-428c-84d5-dc13f1ed4c76","oid":"bd8525a3-a714-4bcc-be5f-d8e66ce08f87","time":1480262400000,"startPlace":"淮北","destination":"合肥","number":"6","telephone":"18196645936","date":1478671935000},{"id":"7dac3e0e-cd4b-487c-85f0-56441f55dce4","oid":"bd8525a3-a714-4bcc-be5f-d8e66ce08f87","time":1480262400000,"startPlace":"淮北","destination":"合肥","number":"2","telephone":"18196645936","date":1478671929000},{"id":"ca2eb96c-7235-47d8-a8a0-7139e5ee52b2","oid":"bd8525a3-a714-4bcc-be5f-d8e66ce08f87","time":1480089600000,"startPlace":"蚌埠","destination":"合肥","number":"4","telephone":"18196645936","date":1478671685000},{"id":"4bb7f1e0-5f72-40c8-b3df-ea54379a96c1","oid":"bd8525a3-a714-4bcc-be5f-d8e66ce08f87","time":1480089600000,"startPlace":"","destination":null,"number":null,"telephone":null,"date":1478671670000},{"id":"e36cc692-1618-4057-87b4-26e9cf68c929","oid":"bd8525a3-a714-4bcc-be5f-d8e66ce08f87","time":1480089600000,"startPlace":"","destination":null,"number":null,"telephone":null,"date":1478671656000},{"id":"faef1d97-eeef-4021-933f-4edc99a38176","oid":"bd8525a3-a714-4bcc-be5f-d8e66ce08f87","time":1480089600000,"startPlace":"蚌埠","destination":"合肥","number":"4","telephone":"18196645936","date":1478671632000},{"id":"00b65a8a-528e-454e-9ad8-ad0ff2ebb03d","oid":"bd8525a3-a714-4bcc-be5f-d8e66ce08f87","time":1480089600000,"startPlace":"蚌埠","destination":"合肥","number":"4","telephone":"18196645936","date":1478671588000},{"id":"24e04f18-e280-44b5-8a67-df1c1de39e53","oid":"bd8525a3-a714-4bcc-be5f-d8e66ce08f87","time":1479916800000,"startPlace":"合肥","destination":"我家","number":"5","telephone":"18196645936","date":1478671258000}]
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
         * id : 5594b80f-bd0e-4864-b1f6-2093f23d58b5
         * oid : bd8525a3-a714-4bcc-be5f-d8e66ce08f87
         * time : 1481212800000
         * startPlace : 界首
         * destination : 合肥
         * number : 4
         * telephone : 18196645936
         * date : 1478671985000
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
