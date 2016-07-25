package fr.maelgui.musicplayer.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import fr.maelgui.musicplayer.MediaProvider;
import fr.maelgui.musicplayer.R;
import fr.maelgui.musicplayer.adapters.SongAdapter;
import fr.maelgui.musicplayer.models.Song;

/**
 * Created by mguillos on 25/07/16.
 */
public class SongFragment extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<Song> songList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler, container, false);
        MediaProvider mediaProvider = new MediaProvider();

        songList = mediaProvider.getSongList(getActivity());

        recyclerView = (RecyclerView) view.findViewById(R.id.list);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        recyclerView.setAdapter(new SongAdapter(songList, getActivity()));

        Collections.sort(songList, new Comparator<Song>(){
            public int compare(Song a, Song b){
                return a.getTitle().compareTo(b.getTitle());
            }
        });

        return view;
    }
}
