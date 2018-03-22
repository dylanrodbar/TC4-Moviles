package com.example.dylanrodbar.toppeliculas;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridLayout;
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
        numberOfMovies = 21;
        initializeMovies();
        getPage();
        GridLayout g = findViewById(R.id.g);
        
        //fillGridLayout();

    }

    public void initializeMovies() {
        for(int i = 0; i < numberOfMovies; i++) {
            Movie movie = new Movie();
            movies.add(movie);
        }
    }

    public void fillGridLayout() {

        /*TableLayout tableLa = findViewById(R.id.tableMovies);
        RelativeLayout r = new RelativeLayout(this);

        for (int i = 0; i < 7; i++) {
            TableRow tRow = new TableRow(this);
            for (int j = 0; j < 3; j++) {
                ImageView imageV = new ImageView(this);
                //imageV.setLayoutParams(params);

                //GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                TableRow.LayoutParams params = new TableRow.LayoutParams();

                //GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                params.height = 100;
                params.width = 100;
                //params.rowSpec = GridLayout.spec(i);
                //params.columnSpec = GridLayout.spec(j);
                //params.rowSpec = GridLayout.spec(i);
                //params.columnSpec = GridLayout.spec(j);

                r.setLayoutParams(params);
                r.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                //imageV.setLayoutParams(params);
                //imageV.setImageResource(R.drawable.ic);
                //imageV.setImageResource(R.drawable.b);
                //gridLa.addView(imageV);
                tRow.addView(r);
                //tRow.addView(imageV);
                //gridLa.addView(imageV);


            }
            //tableLa.addView(tRow);
        }*/
    }



    public void getPage() {
        //final TextView t = findViewById(R.id.textView);
        //t.setText("hiiiii");
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

                    final TextView titleText = findViewById(R.id.txtNameG);
                    final TextView starsText = findViewById(R.id.txtStarsG);
                    final TextView metascoreText = findViewById(R.id.txtMetascore);
                    try {


                        title[0] = doc.title();
                        Elements nameElements = doc.getElementsByClass("lister-item-header");
                        Elements starsElements = doc.getElementsByClass("ratings-imdb-rating");
                        Elements metascoreElements = doc.getElementsByClass("metascore");
                        Elements imageElements = doc.getElementsByClass("loadlate");
                        //Elements imageElements = doc.getElementsByTag("img");

                        for (int i = 0; i < numberOfMovies; i++) {
                            movies.get(i).setTitle(nameElements.get(i).getElementsByTag("a").first().text());
                            movies.get(i).setStars(starsElements.get(i).getElementsByAttribute("data-value").first().text());
                            movies.get(i).setMetascore(metascoreElements.get(i).text());
                            movies.get(i).setImage(imageElements.get(i).attr("loadlate"));
                            //movies.get(i).setMetascore("hi");
                        }
                        //t.setText(e[0].getElementsByAttribute("data-value").first().text());
                        //t.setText(e[0].getElementsByAttribute("data-tconst").first().text());
                        //t.setText(e[0].getElementsByTag("a").first().text());

                        //Elements el1 = doc.getElementsByClass("ratings-imdb-rating");s
                        //Elements el1 = doc.getElementsByClass("metascore");
                        //Elements el1 = doc.getElementsByClass("lister-item-header");

                        //Elements el1 = doc.getElementsByClass("lister-item-image");
                        //Elements el2 = el1.first().getElementsByTag("a");
                        //Elements el3 = el2.first().getElementsByClass("loadlate");


                        Thread.sleep(100);

                    } catch (InterruptedException e) {

                        e.printStackTrace();
                    }


                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            titleText.setText(movies.get(1).getTitle());
                            starsText.setText(movies.get(1).getStars());
                            metascoreText.setText(movies.get(1).getImage());
                            Bitmap bm;
                            ImageView image = findViewById(R.id.movieImageG);
                            Bitmap bitmap = null;
                            new SendHttpRequestTask().execute(movies.get(1).getImage());


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
            ImageView imageView = (ImageView) findViewById(R.id.movieImageG);
            imageView.setImageBitmap(result);
        }
    }



    class Parser extends AsyncTask<Void, Void, String> {


        @Override
        protected String doInBackground(Void... voids) {
            Document doc = null;

            try {
                doc = Jsoup.connect("http://www.imdb.com/list/ls064079588/").get();
                Log.d("hii", "helooooo");
            } catch (IOException e) {
                e.printStackTrace();
            }
            Toast toast1 =
                    Toast.makeText(getApplicationContext(),
                            "hiii", Toast.LENGTH_SHORT);

            toast1.show();



            return "Executed";
        }


        protected void onPostExecute(String s) {

        }

    }




}



