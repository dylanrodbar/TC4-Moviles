package com.example.dylanrodbar.toppeliculas;

/**
 * Created by dylanrodbar on 20/3/2018.
 */

public class Movie {
    private String title;
    private String stars;
    private String metascore;
    private String image;

    Movie() {

    }

    public void setTitle(String title) {
        this.title = title;
    }
    public void setStars(String stars) {
        this.stars = stars;
    }
    public void setMetascore(String metascore) {
        this.metascore = metascore;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public String getTitle() {
        return title;
    }
    public String getStars() {
        return stars;
    }
    public String getMetascore() {
        return metascore;
    }
    public String getImage() {
        return image;
    }
}
