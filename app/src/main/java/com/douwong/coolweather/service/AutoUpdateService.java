package com.douwong.coolweather.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;

import com.douwong.coolweather.receiver.AutoUpdateReceiver;
import com.douwong.coolweather.util.HttpUtil;
import com.douwong.coolweather.util.Utility;

/**
 * Created by Zds .
 * on 2016/10/26  16:49
 * 描述:
 * 包名: com.douwong.coolweather.service
 */

public class AutoUpdateService extends Service {
    public final static int anHour = 8 * 60 * 60 * 1000; // 这是8小时的毫秒数

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                updateWeather();
            }
        }).start();


        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        long triggerAtTime = SystemClock.elapsedRealtime() + anHour;
        Intent i = new Intent(this, AutoUpdateReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(this, 0, i, 0);
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pi);
        return super.onStartCommand(intent, flags, startId);
    }

    private void updateWeather() {
        SharedPreferences prefs = PreferenceManager.
                getDefaultSharedPreferences(this);
        String weatherCode = prefs.getString("weather_code", "");
        String address = "http://www.weather.com.cn/data/cityinfo/" +
                weatherCode + ".html";
        HttpUtil.sendHttpRequest(address, new HttpUtil.HttpCallbackListener() {
            @Override
            public void onFinish(String response) {

                //县温度的具体信息
                Utility.handleWeatherResponse(AutoUpdateService.this,
                        response);
            }

            @Override
            public void onError(Exception e) {
                e.printStackTrace();
            }

        });
    }
}


