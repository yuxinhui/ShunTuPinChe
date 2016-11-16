package com.jinfukeji.shuntupinche.weather.json.entity;

import java.util.List;

/**
 * Created by "于志渊"
 * 时间:"9:55"
 * 包名:com.jinfukeji.shuntupinche.weather.json.entity
 * 描述:
 */

public class Result {
    private String currentCity;
    private String pm25;
    private List<Index> index;
    private List<Weather_data> weather_data;

    public Result() {
    }

    public Result(String currentCity, String pm25, List<Index> index, List<Weather_data> weather_data) {
        this.currentCity = currentCity;
        this.pm25 = pm25;
        this.index = index;
        this.weather_data = weather_data;
    }

    public List<Weather_data> getWeather_data() {
        return weather_data;
    }

    public void setWeather_data(List<Weather_data> weather_data) {
        this.weather_data = weather_data;
    }

    public String getCurrentCity() {
        return currentCity;
    }

    public void setCurrentCity(String currentCity) {
        this.currentCity = currentCity;
    }

    public String getPm25() {
        return pm25;
    }

    public void setPm25(String pm25) {
        this.pm25 = pm25;
    }

    public List<Index> getIndex() {
        return index;
    }

    public void setIndex(List<Index> index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "Result{" +
                "currentCity='" + currentCity + '\'' +
                ", pm25='" + pm25 + '\'' +
                ", index=" + index +
                ", weather_data=" + weather_data +
                '}';
    }
}
