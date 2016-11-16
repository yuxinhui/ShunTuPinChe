package com.jinfukeji.shuntupinche.weather.entity;

import java.util.List;

/**
 * Created by "于志渊"
 * 时间:"9:47"
 * 包名:com.jinfukeji.shuntupinche.weather.entity
 * 描述:城市
 */

public class City {
    private String id;
    private String name;
    private List<District> disList;

    public City() {
    }

    public City(String id, String name, List<District> disList) {
        this.id = id;
        this.name = name;
        this.disList = disList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<District> getDisList() {
        return disList;
    }

    public void setDisList(List<District> disList) {
        this.disList = disList;
    }

    @Override
    public String toString() {
        return name;
    }
}
