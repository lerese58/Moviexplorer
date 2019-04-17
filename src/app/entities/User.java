package app.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class User {

    private final long userID;
    private String login;
    private String password;
    private final LocalDate registerDate;
    private List<Review> reviews;
    private List<User> friends;
    private FilmCollection likedMovies;
    private FilmCollection watchLater;
    private FilmCollection seen;
    private List<FilmCollection> collections;

    public User(long userID, String login, String password, LocalDate registerDate) {
        this.login = login;
        this.password = password;
        this.registerDate = registerDate;
        reviews = new ArrayList<>();
        friends = new ArrayList<>();
        likedMovies = new FilmCollection(userID,"likedMovies");
        watchLater = new FilmCollection(userID,"watchLater");
        seen = new FilmCollection(userID,"seen");
        collections = new ArrayList<>();
        this.userID = this.hashCode();

    }

    public long getUserID() {
        return userID;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public LocalDate getRegisterDate() {
        return registerDate;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public List<User> getFriends() {
        return friends;
    }

    public FilmCollection getLikedMovies() {
        return likedMovies;
    }

    public FilmCollection getWatchLater() {
        return watchLater;
    }

    public FilmCollection getSeen() {
        return seen;
    }

    public List<FilmCollection> getCollections() {
        return collections;
    }
}
