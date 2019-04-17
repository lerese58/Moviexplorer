package app.services;

import app.dao.FilmRepo;
import app.entities.Film;

import java.util.List;

public class FilmService implements Service<Film> {

    private FilmRepo _filmRepository = new FilmRepo();

    @Override
    public List<Film> getAll() {
        return null;
    }

    @Override
    public List<Film> getByName(String pattern) {
        return null;
    }

    @Override
    public boolean create(Film obj) {
        return _filmRepository.create(obj);
    }

    @Override
    public Film getById(long id) {
        return _filmRepository.getById(id);
    }

    @Override
    public boolean delete(Film obj) {
        return _filmRepository.delete(obj);
    }

    @Override
    public boolean update(long id, Film obj) {
        return _filmRepository.update(id, obj);
    }
}
