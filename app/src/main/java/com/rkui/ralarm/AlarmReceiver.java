package com.rkui.ralarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by RaoKui on 2017/10/19.
 */

public class AlarmReceiver extends BroadcastReceiver {

    private static final String ALARM_INTENT = "com.rkui.alarm";

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        boolean isRepeat = bundle.getBoolean("isRepeat", false);
        long time = bundle.getLong("time");
        if (isRepeat) {
            // 设置重复闹钟
            AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            Intent intentAlarm = new Intent(ALARM_INTENT);
            intent.putExtras(bundle);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context,,intentAlarm, )

        }


    }
}
