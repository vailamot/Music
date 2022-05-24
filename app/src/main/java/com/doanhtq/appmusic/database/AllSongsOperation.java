package com.doanhtq.appmusic.database;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.doanhtq.appmusic.Song;

import java.util.ArrayList;
import java.util.LinkedList;

public class AllSongsOperation {
    private SQLiteDatabase mSqLiteDatabase;
    private SongOpenHelper mSongOpenHelper;

    public AllSongsOperation(Context context) {
        mSongOpenHelper = new SongOpenHelper(context);
    }

    // open database
    public void openDatabase(){
        mSqLiteDatabase = mSongOpenHelper.getWritableDatabase();
    }

    //close database
    public void closeDatabase(){
        mSqLiteDatabase.close();
    }

    // lay toan bo bai hat
    public ArrayList<Song> getAllSongs(){
        ArrayList<Song> mSongList = new ArrayList<>();
        openDatabase();
        Cursor cursor = mSqLiteDatabase.query(SongOpenHelper.TABLE_SONGS_ALL, SongOpenHelper.ALL_COLUMNS,
                null,null,null,null,null);
//        Cursor cursor = mSqLiteDatabase.rawQuery("SELECT * FROM " + SongOpenHelper.TABLE_SONGS_ALL, null);
        if ((cursor != null) && (cursor.moveToFirst())){
          //  cursor.moveToFirst();
            do {
                Song mSong = new Song(cursor.getInt(cursor.getColumnIndexOrThrow(SongOpenHelper.COLUMN_SONG_ID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(SongOpenHelper.COLUMN_SONG_TITLE)),
                        cursor.getString(cursor.getColumnIndexOrThrow(SongOpenHelper.COLUMN_SONG_SUBTITLE)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(SongOpenHelper.COLUMN_SONG_IMAGE)),
                        cursor.getString(cursor.getColumnIndexOrThrow(SongOpenHelper.COLUMN_SONG_PATH)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(SongOpenHelper.COLUMN_SONG_LIKE)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(SongOpenHelper.COLUMN_SONG_PLAY)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(SongOpenHelper.COLUMN_SONG_PLAY_NUMBER)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(SongOpenHelper.COLUMN_SONG_DURATION)));
                mSongList.add(mSong);
            } while (cursor.moveToNext());
        }
        closeDatabase();
        return mSongList;
    }

    // them bai hat moi
    public void addSong(Song song){
        openDatabase();
        ContentValues mContentValues = new ContentValues();

        mContentValues.put(SongOpenHelper.COLUMN_SONG_TITLE, song.getSongTitle());
        mContentValues.put(SongOpenHelper.COLUMN_SONG_SUBTITLE, song.getSongSubtitle());
        mContentValues.put(SongOpenHelper.COLUMN_SONG_IMAGE, song.getSongImage());
        mContentValues.put(SongOpenHelper.COLUMN_SONG_PATH, song.getSongPath());
        mContentValues.put(SongOpenHelper.COLUMN_SONG_LIKE, song.getSongLike());
        mContentValues.put(SongOpenHelper.COLUMN_SONG_PLAY, song.getSongPlay());
        mContentValues.put(SongOpenHelper.COLUMN_SONG_PLAY_NUMBER, song.getSongPlayNumber());
        mContentValues.put(SongOpenHelper.COLUMN_SONG_DURATION, song.getSongDuration());

        mSqLiteDatabase.insert(SongOpenHelper.TABLE_SONGS_ALL,null, mContentValues);
        closeDatabase();
    }



}
