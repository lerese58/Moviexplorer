package app.entities;

import java.util.ArrayList;
import java.util.List;

public class FilmCollection {

    private final Long authorID;
    private String title;
    private List<Film> films;
    private int likes;
    private int dislikes;

    public FilmCollection(Long authorID, String title) {
        this.authorID = authorID;
        this.title = title;
        this.films = new ArrayList<>();
        this.likes = 0;
        this.dislikes = 0;
    }

    public Long getAuthorID() {
        return authorID;
    }

    public String getTitle() {
        return title;
    }

    public List<Film> getFilms() {
        return films;
    }

    public int getLikes() {
        return likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void likeThis() {
        likes++;
    }
    public void dislikeThis() {
        dislikes++;
    }
}
