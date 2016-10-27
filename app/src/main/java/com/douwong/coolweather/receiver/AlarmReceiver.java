package com.douwong.coolweather.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.douwong.coolweather.service.LongRunningService;

/**
 * Created by Zds .
 * on 2016/10/27  10:55
 * 描述:
 * 包名: com.douwong.coolweather.receiver
 */

public class AlarmReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent(context, LongRunningService.class);

        context.startService(i);


    }
}
