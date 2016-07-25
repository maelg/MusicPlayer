package fr.maelgui.musicplayer.adapters;

import android.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import fr.maelgui.musicplayer.R;
import fr.maelgui.musicplayer.models.Artist;

/**
 * Created by mguillos on 22/07/16.
 */
public class ArtistAdapter extends RecyclerView.Adapter<ArtistAdapter.MyViewHolder> {

    private ArrayList<Artist> artists;

    public ArtistAdapter( ArrayList<Artist> artistsList){
        artists = artistsList;
    }

    @Override
    public int getItemCount() {
        return artists.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_artist, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Artist artist = artists.get(position);
        holder.display(artist);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final ImageView artistAlbumArt;
        private final TextView artistName;
        private final TextView artistNbrTracks;
        private final TextView artistNbrAlbums;

        private Artist currArtist;

        public MyViewHolder(final View itemView) {
            super(itemView);

            artistAlbumArt = (ImageView)itemView.findViewById(R.id.albumArt);
            artistName = (TextView)itemView.findViewById(R.id.artist_name);
            artistNbrTracks = (TextView)itemView.findViewById(R.id.artist_nbr_tracks);
            artistNbrAlbums = (TextView)itemView.findViewById(R.id.artist_nbr_albums);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    new AlertDialog.Builder(itemView.getContext())
                            .setTitle(currArtist.getName())
                            .setMessage(currArtist.getNbrAlbums())
                            .show();
                }
            });
        }

        public void display(Artist artist) {
            currArtist = artist;
            artistAlbumArt.setImageResource(R.drawable.default_album);
            artistName.setText(currArtist.getName());
            artistNbrAlbums.setText(currArtist.getNbrAlbums());
            artistNbrTracks.setText(currArtist.getNbrTracks());
        }
    }

}