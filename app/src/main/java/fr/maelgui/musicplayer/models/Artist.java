package fr.maelgui.musicplayer.models;

/**
 * Created by mguillos on 22/07/16.
 */
public class Artist extends Item {
    private String name;
    private String nbrAlbums;
    private String nbrTracks;

    public Artist(String artistName, String artistNbrAlbums, String ArtistNbrTracks) {
        name = artistName;
        nbrAlbums = artistNbrAlbums;
        nbrTracks = ArtistNbrTracks;
    }

    public String getName(){return name;}
    public String getNbrAlbums(){return nbrAlbums;}
    public String getNbrTracks(){return nbrTracks;}

    @Override
    public String getTitle() {
        return name;
    }
}
