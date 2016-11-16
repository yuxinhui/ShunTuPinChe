package com.jinfukeji.shuntupinche.weather.json.entity;

/**
 * Created by "于志渊"
 * 时间:"9:52"
 * 包名:com.jinfukeji.shuntupinche.weather.json.entity
 * 描述:
 */

public class Index {
    private String title;
    private String zs;
    private String tipt;
    private String des;

    public Index() {
    }

    public Index(String title, String zs, String tipt, String des) {
        this.title = title;
        this.zs = zs;
        this.tipt = tipt;
        this.des = des;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getZs() {
        return zs;
    }

    public void setZs(String zs) {
        this.zs = zs;
    }

    public String getTipt() {
        return tipt;
    }

    public void setTipt(String tipt) {
        this.tipt = tipt;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    @Override
    public String toString() {
        return "Index{" +
                "title='" + title + '\'' +
                ", zs='" + zs + '\'' +
                ", tipt='" + tipt + '\'' +
                ", des='" + des + '\'' +
                '}';
    }
}
