package com.gaoshiwei.coolweather.util;


import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 *
 * 由于 OkHttp 的出色封装，这里和服务器进行交互的代码非常简单
 * 在我们发起一条 HTTP 请求只需要调用 sendOkHttpRequest()方法,，传入请求地址，并注册一个回调来处理服务器响应就可以了。
 * Created by gaoshiwei on 2017/5/10.
 */

public class HttpUtil {
    public static void sendOkHttpRequest(String address,okhttp3.Callback callback){
        OkHttpClient client =new OkHttpClient();
        Request request=new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);

    }
}
