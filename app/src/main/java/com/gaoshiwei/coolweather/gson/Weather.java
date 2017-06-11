package com.gaoshiwei.coolweather.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by gaoshiwei on 2017/5/15.
 *
 * 返回的天气数据中还会包含一项 status 数据 ，成功返回 ok。
 *
 * 在 Weather 类中，我们对 Basic、AQI、Now、Suggestion 和 Forecast 类进行了引用。其
 中，由于 daily_forecast 中包含的是一个数组，因此这里使用了 List 来引用 Forecast 类
 */

public class Weather {
    public String status;
    public Basic basic;
    public AQI aqi;
    public Now now;
    public Suggestion suggestion;
    @SerializedName("daily_forecast")
    public List<Forecast> forecastsList;

}
