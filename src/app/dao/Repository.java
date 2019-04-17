package app.dao;

public interface Repository<T> {

    //typical CRUD

    boolean create(T obj);

    T getById(long id);

    boolean delete(T obj);

    boolean update(long id, T obj);

}
