package com.gaoshiwei.coolweather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by gaoshiwei on 2017/5/15.

 "now":{
    "tmp":"29",
    "cond":{
    "txt":"阵雨"
    }
 }


 */

public class Now {

    @SerializedName("tmp")
    public String temperature;

    @SerializedName("cond")
    public More more;

    public class More {
        @SerializedName("txt")
        public String info;

    }
}
