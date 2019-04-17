package app.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SearchEngine {
    //todo: use custom ResultList or List<Result> instead of List<Object>

    private AdvConnection conn = AdvConnection.getInstance();
    private List<Long> films = new ArrayList<>();
    private List<Long> persons = new ArrayList<>();

    public void search(String substr) {
        films.clear();
        persons.clear();
        String searchFilm = "SELECT id FROM Film WHERE title like '%" + substr.toLowerCase() + "%'";
        String searchPerson = "SELECT id FROM Person WHERE name like '%" + substr.toLowerCase() + "%'";
        try {
            ResultSet filmSet = conn.resultTransaction(() -> {
                PreparedStatement ps = conn.prepareStatement(searchFilm);
                return ps.executeQuery();
            });
            ResultSet personSet = conn.resultTransaction(() -> {
                PreparedStatement ps = conn.prepareStatement(searchPerson);
                return ps.executeQuery();
            });
            if (filmSet.next()) {
                do {
                    films.add(filmSet.getLong("id"));
                } while (filmSet.next());
            }
            if (personSet.next()) {
                do {
                    persons.add(personSet.getLong("id"));
                } while (personSet.next());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Long> getFilms() {
        return films;
    }

    public List<Long> getPersons() {
        return persons;
    }
}
