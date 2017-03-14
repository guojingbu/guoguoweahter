package com.jiuwu.guojingbu.guoguoweather.utils;

import android.os.Handler;
import android.os.Looper;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

/**
 * @author guojingbu
 */

public abstract class JsonCallback {
    private Handler handler;

    public JsonCallback() {
        this.handler = new Handler(Looper.getMainLooper());
    }

    /**
     * 请求失败
     */
    public void postFailure(final Call call, final IOException e) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                String s = call.request().body().toString();
                failure(s, e);
            }
        });
    }

    public void postSuccess(final Response response) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                String responseText = null;
                try {
                    responseText = response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                success(responseText);
            }
        });
    }

    public abstract void failure(String s, IOException e);

    public abstract void success(String responseText);

}
