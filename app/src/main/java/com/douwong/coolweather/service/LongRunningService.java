package com.douwong.coolweather.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;

import com.douwong.coolweather.receiver.AlarmReceiver;

/**
 * Created by Zds .
 * on 2016/10/27  10:53
 * 描述:
 * 包名: com.douwong.coolweather.service
 */

public class LongRunningService extends Service {


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

            }
        }).start();
        ;

        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        int anHour = 60 * 60 * 1000;

        long elapsedRealtime = SystemClock.elapsedRealtime();

        Intent it = new Intent(this, AlarmReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(this, 0, it, 0);

        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, elapsedRealtime, pi);
        return super.onStartCommand(intent, flags, startId);

    }
}
