package com.doanhtq.appmusic.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SongOpenHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "doanhtq.songs";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_SONGS_ALL = "allSongs";
    public static final String TABLE_SONGS_FAVORITE = "favoriteSongs";

    public static final String COLUMN_SONG_ID = "songID";
    public static final String COLUMN_SONG_TITLE = "songTitle";
    public static final String COLUMN_SONG_SUBTITLE = "songSubtitle";
    public static final String COLUMN_SONG_IMAGE = "songImage";
    public static final String COLUMN_SONG_PATH = "songPath";
    public static final String COLUMN_SONG_LIKE = "songLike";
    public static final String COLUMN_SONG_PLAY = "songPlay";
    public static final String COLUMN_SONG_PLAY_NUMBER = "songPlayNumber";
    public static final String COLUMN_SONG_DURATION = "songDuration";

    public static final String[] ALL_COLUMNS = {
            COLUMN_SONG_ID,
            COLUMN_SONG_TITLE,
            COLUMN_SONG_SUBTITLE,
            COLUMN_SONG_IMAGE,
            COLUMN_SONG_PATH,
            COLUMN_SONG_LIKE,
            COLUMN_SONG_PLAY,
            COLUMN_SONG_PLAY_NUMBER,
            COLUMN_SONG_DURATION
    };


    public static final String CREATE_TABLE_SONGS_ALL = "CREATE TABLE " + TABLE_SONGS_ALL + " ( "
            + COLUMN_SONG_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_SONG_TITLE + " TEXT, "
            + COLUMN_SONG_SUBTITLE + " TEXT, "
            + COLUMN_SONG_IMAGE + " INTEGER, "
            + COLUMN_SONG_PATH + " TEXT, "
            + COLUMN_SONG_LIKE + " INTEGER, "
            + COLUMN_SONG_PLAY + " INTEGER, "
            + COLUMN_SONG_PLAY_NUMBER + " INTEGER, "
            + COLUMN_SONG_DURATION + " INTEGER)";
    public static final String CREATE_TABLE_SONGS_FAVORITE = "CREATE TABLE " + TABLE_SONGS_FAVORITE + " ( "
            + COLUMN_SONG_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_SONG_TITLE + " TEXT, "
            + COLUMN_SONG_SUBTITLE + " TEXT, "
            + COLUMN_SONG_IMAGE + "INTEGER, "
            + COLUMN_SONG_PATH + "TEXT, "
            + COLUMN_SONG_LIKE + "INTEGER, "
            + COLUMN_SONG_PLAY + "INTEGER, "
            + COLUMN_SONG_PLAY_NUMBER + "INTEGER, "
            + COLUMN_SONG_DURATION + "INTEGER )";

    public static final String DELETE_TABLE_SONGS_ALL = "DROP TABLE IF EXISTS " + TABLE_SONGS_ALL;
    public static final String DELETE_TABLE_SONGS_FAVORITE = "DROP TABLE IF EXISTS " + TABLE_SONGS_FAVORITE;

    public SongOpenHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_SONGS_ALL);
        db.execSQL(CREATE_TABLE_SONGS_FAVORITE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DELETE_TABLE_SONGS_ALL);
        db.execSQL(CREATE_TABLE_SONGS_ALL);

        db.execSQL(DELETE_TABLE_SONGS_FAVORITE);
        db.execSQL(CREATE_TABLE_SONGS_FAVORITE);

    }
}
