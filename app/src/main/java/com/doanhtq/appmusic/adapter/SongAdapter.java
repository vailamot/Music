package com.doanhtq.appmusic.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.doanhtq.appmusic.Key;
import com.doanhtq.appmusic.R;
import com.doanhtq.appmusic.Song;
import com.doanhtq.appmusic.interfaces.IClickItem;

import java.util.ArrayList;
import java.util.LinkedList;

import es.claucookie.miniequalizerlibrary.EqualizerView;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongViewHolder> {
    private ArrayList<Song> mSongList;
    private LayoutInflater mInflater;

    private IClickItem mIClickItem;



    public SongAdapter(Context context, ArrayList<Song> mSongList, IClickItem mIClickItem) {
        this.mSongList = mSongList;
        mInflater = LayoutInflater.from(context);
        this.mIClickItem = mIClickItem;
    }

    @NonNull
    @Override
    public SongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = mInflater.inflate(R.layout.item_of_all_songs, parent, false);
        return new SongViewHolder(mView, this, mIClickItem);
    }

    @Override
    public void onBindViewHolder(@NonNull SongViewHolder holder, int position) {
        Song mSong = mSongList.get(position);
        holder.mSongID.setText(mSong.getSongID()+"");
        holder.mSongTitle.setText(mSong.getSongTitle());
        int duration = mSong.getSongDuration()/1000;
        String stringOfDuration = (duration/60) + ":" + (duration%60);
        holder.mSongSubtitle.setText(stringOfDuration);
        if (mSong.getSongPlay() == Key.PLAY){
            holder.mSongTitle.setTypeface(null, Typeface.BOLD);
            holder.mSongIsPlay.setVisibility(View.VISIBLE);
            holder.mSongIsPlay.animateBars();
            holder.mSongID.setVisibility(View.GONE);
        } else {
            holder.mSongTitle.setTypeface(null, Typeface.NORMAL);
            holder.mSongIsPlay.setVisibility(View.GONE);
            holder.mSongID.setVisibility(View.VISIBLE);
        }
//        switch (mSong.getSongPlay()){
//            case Key.STOP:{
//                holder.mSongTitle.setTypeface(null, Typeface.NORMAL);
//            }
//            case Key.PLAY:{
//                holder.mSongTitle.setTypeface(null, Typeface.BOLD);
//            }
//        }


    }

    @Override
    public int getItemCount() {
        return mSongList.size();
    }



    public class SongViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mSongID, mSongTitle, mSongSubtitle;
        private ImageView mIconMore;
        EqualizerView mSongIsPlay;
        private SongAdapter mSongAdapter;
        private IClickItem mIClickItem;

        public SongViewHolder(@NonNull View itemView, SongAdapter songAdapter, IClickItem iClickItem) {
            super(itemView);
            mSongID = itemView.findViewById(R.id.song_id);
            mSongTitle = itemView.findViewById(R.id.song_title);
            mSongSubtitle = itemView.findViewById(R.id.song_subtitle);
            mIconMore = itemView.findViewById(R.id.ic_more);
            mSongIsPlay = itemView.findViewById(R.id.song_is_play);
            this.mSongAdapter = songAdapter;
            this.mIClickItem = iClickItem;
            itemView.setOnClickListener(this);

            mIconMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PopupMenu popupMenu = new PopupMenu(v.getContext(), v);
                    popupMenu.inflate(R.menu.menu_popup);
                    popupMenu.show();
                }
            });
        }

        @Override
        public void onClick(View v) {
            mIClickItem.onClickItem(getAdapterPosition());
        }
    }
}
