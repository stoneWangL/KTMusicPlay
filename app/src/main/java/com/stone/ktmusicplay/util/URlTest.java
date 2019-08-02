package com.stone.ktmusicplay.util;

import android.util.Log;
import android.widget.Toast;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * @Author: stoneWang
 * @CreateDate: 2019/8/2 17:36
 * @Description:
 */
public class URlTest {
    private static final String TAG = "URlTest";

    public static void getResult() {

        String path = URLProviderUtils.getHomeUrl(0,20);
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(path)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.d(TAG, "获取数据失败");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                Log.d(TAG, "获取数据成功");
                String result = response.body().string();
                Log.d(TAG, "result = " + result);
            }
        });

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    String path = URLProviderUtils.getHomeUrl(0,20);
//                    OkHttpClient client = new OkHttpClient();
//                    Request request = new Request.Builder()
//                            .url(path)
//                            .build();
//                    Response response =  client.newCall(request).execute();
//                    Log.d(TAG, "获取数据成功");
//                    String result = response.body().string();
//                    Log.d(TAG, "result = " + result);
//                } catch (Exception e){
//                    e.printStackTrace();
//                }
//
//            }
//        }).start();

    }
}
