package app.entities;

import java.time.LocalDate;
import java.util.List;

public class User {


    private long userID;
    private String login;
    private String password;
    private LocalDate registerDate;
    private float commentsRate;
    private List<Review> reviews;
    private List<User> friends;
    private List<Film> likedMovies;

    public User(long userID, String login, String password, LocalDate registerDate,
                float commentsRate, List<User> friends, List<Film> likedMovies) {
        this.userID = userID;
        this.login = login;
        this.password = password;
        this.registerDate = registerDate;
        this.commentsRate = commentsRate;
        this.friends = friends;
        this.likedMovies = likedMovies;
    }
}
