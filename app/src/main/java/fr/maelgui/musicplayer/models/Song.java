package fr.maelgui.musicplayer.models;

/**
 * Created by mguillos on 22/07/16.
 */
public class Song extends Item {
    private long id;
    private String title;
    private String artist;

    public Song(long songID, String songTitle, String songArtist) {
        id = songID;
        title = songTitle;
        artist = songArtist;
    }

    public long getID(){return id;}
    public String getTitle(){return title;}
    public String getArtist(){return artist;}
}
