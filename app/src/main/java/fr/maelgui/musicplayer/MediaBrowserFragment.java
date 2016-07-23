package fr.maelgui.musicplayer;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
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
import fr.maelgui.musicplayer.models.Artist;
import fr.maelgui.musicplayer.models.Song;
import fr.maelgui.musicplayer.models.Album;

/**
 * Created by mguillos on 22/07/16.
 */
public class MediaBrowserFragment extends Fragment {

    private ArrayList<Song> songsList;
    private ArrayList<Artist> artistsList;
    private ArrayList<Album> albumsList;
    private ListView listView;


    //Overriden method onCreateView
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        String type =  getArguments().getString("type");
        int fragment = -1;

        switch (type) {
            case "Songs":
                fragment = R.layout.fragment_main_list;
                break;

            case "Artists":
                fragment = R.layout.fragment_main_list;
                break;

            case "Albums":
                fragment = R.layout.fragment_main_grid;
                break;
        }

        View view = inflater.inflate(fragment, container, false);
        listView = (ListView) view.findViewById(R.id.item_list);

        switch (type) {
            case "Songs":
                songsList = new ArrayList<Song>();

                getSongList();

                Collections.sort(songsList, new Comparator<Song>(){
                    public int compare(Song a, Song b){
                        return a.getTitle().compareTo(b.getTitle());
                    }
                });

                SongAdapter songAdt = new SongAdapter(getActivity(), songsList);
                listView.setAdapter(songAdt);
                break;

            case "Artists":
                artistsList = new ArrayList<Artist>();

                getArtistList();

                Collections.sort(artistsList, new Comparator<Artist>(){
                    public int compare(Artist a, Artist b){
                        return a.getName().compareTo(b.getName());
                    }
                });

                ArtistAdapter artistAdt = new ArtistAdapter(getActivity(), artistsList);
                listView.setAdapter(artistAdt);
                break;

            case "Albums":
                GridView gridView = (GridView) view.findViewById(R.id.item_grid);
                albumsList = new ArrayList<Album>();

                getAlbumList();

                Collections.sort(albumsList, new Comparator<Album>(){
                    public int compare(Album a, Album b){
                        return a.getName().compareTo(b.getName());
                    }
                });

                AlbumAdapter albumAdt = new AlbumAdapter(getActivity(), albumsList);
                gridView.setAdapter(albumAdt);
                break;
        }

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
                songsList.add(new Song(thisId, thisTitle, thisArtist));
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
                artistsList.add(new Artist(thisName, thisNbrAlbums, thisNbrTracks));
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
                albumsList.add(new Album(thisName, thisArt, thisArtist));
            }
            while (musicCursor.moveToNext());
        }
    }
}
