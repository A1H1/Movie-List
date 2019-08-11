package in.devco.movielist;

import java.util.List;

class Item {
    private String title;
    private String image;
    private float rating;
    private int releaseYear;
    private List<String> genre;

    String getTitle() {
        return title;
    }

    String getImage() {
        return image;
    }

    float getRating() {
        return rating;
    }

    int getReleaseYear() {
        return releaseYear;
    }

    List<String> getGenre() {
        return genre;
    }
}
