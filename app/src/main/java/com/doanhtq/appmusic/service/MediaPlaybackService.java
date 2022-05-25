package com.doanhtq.appmusic.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;

import androidx.core.app.NotificationCompat;

import com.doanhtq.appmusic.R;
import com.doanhtq.appmusic.Song;
import com.doanhtq.appmusic.Utils;
import com.doanhtq.appmusic.activity.MusicActivity;
import com.doanhtq.appmusic.database.AllSongsOperation;

import java.util.ArrayList;

public class MediaPlaybackService extends Service {
    private ArrayList<Song> mSongList = new ArrayList<>();
    private Song mSong = new Song();
    private int mSongPosition;
    private MediaPlayer mMediaPlayer;

    private AllSongsOperation mAllSongsOperation;


    public MediaPlaybackService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mAllSongsOperation = new AllSongsOperation(getApplicationContext());
        mSongList = mAllSongsOperation.getAllSongs();
        mSongPosition = intent.getIntExtra(Utils.EXTRA_SONG_POSITION, 0);
        mSong = mSongList.get(mSongPosition);
        sendNotification();
        startMusic();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }

    private void sendNotification(){
        Intent notificationIntent = new Intent(this, MusicActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, Utils.NOTIFICATION_CHANNEL_ID);
        builder.setContentTitle(mSong.getSongTitle());
        builder.setContentText(mSong.getSongSubtitle());
        builder.setSmallIcon(R.drawable.ic_music);
        builder.setContentIntent(pendingIntent);
        Notification notification = builder.build();

        startForeground(1, notification);
    }

    private void startMusic(){
        mMediaPlayer = MediaPlayer.create(getApplicationContext(),
        Uri.parse(mSong.getSongPath()));
        mMediaPlayer.start();
    }

}