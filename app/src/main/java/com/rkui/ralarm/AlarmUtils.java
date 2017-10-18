package com.rkui.ralarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import java.util.Calendar;

/**
 * Created by RaoKui on 2017/10/18.
 */

public class AlarmUtils {

    private static final String ALARM_INTENT = "com.rkui.alarm";

    private static final int INTERVAL_TIME = 24 * 60 * 60 * 1000;

    /**
     * 设置重复闹钟
     *
     * @param context  上下文
     * @param alarm_id 闹钟id
     * @param hour     小时
     * @param minute   分钟
     * @param second   秒
     * @param bundle   闹钟传递的内容
     */
    public static void setRepeatAlarm(Context context, int alarm_id, int hour, int minute, int second, Bundle bundle) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
                hour, minute, second);
        Intent alarmIntent = new Intent(ALARM_INTENT);
        if (bundle != null) {
            alarmIntent.putExtras(bundle);
        }
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, alarm_id, alarmIntent,
                PendingIntent.FLAG_CANCEL_CURRENT);
        // API 19之后可以准确设置闹钟
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            alarmManager.setWindow(AlarmManager.RTC_WAKEUP, getTime(calendar.getTimeInMillis()), INTERVAL_TIME, pendingIntent);
        } else {
            alarmManager.set(AlarmManager.RTC_WAKEUP, getTime(calendar.getTimeInMillis()), pendingIntent);
            // 四个参数：1、类型  2、开始时间 3、重复时间
//      alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, getTime(calendar.getTimeInMillis()), INTERVAL_TIME, pendingIntent);
        }

    }

    private static long getTime(long time) {
        return time > System.currentTimeMillis() ? time : (time + 24 * 60 * 60 * 1000);
    }
}
