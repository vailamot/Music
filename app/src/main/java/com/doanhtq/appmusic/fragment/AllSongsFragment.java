package com.doanhtq.appmusic.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.doanhtq.appmusic.Utils;
import com.doanhtq.appmusic.R;
import com.doanhtq.appmusic.Song;
import com.doanhtq.appmusic.activity.MusicActivity;
import com.doanhtq.appmusic.adapter.SongAdapter;
import com.doanhtq.appmusic.database.AllSongsOperation;
import com.doanhtq.appmusic.interfaces.IClickItem;
import com.doanhtq.appmusic.interfaces.IMediaPlayback;
import com.doanhtq.appmusic.service.MediaPlaybackService;

import java.util.ArrayList;

public class AllSongsFragment extends Fragment implements IClickItem {
    private RecyclerView mRecyclerView;
    private SongAdapter mSongAdapter;

    // view player_small
    private View viewPlayerSmall;
    private TextView mSongTitlePlayer ;
    private TextView mSongSubtitlePlayer ;
    private ImageView mSongImagePlayer;
    private ImageView mSongIsPlay ;

    private ArrayList<Song> mSongList = new ArrayList<>();
    private Song mSong = null;
    private AllSongsOperation mAllSongsOperation;

    private IMediaPlayback mIMediaPlayback;

    private MediaPlaybackService mMediaPlaybackService;

    public AllSongsFragment() {
        // Required empty public constructor
    }
    public AllSongsFragment(IMediaPlayback iMediaPlayback){
        this.mIMediaPlayback = iMediaPlayback;
    }

    public static AllSongsFragment newInstance(IMediaPlayback  iMediaPlayback) {
        AllSongsFragment fragment = new AllSongsFragment(iMediaPlayback);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mSong = (Song) getArguments().getSerializable(Utils.SONG_ITEM_KEY);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_all_songs, container, false);
        // lay danh sach bai hat tu database
        mAllSongsOperation = new AllSongsOperation(container.getContext());
        mSongList = mAllSongsOperation.getAllSongs();

        // hien thi danh sach bai hat bang recyclerview
        if(mSongList != null){
            mRecyclerView = rootView.findViewById(R.id.rv_all_songs);
            mSongAdapter = new SongAdapter(container.getContext(), mSongList,this);
            mRecyclerView.setAdapter(mSongAdapter);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        }

        // anh xa view player_small
        viewPlayerSmall = rootView.findViewById(R.id.player_small);

        //
        if (mSong != null){
            mSongTitlePlayer = viewPlayerSmall.findViewById(R.id.song_title);
            mSongSubtitlePlayer = viewPlayerSmall.findViewById(R.id.song_subtitle);
            mSongImagePlayer = viewPlayerSmall.findViewById(R.id.song_image);
            mSongIsPlay = viewPlayerSmall.findViewById(R.id.song_is_play);
            updateUI(mSong.getSongID()-1);
            displayPlayerSmall();
        }
        return rootView;
    }

    // nhan vi tri item click
    @Override
    public void onClickItem(int position) {
//        Toast.makeText(getActivity().getApplicationContext(), "clicked" + position, Toast.LENGTH_SHORT).show();
//        MediaPlayer mediaPlayer = MediaPlayer.create(getActivity().getApplicationContext(),
//                Uri.parse(mSongList.get(position).getSongPath()));
//        mediaPlayer.start();
//        mMediaPlaybackService = new MediaPlaybackService();
//        mMediaPlaybackService.setSongList(mSongList);
        Intent intent = new Intent(getContext().getApplicationContext(), MediaPlaybackService.class);
        intent.putExtra(Utils.EXTRA_SONG_POSITION, position);
        getContext().getApplicationContext().startService(intent);

        updateUI(position);
        if (MusicActivity.isDisplayPortrait) {
            mSongTitlePlayer = viewPlayerSmall.findViewById(R.id.song_title);
            mSongSubtitlePlayer = viewPlayerSmall.findViewById(R.id.song_subtitle);
            mSongImagePlayer = viewPlayerSmall.findViewById(R.id.song_image);
            mSongIsPlay = viewPlayerSmall.findViewById(R.id.song_is_play);
            displayPlayerSmall();
        } else {
            mIMediaPlayback.openMediaPlaybackFragment(mSong);
        }
    }

    // cap nhat giao dien khi phat nhac
    public void updateUI(int position){


        for (int i = 0; i < mSongList.size(); i++){
            if (i == position) {
                mSong = mSongList.get(i);
                mSong.setSongPlay(Utils.PLAY);
               // displayPlayerSmall();
            } else {
                mSongList.get(i).setSongPlay(Utils.STOP);
            }
            mSongAdapter.notifyDataSetChanged();
        }

    }
    // quan ly view player small
    public void displayPlayerSmall(){

        viewPlayerSmall.setVisibility(View.VISIBLE);
        mSongTitlePlayer.setText(mSong.getSongTitle());
        mSongSubtitlePlayer.setText(mSong.getSongSubtitle());

        final Boolean[] isPlay = {true};

        mSongIsPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPlay[0]){
                    mSongIsPlay.setImageResource(R.drawable.ic_play);
                    isPlay[0] = false;
                }else {
                    mSongIsPlay.setImageResource(R.drawable.ic_pause);
                    isPlay[0] = true;
                }
            }
        });
        mSongTitlePlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIMediaPlayback.openMediaPlaybackFragment(mSong);
            }
        });
    }
}