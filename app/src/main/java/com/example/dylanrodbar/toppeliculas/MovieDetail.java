package com.example.dylanrodbar.toppeliculas;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MovieDetail extends AppCompatActivity {
    private String title;
    private String rating;
    private String metascore;
    private String image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        setActivityValues();
    }


    public void setActivityValues() {
        title = getIntent().getStringExtra("title");
        rating = getIntent().getStringExtra("rating");
        metascore = getIntent().getStringExtra("metascore");
        image = getIntent().getStringExtra("image");
        TextView titleT = findViewById(R.id.titleT);
        TextView ratingT = findViewById(R.id.rating);
        TextView metascoreT = findViewById(R.id.metascore);
        titleT.setText(title);
        ratingT.setText("Rating: " + rating);
        metascoreT.setText("Metascore: " + metascore);
        new SendHttpRequestTask().execute(image);

    }


    private class SendHttpRequestTask extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... params) {
            try {
                URL url = new URL(params[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(input);
                return myBitmap;
            }catch (Exception e){
                //Log.d(e.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            ImageView imageView = (ImageView) findViewById(R.id.imgMovie);
            imageView.setImageBitmap(result);
        }
    }
}
