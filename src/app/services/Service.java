package app.services;

import java.util.List;

public interface Service<T> {

    List<T> getAll();

    List<T> getByName(String pattern);

    boolean create(T obj);

    T getById(long id);

    boolean delete(T obj);

    boolean update(long id, T obj);
}
