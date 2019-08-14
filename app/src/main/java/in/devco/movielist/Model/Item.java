package in.devco.movielist.Model;

import java.util.List;

public class Item {
    private String title;
    private String image;
    private float rating;
    private int releaseYear;
    private List<String> genre;

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public float getRating() {
        return rating;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public List<String> getGenre() {
        return genre;
    }
}
