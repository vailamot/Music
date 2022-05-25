package com.doanhtq.appmusic.application;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.doanhtq.appmusic.Utils;

public class MusicApplication extends Application {
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannel();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createNotificationChannel() {
        NotificationChannel musicChannel = new NotificationChannel(Utils.NOTIFICATION_CHANNEL_ID,
                Utils.NOTIFICATION_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH);
        musicChannel.setSound(null,null);
        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        if (notificationManager != null) {
            notificationManager.createNotificationChannel(musicChannel);
        }
    }
}
