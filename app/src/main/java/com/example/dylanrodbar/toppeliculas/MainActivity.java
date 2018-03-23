package com.example.dylanrodbar.toppeliculas;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private ArrayList<Movie> movies;
    private int numberOfMovies;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        movies = new ArrayList<>();
        numberOfMovies = 20;
        initializeMovies();
        getPage();
        GridView gridview = (GridView) findViewById(R.id.gridMovies);
        Adapter adapter=new Adapter(this, movies);
        gridview.setAdapter(adapter);

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                //String Slecteditem= itemname[+position];
                //Toast.makeText(getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();
                //openMusicDetail(position);
                sendData(position);

            }
        });
    }


    public void sendData(int position) {
        Intent intent = new Intent(this, MovieDetail.class);
        intent.putExtra("title", movies.get(position).getTitle());
        intent.putExtra("rating", movies.get(position).getStars());
        intent.putExtra("metascore", movies.get(position).getMetascore());
        intent.putExtra("image", movies.get(position).getImage());
        startActivity(intent);
    }

    public void initializeMovies() {
        for(int i = 0; i < numberOfMovies; i++) {
            Movie movie = new Movie();
            movies.add(movie);
        }
    }

    public void fillGridLayout() {


    }



    public void getPage() {
        final String[] title = new String[1];
        final Element[] e = new Element[1];
        Runnable r = new Runnable() {
            @Override
            public void run() {
                Document doc = null;
                try {
                    doc = Jsoup.connect("http://www.imdb.com/list/ls064079588/").get();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                while (true) {

                    try {


                        title[0] = doc.title();
                        Elements nameElements = doc.getElementsByClass("lister-item-header");
                        Elements starsElements = doc.getElementsByClass("ratings-imdb-rating");
                        Elements metascoreElements = doc.getElementsByClass("metascore");
                        Elements imageElements = doc.getElementsByClass("loadlate");

                        for (int i = 0; i < numberOfMovies; i++) {
                            movies.get(i).setTitle(nameElements.get(i).getElementsByTag("a").first().text());
                            movies.get(i).setStars(starsElements.get(i).getElementsByAttribute("data-value").first().text());
                            movies.get(i).setMetascore(metascoreElements.get(i).text());
                            movies.get(i).setImage(imageElements.get(i).attr("loadlate"));
                        }

                        Thread.sleep(100);

                    } catch (InterruptedException e) {

                        e.printStackTrace();
                    }


                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            Bitmap bm;
                            Bitmap bitmap = null;


                            try {
                                Thread.sleep(1);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }


                    });
                }
            }
        };



        Thread te = new Thread(r, "T1");
        te.start();
    }

}



