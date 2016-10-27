package com.douwong.coolweather.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.douwong.coolweather.R;
import com.douwong.coolweather.activty.MainActivity;

/**
 * Created by Zds .
 * on 2016/10/27  10:11
 * 描述:
 * 包名: com.douwong.coolweather.service
 */

public class MyService extends Service {

    private DownloadBinder mBinder = new DownloadBinder();

    public class DownloadBinder extends Binder {
        public void startDownload() {
            Log.d("MyService", "startDownload executed");

        }

        public int getProgress() {
            Log.d("MyService", "getProgress executed");
            return 0;
        }
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Notification notification = new Notification(R.mipmap.ic_launcher,"NOtification comes", System.currentTimeMillis());

//        Notification.Builder builder = new Notification.Builder();/**/


        Intent notificationIntent = new Intent(this, MainActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                notificationIntent, 0);
//        notification.setLatestEventInfo(this, "This is title", "This is content", pendingIntent);



    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }
}
