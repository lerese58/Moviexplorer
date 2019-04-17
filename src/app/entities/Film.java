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
    private final int durationInMinutes;
    private final Set<Genre> genres;
    private final Map<Person, Role> workers;
    private LocalDate premiereDate;
    private final String country;
    private final BigDecimal budget;
    private BigDecimal profit;
    private final RatingMPAA mpaaRating;
    private float rating;
    private ImageView pic;

    //for inserting into db new Film
    public Film(String title, MovieType type, int durationInMinutes, Set<Genre> genres,
                Map<Person, Role> workers, LocalDate premiereDate, String country,
                BigDecimal budget, BigDecimal profit, RatingMPAA mpaaRating, float rating, ImageView pic) {

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
        this.filmID = this.hashCode();
    }

    //for construction Film object from db
    public Film(long id, String title, MovieType type, int durationInMinutes, Set<Genre> genres,
                Map<Person, Role> workers, LocalDate premiereDate, String country,
                BigDecimal budget, BigDecimal profit, RatingMPAA mpaaRating, float rating, ImageView pic) {
        this.filmID = id;
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

    public void setPic(ImageView pic) {
        this.pic = pic;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Film{");
        sb.append("ID=").append(filmID);
        sb.append(", title='").append(title).append('\'');
        sb.append(", type=").append(type);
        sb.append(", dur").append(durationInMinutes);
        sb.append(", genres=").append(genres);
        sb.append(", cast=").append(workers);
        sb.append(", premiere=").append(premiereDate);
        sb.append(", country='").append(country).append('\'');
        sb.append(", budget=").append(budget);
        sb.append(", profit=").append(profit);
        sb.append(", mpaa=").append(mpaaRating);
        sb.append(", rating=").append(rating);
        sb.append('}');
        return sb.toString();
    }
}
