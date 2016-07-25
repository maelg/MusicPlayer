package fr.maelgui.musicplayer.adapters;

import android.app.AlertDialog;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
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
import fr.maelgui.musicplayer.models.Artist;

/**
 * Created by mguillos on 22/07/16.
 */

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.MyViewHolder> {

    private ArrayList<Album> albums;

    public AlbumAdapter(ArrayList<Album> albumsList) {
        albums = albumsList;
    }

    @Override
    public int getItemCount() {
        return albums.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_album, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Album album = albums.get(position);
        holder.display(album);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final ImageView albumArt;
        private final TextView albumName;
        private final TextView albumArtist;

        private Album currAlbum;

        public MyViewHolder(final View itemView) {
            super(itemView);

            albumArt = (ImageView) itemView.findViewById(R.id.albumArt);
            albumName = (TextView) itemView.findViewById(R.id.album_name);
            albumArtist = (TextView) itemView.findViewById(R.id.album_artist);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    new AlertDialog.Builder(itemView.getContext())
                            .setTitle(currAlbum.getTitle())
                            .setMessage(currAlbum.getArtist())
                            .show();
                }
            });
        }

        public void display(Album album) {
            currAlbum = album;
            if(currAlbum.getArt() != null)
                albumArt.setImageURI(Uri.parse(currAlbum.getArt()));
            else
                albumArt.setImageResource(R.drawable.default_album);
            albumName.setText(album.getTitle());
            albumArtist.setText(album.getArtist());
        }
    }
}