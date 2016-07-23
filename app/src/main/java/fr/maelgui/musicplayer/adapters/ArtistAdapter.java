package fr.maelgui.musicplayer.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import fr.maelgui.musicplayer.R;
import fr.maelgui.musicplayer.models.Artist;

/**
 * Created by mguillos on 22/07/16.
 */
public class ArtistAdapter extends BaseAdapter {

    private ArrayList<Artist> artists;
    private LayoutInflater artistInf;

    public ArtistAdapter(Context c, ArrayList<Artist> theArtists){
        artists = theArtists;
        artistInf = LayoutInflater.from(c);
    }

    @Override
    public int getCount() {
        return artists.size();
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
        LinearLayout artistLay = (LinearLayout)artistInf.inflate
                (R.layout.item_artist, parent, false);
        //get title and artist views
        TextView artistNameView = (TextView)artistLay.findViewById(R.id.artist_name);
        TextView artistNbrTracksView = (TextView)artistLay.findViewById(R.id.artist_nbr_tracks);
        TextView artistNbrAlbumsView = (TextView)artistLay.findViewById(R.id.artist_nbr_albums);
        //get song using position
        Artist currArtist = artists.get(position);
        //get title and artist strings
        artistNameView.setText(currArtist.getName());
        artistNbrAlbumsView.setText(currArtist.getNbrAlbums());
        artistNbrTracksView.setText(currArtist.getNbrTracks());
        //set position as tag
        artistLay.setTag(position);
        return artistLay;
    }
}
