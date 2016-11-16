package com.jinfukeji.shuntupinche.weather.json.entity;

import android.graphics.Bitmap;

/**
 * Created by "于志渊"
 * 时间:"9:54"
 * 包名:com.jinfukeji.shuntupinche.weather.json.entity
 * 描述:
 */

public class Weather_data {
    private String date;
    private String dayPictureUrl;
    private Bitmap dayPicture;
    private String nightPictureUrl;
    private Bitmap nightPicture;
    private String weather;
    private String wind;
    private String temperature;

    public Weather_data() {
    }

    public Weather_data(String date, String dayPictureUrl, Bitmap dayPicture, String nightPictureUrl, Bitmap nightPicture, String weather, String wind, String temperature) {
        this.date = date;
        this.dayPictureUrl = dayPictureUrl;
        this.dayPicture = dayPicture;
        this.nightPictureUrl = nightPictureUrl;
        this.nightPicture = nightPicture;
        this.weather = weather;
        this.wind = wind;
        this.temperature = temperature;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDayPictureUrl() {
        return dayPictureUrl;
    }

    public void setDayPictureUrl(String dayPictureUrl) {
        this.dayPictureUrl = dayPictureUrl;
    }

    public Bitmap getDayPicture() {
        return dayPicture;
    }

    public void setDayPicture(Bitmap dayPicture) {
        this.dayPicture = dayPicture;
    }

    public String getNightPictureUrl() {
        return nightPictureUrl;
    }

    public void setNightPictureUrl(String nightPictureUrl) {
        this.nightPictureUrl = nightPictureUrl;
    }

    public Bitmap getNightPicture() {
        return nightPicture;
    }

    public void setNightPicture(Bitmap nightPicture) {
        this.nightPicture = nightPicture;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    @Override
    public String toString() {
        return "Weather_data{" +
                "date='" + date + '\'' +
                ", dayPictureUrl='" + dayPictureUrl + '\'' +
                ", dayPicture=" + dayPicture +
                ", nightPictureUrl='" + nightPictureUrl + '\'' +
                ", nightPicture=" + nightPicture +
                ", weather='" + weather + '\'' +
                ", wind='" + wind + '\'' +
                ", temperature='" + temperature + '\'' +
                '}';
    }
}
