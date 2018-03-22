package com.example.dylanrodbar.toppeliculas;

import android.app.Activity;
import android.content.ContentUris;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by dylanrodbar on 21/3/2018.
 */

public class Adapter extends ArrayAdapter<Movie> {

    private final Activity context;
    private ArrayList<Movie> movies = null;

    public Adapter(Activity context, ArrayList<Movie> movies) {
        super(context, R.layout.relative_grid, movies);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.movies = movies;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.relative_grid, null,true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.txtNameG);
        TextView txtStars = (TextView) rowView.findViewById(R.id.txtStarsG);
        TextView txtMetascore = (TextView) rowView.findViewById(R.id.txtMetascoreG);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.movieImageG);

        txtTitle.setText(movies.get(position).getTitle());
        txtStars.setText(movies.get(position).getStars());
        txtMetascore.setText(movies.get(position).getMetascore());





        //Bitmap bm = BitmapFactory.decodeFile(songs.get(position).getPath());
        //imageView.setImageResource(R.drawable.tfm);
        //imageView.setImageBitmap(bm);

        //Uri artworkUri = Uri.parse("content://media/external/audio/albumart");
        //Uri path1 = ContentUris.withAppendedId(artworkUri, songs.get(position).getAlbumId());
        //Glide.with(imageView.getContext()).load(path1).into(imageView);

        //imageView.setImageBitmap(bm);
        return rowView;

    }}

