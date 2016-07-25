package fr.maelgui.musicplayer.adapters;

/**
 * Created by mguillos on 25/07/16.
 */

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.maelgui.musicplayer.MainActivity;
import fr.maelgui.musicplayer.R;
import fr.maelgui.musicplayer.models.Song;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.MyViewHolder> {

    private ArrayList<Song> songs;
    private Context mContext;

    public SongAdapter( ArrayList<Song> theSongs, Context context){
        songs = theSongs;
        mContext = context;
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_song, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Song song = songs.get(position);
        holder.display(song, position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final View view;
        private final ImageView songAlbumArt;
        private final TextView songName;
        private final TextView songArtist;

        private Song currSong;

        public MyViewHolder(final View itemView) {
            super(itemView);
            view = itemView;

            songAlbumArt = (ImageView)itemView.findViewById(R.id.albumArt);
            songName = (TextView)itemView.findViewById(R.id.song_title);
            songArtist = (TextView)itemView.findViewById(R.id.song_artist);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((MainActivity) mContext).play(songs, Integer.parseInt(view.getTag().toString()));
                }
            });
        }

        public void display(Song song, int position) {
            currSong = song;
            songAlbumArt.setImageResource(R.drawable.default_album);
            songName.setText(song.getTitle());
            songArtist.setText(song.getArtist());
            view.setTag(position);
        }
    }

}