package com.gaoshiwei.coolweather.gson;

/**
 * Created by gaoshiwei on 2017/5/15.
 *
 *
  "aqi":{
        "city":{
            "aqi":"44",
            "pm25":"13"
            }
        }

 *
 */

public class AQI {
    public AQICity city;

    public class AQICity{
        public String aqi;
        public String pm25;

    }
}
