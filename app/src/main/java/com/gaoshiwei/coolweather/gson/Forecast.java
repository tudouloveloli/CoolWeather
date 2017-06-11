package com.gaoshiwei.coolweather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by gaoshiwei on 2017/5/15.
 * 这个比较复杂，它是一个数组。
 */

public class Forecast {

    public String date;
    @SerializedName("tmp")
    public Temperature temperature;
    @SerializedName("cond")
    public More more;


    public class Temperature {
        public String max;
        public String min;

    }

    public class More {
        @SerializedName("txt_d")
        public String info;
    }

}
