package app.services;

import app.enums.Genre;
import app.enums.RatingMPAA;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class SearchRequest {


    //    private SearchType type;
    private final Pattern strToSearch;
    private final int fromYear;
    private final int toYear;
    private final String country;
    private final RatingMPAA mpaaRating;
    private final Set<Genre> genreSet;

    public static class SearchRequestBuilder {
        private Pattern strPattern = Pattern.compile("");
        private int from = 1900;
        private int to = LocalDate.now().getYear();
        private String cntr = "";
        private RatingMPAA mpaa = RatingMPAA.G;
        private Set<Genre> genres = new HashSet<>();

        public SearchRequestBuilder withStrToSearch(Pattern pattern) {
            this.strPattern = pattern;
            return this;
        }
        public SearchRequestBuilder withYearAfter(int after) {
            this.from = after;
            return this;
        }
        public SearchRequestBuilder withYearBefore(int before) {
            this.from = before;
            return this;
        }
        public SearchRequestBuilder withCountry(String country) {
            this.cntr = country;
            return this;
        }
        public SearchRequestBuilder withMPAA(RatingMPAA mmpa) {
            this.mpaa = mmpa;
            return this;
        }
        public SearchRequestBuilder withGenre(Genre genre) {
            this.genres.add(genre);
            return this;
        }

        public SearchRequest build() {
            return new SearchRequest(this);
        }
    }

    private SearchRequest(SearchRequestBuilder builder) {
        strToSearch = builder.strPattern;
        fromYear = builder.from;
        toYear = builder.to;
        country = builder.cntr;
        mpaaRating = builder.mpaa;
        genreSet = builder.genres;
    }

    public Pattern getStrToSearch() {
        return strToSearch;
    }

    public int getFromYear() {
        return fromYear;
    }

    public int getToYear() {
        return toYear;
    }

    public String getCountry() {
        return country;
    }

    public RatingMPAA getMpaaRating() {
        return mpaaRating;
    }

    public Set<Genre> getGenreSet() {
        return genreSet;
    }

}
