package com.geek;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;

import java.io.IOException;

public class HttpClient {
    public static void main(String[] args) {
        String url ="http://localhost:8803/";
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().get().url(url).build();
        Call call = okHttpClient.newCall(request);
        try {
            ResponseBody responseBody = call.execute().body();
            System.out.println(responseBody.string()) ;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
