package com.example.dylanrodbar.toppeliculas;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by dylanrodbar on 21/3/2018.
 */

public class Adapter extends ArrayAdapter<Movie> {
    private final Activity context;
    private ArrayList<Movie> movies = null;

    public Adapter(Activity context, ArrayList<Movie> movies) {
        super(context, R.layout.relative_grid, movies);
        this.context = context;
        this.movies = movies;
    }



    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.relative_grid, null,true);


        TextView txtNameG = (TextView) rowView.findViewById(R.id.txtNameG);
        TextView txtStarsG = (TextView) rowView.findViewById(R.id.txtStarsG);
        TextView txtMetascoreG = (TextView) rowView.findViewById(R.id.txtMetascoreG);

        txtNameG.setText(movies.get(position).getTitle());
        txtStarsG.setText("Rating: " + movies.get(position).getStars());
        txtMetascoreG.setText("Metascore: "+ movies.get(position).getMetascore());

        /*URL url = null;
        try {
            url = new URL(movies.get(position).getImage());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/



        /*ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(mThumbIds[position]);*/

        return rowView;
    }




}

