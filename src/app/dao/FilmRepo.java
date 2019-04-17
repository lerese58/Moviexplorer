package app.dao;

import app.entities.Film;

public class FilmRepo implements Repository<Film>{

    @Override
    public boolean create(Film obj) {
        return false;
    }

    @Override
    public Film getById(long id) {
        return null;
    }

    @Override
    public boolean delete(Film obj) {
        return false;
    }

    @Override
    public boolean update(long id, Film obj) {
        return false;
    }
}
