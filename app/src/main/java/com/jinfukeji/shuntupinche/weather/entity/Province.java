package com.jinfukeji.shuntupinche.weather.entity;

import java.util.List;

/**
 * Created by "于志渊"
 * 时间:"9:50"
 * 包名:com.jinfukeji.shuntupinche.weather.entity
 * 描述:省
 */

public class Province {
    private String id;
    private String name;
    private List<City> citys;

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

    public List<City> getCitys() {
        return citys;
    }

    public void setCitys(List<City> citys) {
        this.citys = citys;
    }

    @Override
    public String toString() {
        return name;
    }
}
