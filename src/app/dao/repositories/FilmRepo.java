package app.dao.repositories;

import app.dao.AdvConnection;
import app.entities.Film;
import app.entities.Person;
import app.enums.Genre;
import app.enums.MovieType;
import app.enums.RatingMPAA;
import app.enums.Role;
import javafx.scene.image.ImageView;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class FilmRepo implements Repository<Film> {

    private AdvConnection conn = AdvConnection.getInstance();
    private PersonRepo pr = new PersonRepo();

    @Override
    public boolean create(Film obj) {
        String filmNew = "INSERT INTO Film values(?,?,?,?,?,?,?,?,?,?,?)";
        String prodNew = "INSERT INTO Production VALUES(?,?,?)";
        try {
            conn.voidTransaction(() -> {
                PreparedStatement createReq = conn.prepareStatement(filmNew);
                createReq.setLong(1, obj.getFilmID());
                createReq.setString(2, obj.getTitle());
                createReq.setString(3, obj.getType().toString());
                createReq.setInt(4, obj.getDurationInMinutes());
                createReq.setString(5, obj.getCountry());
                createReq.setInt(6, obj.getBudget().intValue());
                createReq.setInt(7, obj.getProfit().intValue());
                createReq.setString(8, obj.getMpaaRating().toString());
                createReq.setFloat(9, obj.getRating());
                StringBuilder genres = new StringBuilder();
                for (Genre genre : obj.getGenres()) genres.append(genre.toString()).append("/");
                genres.delete(genres.lastIndexOf("/"), genres.length());
                createReq.setString(10, genres.toString());
                createReq.setString(11, obj.getPremiereDate().toString());
                obj.getWorkers().forEach((person, role) -> {
                    try {
                        PreparedStatement prodReq = conn.prepareStatement(prodNew);
                        prodReq.setLong(1, obj.getFilmID());
                        prodReq.setLong(2, person.getPersonID());
                        prodReq.setString(3, role.toString());
                        prodReq.executeUpdate();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });
                return createReq.executeUpdate();
            });
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Film getById(long id) {
        String getFilm = "SELECT * FROM Film WHERE id = ?";
        String getProd = "SELECT * FROM Production WHERE filmID = ?";
        try {
            ResultSet rs = conn.resultTransaction(() -> {
                PreparedStatement get = conn.prepareStatement(getFilm);
                get.setLong(1, id);
                return get.executeQuery();
            });
            ResultSet rsProd = conn.resultTransaction(() -> {
                PreparedStatement prod = conn.prepareStatement(getProd);
                prod.setLong(1, id);
                return prod.executeQuery();
            });
            Map<Person, Role> cast = new HashMap<>();
            if (rsProd.next()) {
                do {
                    cast.put(pr.getById(rsProd.getLong("personID")), Role.valueOf(rsProd.getString("role")));
                } while (rsProd.next());
            }
            String[] genres = rs.getString("genres").split("/");
            Set<Genre> setOfGenres = new HashSet<>();
            for (String str : genres) setOfGenres.add(Genre.valueOf(str));
            if (rs.next()) {
                return new Film(rs.getLong("id"),
                        rs.getString("title"),
                        MovieType.FULL_LENGTH,
                        rs.getInt("durationInMin"),
                        setOfGenres,
                        cast,
                        rs.getString("premiere") != null ?
                                LocalDate.parse(rs.getString("premiere")) : LocalDate.now(),
                        rs.getString("country"),
                        BigDecimal.valueOf(rs.getInt("budget")),
                        BigDecimal.valueOf(rs.getInt("profit")),
                        RatingMPAA.valueOf(rs.getString("ratingMPAA")),
                        rs.getFloat("rating"),
                        new ImageView());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean delete(Film obj) {
        String sql = "DELETE FROM Film WHERE id = ?";
        try {
            conn.voidTransaction(() -> {
                PreparedStatement deleteReq = conn.prepareStatement(sql);
                deleteReq.setLong(1, obj.getFilmID());
                return deleteReq.executeUpdate();
            });
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean update(long id, Film obj) {
        String sql = "UPDATE Film SET title=?,type=?,durationInMin=?,country=?,budget=?,profit=?,ratingMPAA=?,rating=?,genres=?,premiere=? WHERE id=?";
        try {
            conn.voidTransaction(() -> {
                PreparedStatement updateReq = conn.prepareStatement(sql);
                updateReq.setString(1, obj.getTitle());
                updateReq.setString(2, obj.getType().toString());
                updateReq.setInt(3, obj.getDurationInMinutes());
                updateReq.setString(4, obj.getCountry());
                updateReq.setInt(5, obj.getBudget().intValue());
                updateReq.setInt(6, obj.getProfit().intValue());
                updateReq.setString(7, obj.getMpaaRating().toString());
                updateReq.setFloat(8, obj.getRating());
                StringBuilder strOfGenres = new StringBuilder();
                for (Genre genre : obj.getGenres()) strOfGenres.append(genre).append("/");
                updateReq.setString(9, strOfGenres.toString());
                updateReq.setString(10, obj.getPremiereDate().toString());
                updateReq.setLong(11, obj.getFilmID());
                return updateReq.executeUpdate();
            });
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<Film> getAll() {
        List<Film> res = new ArrayList<>();
        String getFilm = "SELECT * FROM Film";
        String getProd = "SELECT * FROM Production WHERE filmID = ?";
        try {
            ResultSet rs = conn.resultTransaction(() -> {
                PreparedStatement get = conn.prepareStatement(getFilm);
                return get.executeQuery();
            });
            if (rs.next()) {
                do {
                    ResultSet rsProd = conn.resultTransaction(() -> {
                        PreparedStatement prod = conn.prepareStatement(getProd);
                        prod.setLong(1, rs.getLong("id"));
                        return prod.executeQuery();
                    });
                    Map<Person, Role> cast = new HashMap<>();
                    if (rsProd.next()) {
                        do {
                            cast.put(pr.getById(rsProd.getLong("personID")), Role.valueOf(rsProd.getString("role")));
                        } while (rsProd.next());
                    }
                    String[] genres = rs.getString("genres").split("/");
                    Set<Genre> setOfGenres = new HashSet<>();
                    for (String str : genres) setOfGenres.add(Genre.valueOf(str));
                    res.add(new Film(rs.getLong("id"),
                            rs.getString("title"),
                            MovieType.valueOf(rs.getString("type")),
                            rs.getInt("durationInMin"),
                            setOfGenres,
                            cast,
                            rs.getString("premiere") != null ?
                                    LocalDate.parse(rs.getString("premiere")) : LocalDate.now(),
                            rs.getString("country"),
                            BigDecimal.valueOf(rs.getInt("budget")),
                            BigDecimal.valueOf(rs.getInt("profit")),
                            RatingMPAA.valueOf(rs.getString("ratingMPAA")),
                            rs.getFloat("rating"),
                            new ImageView()));
                } while (rs.next());
            }
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
