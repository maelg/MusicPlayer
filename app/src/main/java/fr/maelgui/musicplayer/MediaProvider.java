package fr.maelgui.musicplayer;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import java.util.ArrayList;

import fr.maelgui.musicplayer.models.Album;
import fr.maelgui.musicplayer.models.Artist;
import fr.maelgui.musicplayer.models.Song;

/**
 * Created by mguillos on 25/07/16.
 */
public class MediaProvider {

    public ArrayList<Song> getSongList(Context context) {

        ArrayList<Song> itemList = new ArrayList();

        ContentResolver musicResolver = context.getContentResolver();
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

        return itemList;
    }

    public ArrayList<Artist> getArtistList(Context context) {

        ArrayList<Artist> itemList = new ArrayList();

        ContentResolver musicResolver = context.getContentResolver();
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

        return itemList;
    }

    public ArrayList<Album> getAlbumList(Context context) {

        ArrayList<Album> itemList = new ArrayList();

        ContentResolver musicResolver = context.getContentResolver();
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

        return itemList;
    }
}
