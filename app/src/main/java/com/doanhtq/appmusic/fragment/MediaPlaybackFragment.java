package com.doanhtq.appmusic.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.doanhtq.appmusic.Key;
import com.doanhtq.appmusic.R;
import com.doanhtq.appmusic.Song;
import com.doanhtq.appmusic.interfaces.IClickItem;
import com.doanhtq.appmusic.interfaces.IMediaPlayback;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MediaPlaybackFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MediaPlaybackFragment extends Fragment {

    private Song mSong;
    private TextView mSongTitle, mSongSubtitle;
    private ImageView mIconReturnList;
    private IMediaPlayback mMediaPlayback;


    public MediaPlaybackFragment(IMediaPlayback mMediaPlayback) {
        this.mMediaPlayback = mMediaPlayback;
        // Required empty public constructor
    }
    public MediaPlaybackFragment(){

    }

    // TODO: Rename and change types and number of parameters
    public static MediaPlaybackFragment newInstance(IMediaPlayback mMediaPlayback) {
        MediaPlaybackFragment fragment = new MediaPlaybackFragment(mMediaPlayback);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        if (getArguments() != null) {
            mSong = (Song) getArguments().getSerializable(Key.SONG_ITEM_KEY);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_media_playback, container, false);
        mSongTitle = view.findViewById(R.id.song_title);
        mSongSubtitle = view.findViewById(R.id.song_subtitle);
        mIconReturnList = view.findViewById(R.id.ic_return_list);
        if (mSong != null) {
            mSongTitle.setText(mSong.getSongTitle());
            mSongSubtitle.setText(mSong.getSongSubtitle());
        }

        mIconReturnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMediaPlayback.returnAllSongsFragment(mSong);
            }
        });

        return view;
    }

}