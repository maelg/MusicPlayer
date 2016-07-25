package fr.maelgui.musicplayer;

import android.content.ClipData;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import fr.maelgui.musicplayer.adapters.AlbumAdapter;
import fr.maelgui.musicplayer.adapters.ArtistAdapter;
import fr.maelgui.musicplayer.adapters.SongAdapter;
import fr.maelgui.musicplayer.models.Album;
import fr.maelgui.musicplayer.models.Artist;
import fr.maelgui.musicplayer.models.Song;
import fr.maelgui.musicplayer.models.Item;


/**
 * Created by mguillos on 22/07/16.
 */
public class MediaBrowserFragment extends Fragment {

    private ArrayList itemList;
    private ListView listView;


    //Overriden method onCreateView
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        String type =  getArguments().getString("type");

        itemList = new ArrayList();

        View view = inflater.inflate(R.layout.fragment_recycler, container, false);
        final RecyclerView rv = (RecyclerView) view.findViewById(R.id.list);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));


        switch (type) {
            case "Songs":

                getSongList();

                rv.setAdapter(new SongAdapter(itemList));

                break;

            case "Artists":
                getArtistList();

                rv.setAdapter(new ArtistAdapter(itemList));
                break;

            case "Albums":
                getAlbumList();
                rv.setAdapter(new AlbumAdapter(itemList));
                rv.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                break;
        }

        Collections.sort(itemList, new Comparator<Item>(){
            public int compare(Item a, Item b){
                return a.getTitle().compareTo(b.getTitle());
            }
        });

        return view;
    }


    public void getSongList() {
        ContentResolver musicResolver = getActivity().getContentResolver();
        Uri musicUri = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor musicCursor = musicResolver.query(musicUri, null, null, null, null);

        if(musicCursor!=null && musicCursor.moveToFirst()){
            //get columns
            int titleColumn = musicCursor.getColumnIndex
                    (android.provider.MediaStore.Audio.Media.TITLE);
            int idColumn = musicCursor.getColumnIndex
                    (android.provider.MediaStore.Audio.Media._ID);
            int artistColumn = musicCursor.getColumnIndex
                    (android.provider.MediaStore.Audio.Media.ARTIST);
            //add songs to list
            do {
                long thisId = musicCursor.getLong(idColumn);
                String thisTitle = musicCursor.getString(titleColumn);
                String thisArtist = musicCursor.getString(artistColumn);
                itemList.add(new Song(thisId, thisTitle, thisArtist));
            }
            while (musicCursor.moveToNext());
        }
    }

    public void getArtistList() {
        ContentResolver musicResolver = getActivity().getContentResolver();
        Uri musicUri = android.provider.MediaStore.Audio.Artists.EXTERNAL_CONTENT_URI;
        Cursor musicCursor = musicResolver.query(musicUri, null, null, null, null);

        if(musicCursor!=null && musicCursor.moveToFirst()){
            //get columns
            int nameColumn = musicCursor.getColumnIndex
                    (MediaStore.Audio.Artists.ARTIST);
            int nbrAlbumsColumn = musicCursor.getColumnIndex
                    (MediaStore.Audio.Artists.NUMBER_OF_ALBUMS);
            int nbrTracksColumn = musicCursor.getColumnIndex
                    (MediaStore.Audio.Artists.NUMBER_OF_TRACKS);

            //add songs to list
            do {
                String thisName = musicCursor.getString(nameColumn);
                String thisNbrAlbums = musicCursor.getString(nbrAlbumsColumn);
                String thisNbrTracks = musicCursor.getString(nbrTracksColumn);
                itemList.add(new Artist(thisName, thisNbrAlbums, thisNbrTracks));
            }
            while (musicCursor.moveToNext());
        }
    }

    public void getAlbumList() {
        ContentResolver musicResolver = getActivity().getContentResolver();
        Uri musicUri = android.provider.MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI;
        Cursor musicCursor = musicResolver.query(musicUri, null, null, null, null);

        if(musicCursor!=null && musicCursor.moveToFirst()){
            //get columns
            int nameColumn = musicCursor.getColumnIndex
                    (MediaStore.Audio.Albums.ALBUM);
            int artColumn = musicCursor.getColumnIndex
                    (MediaStore.Audio.Albums.ALBUM_ART);
            int artistColumn = musicCursor.getColumnIndex
                    (MediaStore.Audio.Albums.ARTIST);

            //add songs to list
            do {
                String thisName = musicCursor.getString(nameColumn);
                String thisArt = musicCursor.getString(artColumn);
                String thisArtist = musicCursor.getString(artistColumn);
                itemList.add(new Album(thisName, thisArt, thisArtist));
            }
            while (musicCursor.moveToNext());
        }
    }
}
