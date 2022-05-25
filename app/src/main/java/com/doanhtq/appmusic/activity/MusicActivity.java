package com.doanhtq.appmusic.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Display;
import android.view.Menu;
import android.view.Surface;
import android.view.WindowManager;
import android.widget.Toast;

import com.doanhtq.appmusic.Utils;
import com.doanhtq.appmusic.R;
import com.doanhtq.appmusic.Song;
import com.doanhtq.appmusic.database.AllSongsOperation;
import com.doanhtq.appmusic.fragment.AllSongsFragment;
import com.doanhtq.appmusic.fragment.MediaPlaybackFragment;
import com.doanhtq.appmusic.interfaces.IMediaPlayback;

public class MusicActivity extends AppCompatActivity implements IMediaPlayback{
    private AllSongsOperation mAllSongsOperation;



    public static Boolean isDisplayPortrait;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        isDisplayPortrait = checkDisplay();
        requestPermission();
        displayAllSongs();

    }

    // cap quyen truy cap
    public void requestPermission(){
        if (ContextCompat.checkSelfPermission(MusicActivity.this,
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MusicActivity.this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ContextCompat.checkSelfPermission(MusicActivity.this,
                        Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission Granted!", Toast.LENGTH_SHORT).show();
                    loadAllSongs();
                    displayAllSongs();

                } else {
                    Toast.makeText(this, "ban phai cho phep truy cap bo nho", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }
    }

    // load du lieu vao database
    public void loadAllSongs(){
        mAllSongsOperation = new AllSongsOperation(this);

        Cursor cursor = getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                null, null, null, null);
        if ((cursor != null) && (cursor.moveToFirst())) {
            cursor.moveToFirst();
            while (cursor.moveToNext()) {
                Song mSong = new Song(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE)),
                        cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)),
                        cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION)),
                        Utils.STOP);
                mAllSongsOperation.addSong(mSong);
            }
        }
    }

    // hien thi fragment danh sach bai hat
    public void displayAllSongs(){
        AllSongsFragment allSongsFragment = AllSongsFragment.newInstance(this);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container,allSongsFragment).addToBackStack(null).commit();
        if (!isDisplayPortrait) {
            MediaPlaybackFragment mediaPlaybackFragment = new MediaPlaybackFragment();
            FragmentManager fragmentManagerPlayback = getSupportFragmentManager();
            FragmentTransaction fragmentTransactionPlayback = fragmentManagerPlayback.beginTransaction();
            fragmentTransactionPlayback.replace(R.id.fragment_playback, mediaPlaybackFragment).addToBackStack(null).commit();
        }
    }


    // action bar chua nut tim kiem
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_action_bar, menu);
        return true;
    }

    @Override
    public void openMediaPlaybackFragment(Song mSong) {
        MediaPlaybackFragment mediaPlaybackFragment = MediaPlaybackFragment.newInstance(this);
        Bundle bundle = new Bundle();
        bundle.putSerializable(Utils.SONG_ITEM_KEY, mSong);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        mediaPlaybackFragment.setArguments(bundle);
        if (isDisplayPortrait) {
            fragmentTransaction.replace(R.id.fragment_container,mediaPlaybackFragment).addToBackStack(null).commit();
        } else {
            fragmentTransaction.replace(R.id.fragment_playback, mediaPlaybackFragment).addToBackStack(null).commit();
        }

    }

    @Override
    public void returnAllSongsFragment(Song mSong) {
        AllSongsFragment allSongsFragment = AllSongsFragment.newInstance(this);
        Bundle bundle = new Bundle();
        bundle.putSerializable(Utils.SONG_ITEM_KEY, mSong);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        allSongsFragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.fragment_container,allSongsFragment).addToBackStack(null).commit();
    }

    public Boolean checkDisplay(){
        Display display = ((WindowManager)getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        if (display.getRotation() == Surface.ROTATION_0){
            return true;    // man hinh doc
        } else {
            return false;   // man hinh ngang
        }
    }
    public Boolean getDisplayPortrait() {
        return isDisplayPortrait;
    }
}