package com.doanhtq.appmusic.interfaces;

import com.doanhtq.appmusic.Song;

public interface IMediaPlayback {
    void openMediaPlaybackFragment(Song mSong);
    void returnAllSongsFragment(Song mSong);
}
