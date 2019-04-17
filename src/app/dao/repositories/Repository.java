package app.dao.repositories;

import java.util.List;

public interface Repository<T> {

    //typical CRUD + getAll()

    boolean create(T obj);

    T getById(long id);

    boolean delete(T obj);

    boolean update(long id, T obj);

    List<T> getAll();

}
