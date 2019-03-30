package com.example.bookswap;

import android.app.Application;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

public class Notifications extends Application {
    public static final String CHANNEL_1_ID = "new_request";
    public static final String CHANNEL_2_ID = "new_accept";

    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannels();


    }
    private void createNotificationChannels(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel1 = new NotificationChannel(
                    CHANNEL_1_ID,
                    "new request",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel1.setDescription("New Request");
            NotificationChannel channel2 = new NotificationChannel(
                    CHANNEL_2_ID,
                    "new accept",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel2.setDescription("New Accept");
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel1);
            manager.createNotificationChannel(channel2);
        }
    }


}
