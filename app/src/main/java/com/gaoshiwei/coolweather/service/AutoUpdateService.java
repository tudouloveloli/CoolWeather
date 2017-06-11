package com.gaoshiwei.coolweather.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.PeriodicSync;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.support.annotation.IntDef;

import com.gaoshiwei.coolweather.gson.Weather;
import com.gaoshiwei.coolweather.util.HttpUtil;
import com.gaoshiwei.coolweather.util.Utility;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class AutoUpdateService extends Service {
    public AutoUpdateService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        updateWeather();
        updateBingPic();
        AlarmManager manager= (AlarmManager) getSystemService(ALARM_SERVICE);
        int anHour=8*60*60*1000;//  this is 8 hour mm type
        long triggerAtTime= SystemClock.elapsedRealtime()+anHour;
        Intent intent1=new Intent(this,AutoUpdateService.class);
        PendingIntent pendingIntent=PendingIntent.getService(this,0,intent1,0);
        manager.cancel(pendingIntent);
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,triggerAtTime,pendingIntent);
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * update Weather
     */
    private void updateWeather() {

        SharedPreferences perfs= PreferenceManager.getDefaultSharedPreferences(this);
        String weatherString=perfs.getString("weather",null);
        if (weatherString!=null){
            // when have cache
            Weather weather= Utility.handleWeatherResponse(weatherString);
            // when no cache , request netWork
            String weatherId=weather.basic.weatherId;
            String weatherUrl="http://guolin.tech/api/weather?cityid="+weatherId+"&key=4a02393a196a4a5b94d435a351b0e199";
            HttpUtil.sendOkHttpRequest(weatherUrl, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String responseText=response.body().string();
                    Weather weather=Utility.handleWeatherResponse(responseText);
                    if (weather!=null && "ok".equals(weather.status)){
//                        ???  look SharePreferences
                        SharedPreferences.Editor editor= PreferenceManager.getDefaultSharedPreferences(AutoUpdateService.this).edit();
                        editor.putString("weather",responseText);
                        editor.apply();
                    }
                }
            });
        }


    }

    /**
     *  update background (BingPic)
     */
    private void updateBingPic() {
        String requestBingPic="http://guolin.tech/api/bing_pic";
        HttpUtil.sendOkHttpRequest(requestBingPic, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String bingPic =response.body().string();
                SharedPreferences.Editor editor=PreferenceManager.getDefaultSharedPreferences(AutoUpdateService.this).edit();
                editor.putString("bing_pic",bingPic);
                editor.apply();
            }
        });



    }

}
