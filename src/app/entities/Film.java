package app.entities;

import app.enums.Genre;
import app.enums.MovieType;
import app.enums.RatingMPAA;
import app.enums.Role;
import javafx.scene.image.ImageView;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

public class Film {


    private final long filmID;
    private final String title;
    private final MovieType type;

    public long getFilmID() {
        return filmID;
    }

    public String getTitle() {
        return title;
    }

    public MovieType getType() {
        return type;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public Map<Person, Role> getWorkers() {
        return workers;
    }

    public LocalDate getPremiereDate() {
        return premiereDate;
    }

    public String getCountry() {
        return country;
    }

    public BigDecimal getBudget() {
        return budget;
    }

    public BigDecimal getProfit() {
        return profit;
    }

    public RatingMPAA getMpaaRating() {
        return mpaaRating;
    }

    public float getRating() {
        return rating;
    }

    public ImageView getPic() {
        return pic;
    }

    private final int durationInMinutes;
    private final Set<Genre> genres;
    private final Map<Person, Role> workers;
    private LocalDate premiereDate;
//    private Set<SoundTrack> trackSet;
    private final String country;
    private final BigDecimal budget;
    private BigDecimal profit;
    private final RatingMPAA mpaaRating;
    private float rating;
    private ImageView pic;

    public Film(long filmID, String title, MovieType type, int durationInMinutes, Set<Genre> genres,
                Map<Person, Role> workers, LocalDate premiereDate, /*Set<SoundTrack> trackSet,*/ String country,
                BigDecimal budget, BigDecimal profit, RatingMPAA mpaaRating, float rating, ImageView pic) {
        this.filmID = filmID;
        this.title = title;
        this.type = type;
        this.durationInMinutes = durationInMinutes;
        this.genres = genres;
        this.workers = workers;
        this.premiereDate = premiereDate;
//        this.trackSet = trackSet;
        this.country = country;
        this.budget = budget;
        this.profit = profit;
        this.mpaaRating = mpaaRating;
        this.rating = rating;
        this.pic = pic;
    }
}
