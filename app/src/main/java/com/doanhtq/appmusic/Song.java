package com.doanhtq.appmusic;

import java.io.Serializable;

public class Song implements Serializable {
    private int songID ;
    private String songTitle;
    private String songSubtitle;
    private int songImage;
    private String songPath;
    private int songLike;
    private int songPlay;
    private int songPlayNumber;
    private int songDuration;

    public Song() {
    }

    public Song(String songTitle, String songSubtitle) {
        this.songTitle = songTitle;
        this.songSubtitle = songSubtitle;
//        this.songImage = 1;
//        this.songPath = 1+"";
//        this.songLike = 1;
//        this.songPlay = 1;
//        this.songPlayNumber = 1;
//        this.songDuration = 1;
    }

    public Song(String songTitle, String songSubtitle, String songPath, int songDuration, int songPlay) {
        this.songTitle = songTitle;
        this.songSubtitle = songSubtitle;
        this.songPath = songPath;
        this.songDuration = songDuration;
        this.songPlay = songPlay;
    }

    public Song(int songID, String songTitle, String songSubtitle, int songImage, String songPath, int songLike, int songPlay, int songPlayNumber, int songDuration) {
        this.songID = songID;
        this.songTitle = songTitle;
        this.songSubtitle = songSubtitle;
        this.songImage = songImage;
        this.songPath = songPath;
        this.songLike = songLike;
        this.songPlay = songPlay;
        this.songPlayNumber = songPlayNumber;
        this.songDuration = songDuration;
    }

    public int getSongID() {
        return songID;
    }

    public void setSongID(int songID) {
        this.songID = songID;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    public String getSongSubtitle() {
        return songSubtitle;
    }

    public void setSongSubtitle(String songSubtitle) {
        this.songSubtitle = songSubtitle;
    }

    public int getSongImage() {
        return songImage;
    }

    public void setSongImage(int songImage) {
        this.songImage = songImage;
    }

    public String getSongPath() {
        return songPath;
    }

    public void setSongPath(String songPath) {
        this.songPath = songPath;
    }

    public int getSongLike() {
        return songLike;
    }

    public void setSongLike(int songLike) {
        this.songLike = songLike;
    }

    public int getSongPlay() {
        return songPlay;
    }

    public void setSongPlay(int songPlay) {
        this.songPlay = songPlay;
    }

    public int getSongPlayNumber() {
        return songPlayNumber;
    }

    public void setSongPlayNumber(int songPlayNumber) {
        this.songPlayNumber = songPlayNumber;
    }

    public int getSongDuration() {
        return songDuration;
    }

    public void setSongDuration(int songDuration) {
        this.songDuration = songDuration;
    }
}
