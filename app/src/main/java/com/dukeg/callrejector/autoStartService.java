package com.dukeg.callrejector;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

import com.dukeg.callrejector.LogUtils.logger;

/**
 * Created by Administrator on 2017/11/14.
 * This is a service for app auto start.
 */

public class autoStartService extends Service {
    @Override
    public void onCreate() {
        super.onCreate();
        logger.d("onCreate() executed");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        logger.d("onStartCommand() executed");
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(android.R.drawable.stat_sys_warning)
                .setContentTitle("Call Rejector")
                .setContentText("Call Rejector is working");
        Intent notificationIntent = new Intent(this, MainActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(notificationIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(resultPendingIntent);
        startForeground(1, mBuilder.build());
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopForeground(true);
        logger.d("onDestroy() executed");
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
