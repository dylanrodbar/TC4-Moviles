package com.example.dylanrodbar.toppeliculas;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast toast1 =
                Toast.makeText(getApplicationContext(),
                        "hiiiii", Toast.LENGTH_SHORT);
        getPage();

    }


    public void getPage() {
        final TextView t = findViewById(R.id.textView);
        //t.setText("hiiiii");
        final String[] title = new String[1];
        final Element[] e = new Element[1];
        Runnable r = new Runnable() {
            @Override
            public void run() {
                Document doc;
                try {
                    doc = Jsoup.connect("http://www.imdb.com/list/ls064079588/").get();

                    title[0] = doc.title();
                    //Elements el1 = doc.getElementsByClass("ratings-imdb-rating");
                    //Elements el1 = doc.getElementsByClass("metascore");
                    //Elements el1 = doc.getElementsByClass("lister-item-header");

                    Elements el1 = doc.getElementsByClass("lister-item-image");
                    Elements el2 = el1.first().getElementsByTag("a");
                    Elements el3 = el2.first().getElementsByClass("loadlate");

                    //Element el2 = el1.getElementById("wrapper");
                    //Element el3 = el2.getElementById("root");
                    //Element el4 = el3.getElementById("pagecontent");
                    //Element el5 = el4.getElementById("content-2-wide");
                    //Element el6 = el5.getElementById("main");


                    //e[0] = el1.get(1);
                    e[0] = el3.get(0);
                    //Element el7 = el6.getElementById("article.listo");
                    //Element el8 = el7.getElementById("lister.list.detail.sub-list");
                    //Element el9 = el8.getElementById("lister-list");
                    //e[0] = el9;
                    Thread.sleep(1000);

                }
                catch (IOException e)
                {

                    e.printStackTrace();
                } catch (InterruptedException e) {

                    e.printStackTrace();
                }


                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //t.setText(title[0]);
                            //t.setText(e[0].getElementsByAttribute("data-value").first().text());
                            //t.setText(e[0].getElementsByAttribute("data-tconst").first().text());
                            //t.setText(e[0].getElementsByTag("a").first().text());
                            //t.setText(e[0].attr("src"));
                            String s = e[0].attr("src");
                            t.setText(s);
                            ImageView i = findViewById(R.id.imageView);

                            URL aURL = null;
                            //URL url = new URL(s);
                            Bitmap bitmap = BitmapFactory.decodeFile(s);
                            i.setImageBitmap(bitmap);
                            //InputStream is = url.openStream();
                            //if(aURL != null) {
                            //    Bitmap bitmap = BitmapFactory.decodeStream(aURL.openStream());

                            //}
                            //URLConnection conn = aURL.openConnection();
                            //conn.connect();

                            //InputStream is = conn.getInputStream();
                            //BufferedInputStream bis = new BufferedInputStream(is);
                            //Bitmap bitmap = BitmapFactory.decodeStream(bis);

                            //bis.close();
                            //is.close();


                            /*try {

                                InputStream input = new java.net.URL(s).openStream();
                                //Bitmap bitmap = BitmapFactory.decodeStream(input);
                                //i.setImageBitmap(bitmap);
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }*/
                            try {
                                Thread.sleep(1);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }


        };
        /*Document doc = null;

        try {

            Log.d("hii", "helooooo");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Toast toast1 =
                Toast.makeText(getApplicationContext(),
                        "hiii", Toast.LENGTH_SHORT);

        toast1.show();*/
        Thread te = new Thread(r, "T1");
        te.start();
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



