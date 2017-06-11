package com.gaoshiwei.coolweather.db;

import org.litepal.crud.DataSupport;

/**
 * Created by gaoshiwei on 2017/5/10.
 */

public class City extends DataSupport {
    private int id; // id 是每个实体类中都应该有的字段
    private String cityName; //城市的名字
    private int cityCode; // 城市的代号
    private int provinceId; // 所属省的代号

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getCityCode() {
        return cityCode;
    }

    public void setCityCode(int cityCode) {
        this.cityCode = cityCode;
    }

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }
}
