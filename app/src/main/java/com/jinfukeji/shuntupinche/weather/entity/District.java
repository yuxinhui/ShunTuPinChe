package com.jinfukeji.shuntupinche.weather.entity;

/**
 * Created by "于志渊"
 * 时间:"9:48"
 * 包名:com.jinfukeji.shuntupinche.weather.entity
 * 描述:区域
 */

public class District {
    private String id;
    private String name;

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

    @Override
    public String toString() {
        return name;
    }
}
