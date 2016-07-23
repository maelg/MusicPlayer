package fr.maelgui.musicplayer.models;

/**
 * Created by mguillos on 23/07/16.
 */
public class Album {
    private String name;
    private String art;
    private String id;
    private String artist;
    private String nbrTracks;

    public Album(String albumName, String albumArt, String albumArtist) {
        name = albumName;
        art = albumArt;
        //id = albumId;
        artist = albumArtist;
        //nbrTracks = albumNbrTracks;
    }

    public String getNbrTracks() {
        return nbrTracks;
    }

    public String getName() {
        return name;
    }

    public String getArt() {
        return art;
    }

    public String getId() {
        return id;
    }

    public String getArtist() {
        return artist;
    }
}
