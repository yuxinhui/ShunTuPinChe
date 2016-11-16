package com.jinfukeji.shuntupinche.weather.json.entity;

import java.util.List;

/**
 * Created by "于志渊"
 * 时间:"9:57"
 * 包名:com.jinfukeji.shuntupinche.weather.json.entity
 * 描述:
 */

public class Weather {
    private String error;
    private String status;
    private String date;
    private List<Result> results;

    public Weather() {
    }

    public Weather(String error, String status, String date, List<Result> results) {
        this.error = error;
        this.status = status;
        this.date = date;
        this.results = results;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "error='" + error + '\'' +
                ", status='" + status + '\'' +
                ", date='" + date + '\'' +
                ", results=" + results +
                '}';
    }
}
