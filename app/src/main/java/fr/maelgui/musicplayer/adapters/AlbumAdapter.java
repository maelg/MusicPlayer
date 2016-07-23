package fr.maelgui.musicplayer.adapters;

import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import fr.maelgui.musicplayer.R;
import fr.maelgui.musicplayer.models.Album;

/**
 * Created by mguillos on 22/07/16.
 */
public class AlbumAdapter extends BaseAdapter {

    private ArrayList<Album> albums;
    private LayoutInflater albumInf;

    public AlbumAdapter(Context c, ArrayList<Album> theAlbums){
        albums = theAlbums;
        albumInf = LayoutInflater.from(c);
    }

    @Override
    public int getCount() {
        return albums.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //map to song layout
        LinearLayout albumLay = (LinearLayout)albumInf.inflate
                (R.layout.item_album, parent, false);
        //get title and artist views
        ImageView albumArtView = (ImageView)albumLay.findViewById(R.id.albumArt);
        TextView albumName = (TextView)albumLay.findViewById(R.id.album_name);
        TextView albumArtist = (TextView)albumLay.findViewById(R.id.album_artist);
        //get song using position
        Album currAlbum = albums.get(position);
        //get title and artist strings
        if(currAlbum.getArt() != null)
            albumArtView.setImageURI(Uri.parse(currAlbum.getArt()));
        else
            albumArtView.setImageResource(R.drawable.default_album);
        albumName.setText(currAlbum.getName());
        albumArtist.setText(currAlbum.getArtist());
        //set position as tag
        albumLay.setTag(position);
        return albumLay;
    }
}
