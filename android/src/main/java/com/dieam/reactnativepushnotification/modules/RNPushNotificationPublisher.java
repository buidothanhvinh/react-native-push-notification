package com.dieam.reactnativepushnotification.modules;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.Calendar;
import java.util.Date;

import static com.dieam.reactnativepushnotification.modules.RNPushNotification.LOG_TAG;

public class RNPushNotificationPublisher extends BroadcastReceiver {
    final static String NOTIFICATION_ID = "notificationId";

    @Override
    public void onReceive(Context context, Intent intent) {
        int id = intent.getIntExtra(NOTIFICATION_ID, 0);
        long currentTime = System.currentTimeMillis();

        Log.i(LOG_TAG, "NotificationPublisher: Prepare To Publish: " + id + ", Now Time: " + currentTime);

        Application applicationContext = (Application) context.getApplicationContext();

        Bundle bundle = intent.getExtras();
        if (bundle != null && isShowNotification(bundle, currentTime)){
            new RNPushNotificationHelper(applicationContext)
                    .sendToNotificationCentre(bundle);
        }
    }

    private boolean isShowNotification(Bundle bundle, long currentTime){
        long fireDate = (long) bundle.getDouble("fireDate");
        return Math.abs(fireDate - currentTime) < (1000 * 59);
    }
}
