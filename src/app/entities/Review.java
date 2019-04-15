package app.entities;

public class Review {

    private User author;
    private String text;
    private Film film;
    private int likes;
    private int dislikes;
    private int rate;

    public Review(User author, String text, Film film, int likes, int dislikes, int rate) {
        this.author = author;
        this.text = text;
        this.film = film;
        this.likes = likes;
        this.dislikes = dislikes;
        this.rate = rate;
    }

    public User getAuthor() {
        return author;
    }

    public String getText() {
        return text;
    }

    public Film getFilm() {
        return film;
    }

    public int getLikes() {
        return likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public int getRate() {
        return rate;
    }
}
