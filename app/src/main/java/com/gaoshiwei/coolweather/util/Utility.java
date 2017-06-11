package com.gaoshiwei.coolweather.util;

import android.text.TextUtils;

import com.gaoshiwei.coolweather.db.City;
import com.gaoshiwei.coolweather.db.County;
import com.gaoshiwei.coolweather.db.Province;
import com.gaoshiwei.coolweather.gson.Weather;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 处理和解析返回的Gson数据
 * Created by gaoshiwei on 2017/5/11.
 * 数据的原型都是json 我妨碍小酷天气的资料中。
 * <p>
 * 流程：
 * 先使用 JSONArray 和 JSONObject 将数据解析出来，然后组装成实体类对象，再调用 save()方法将数据存储到数据库当中。
 */

public class Utility {

    /**
     * 解析和处理服务器返回的省级数据
     * @param response
     * @return
     */
    public static boolean handleProvinceResponse(String response) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allProvinces = new JSONArray(response);
                for (int i = 0; i < allProvinces.length(); i++) {
                    JSONObject provinceObject = allProvinces.getJSONObject(i);
                    Province province = new Province();
                    province.setProvinceName(provinceObject.getString("name"));
                    province.setProvinceCode(provinceObject.getInt("id"));
                    province.save(); //存到数据库中
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 解析和处理服务器返回的市级数据
     * @param response
     * @param provinceId
     * @return booleanType
     */
    public static boolean handleCityResponse(String response, int provinceId) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allCities = new JSONArray(response);
                for (int i = 0; i < allCities.length(); i++) {
                    JSONObject cityObject = allCities.getJSONObject(i);
                    City city = new City();
                    city.setCityName(cityObject.getString("name"));
                    city.setCityCode(cityObject.getInt("id"));
                    city.setProvinceId(provinceId);
                    city.save(); //存到数据库中
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 解析和处理县级的数据
     * @param response
     * @param cityId
     * @return
     */
    public static boolean handleCountyResponse(String response, int cityId) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allCounties = new JSONArray(response);
                for (int i = 0; i < allCounties.length(); i++) {
                    JSONObject countyObject = allCounties.getJSONObject(i);
                    County county = new County();
                    county.setCountyName(countyObject.getString("name"));
                    county.setCityId(cityId);
                    county.setWeatherId(countyObject.getString("weather_id")); //天气的id
                    county.save(); //存到数据库中
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;

    }

    /**
     * 将返回的 JSON 数据解析成 Weather实体类
     * @param response
     * @return
     retuen.json

    {
    "HeWeather": [
    {
    "status": "ok",
    "basic": {},
    "aqi": {},
    "now": {},
    "suggestion": {},
    "daily_forecast": []
    }
    ]
    }

     */


    public static Weather handleWeatherResponse(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("HeWeather");
            String weatherContent = jsonArray.getJSONObject(0).toString();
            return new Gson().fromJson(weatherContent, Weather.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;

    }


}
