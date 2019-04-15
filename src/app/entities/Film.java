package app.entities;

import app.enums.Genre;
import app.enums.MovieType;
import app.enums.RatingMPAA;
import app.enums.Role;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

public class Film {


    private long filmID;
    private String title;
    private MovieType type;
    private int durationInMinutes;
    private Set<Genre> genres;
    private Map<Person, Role> workers;
    private LocalDate premiereDate;
//    private Set<SoundTrack> trackSet;
    private String country;
    private BigDecimal budget;
    private BigDecimal profit;
    private RatingMPAA mpaaRating;
    private float rating;

    public Film(long filmID, String title, MovieType type, int durationInMinutes, Set<Genre> genres,
                Map<Person, Role> workers, LocalDate premiereDate, /*Set<SoundTrack> trackSet,*/ String country,
                BigDecimal budget, BigDecimal profit, RatingMPAA mpaaRating, float rating) {
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
    }
}
