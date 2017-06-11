package com.gaoshiwei.coolweather.gson;

import com.google.gson.annotations.SerializedName;


/**
 * Created by gaoshiwei on 2017/5/15.
 * <p>
 * 由于 JSON 中的一些字段可能不太适合直接作为 Java 字段来命名，
 * 因此这里使用了 @SerializedName 注解的方式来让 JSON 字段和 Java 字段之间建立映射关系。
 * <p>
 * "basic":{
 *      "city":"苏州",
 *      "id":"CN101190401",
 *      "update":{
 *              "loc":"2016-08-08 21:58"
 *              }
 *      }
 */

public class Basic {
    @SerializedName("city")
    public String cityName;

    @SerializedName("id")
    public String weatherId;

    public Update update;

    /**
     * 内部类 Update
     */
    public class Update {
        @SerializedName("loc")
        public String updateTime;
    }
}
