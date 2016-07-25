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
import fr.maelgui.musicplayer.adapters.AlbumAdapter;
import fr.maelgui.musicplayer.models.Album;

/**
 * Created by mguillos on 25/07/16.
 */
public class AlbumFragment extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<Album> albumList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler, container, false);
        MediaProvider mediaProvider = new MediaProvider();

        albumList = mediaProvider.getAlbumList(getActivity());

        recyclerView = (RecyclerView) view.findViewById(R.id.list);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setAdapter(new AlbumAdapter(albumList));

        Collections.sort(albumList, new Comparator<Album>(){
            public int compare(Album a, Album b){
                return a.getTitle().compareTo(b.getTitle());
            }
        });

        return view;
    }
}
