package com.doanhtq.appmusic.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.doanhtq.appmusic.Song;

public class FavoriteSongsOperation {
    SQLiteDatabase mSqLiteDatabase;
    SongOpenHelper mSongOpenHelper;

    public FavoriteSongsOperation(Context context) {
        mSongOpenHelper = new SongOpenHelper(context);
    }

    public void openDatabase(){
        mSqLiteDatabase = mSongOpenHelper.getWritableDatabase();
    }

    public void closeDatabase(){
        mSqLiteDatabase.close();
    }

    // them bai hat moi vao danh sach yeu thch
    public void addSongFavorite(Song song){
        ContentValues mContentValues = new ContentValues();
        mContentValues.put(SongOpenHelper.COLUMN_SONG_TITLE, song.getSongTitle());
        mContentValues.put(SongOpenHelper.COLUMN_SONG_SUBTITLE, song.getSongSubtitle());
        mContentValues.put(SongOpenHelper.COLUMN_SONG_IMAGE, song.getSongImage());
        mContentValues.put(SongOpenHelper.COLUMN_SONG_PATH, song.getSongPath());
        mContentValues.put(SongOpenHelper.COLUMN_SONG_LIKE, song.getSongLike());
        mContentValues.put(SongOpenHelper.COLUMN_SONG_PLAY, song.getSongPlay());
        mContentValues.put(SongOpenHelper.COLUMN_SONG_PLAY_NUMBER, song.getSongPlayNumber());
        mContentValues.put(SongOpenHelper.COLUMN_SONG_DURATION, song.getSongDuration());

        mSqLiteDatabase.insert(SongOpenHelper.TABLE_SONGS_FAVORITE,null, mContentValues);
    }



}
