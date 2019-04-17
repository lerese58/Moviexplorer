package app.dao.repositories;

import app.dao.repositories.Repository;
import app.entities.User;

import java.util.List;

public class UserRepo implements Repository<User> {
    @Override
    public boolean create(User obj) {
        return true;
    }

    @Override
    public User getById(long id) {
        return null;
    }

    @Override
    public boolean delete(User obj) {
        return false;
    }

    @Override
    public boolean update(long id, User obj) {
        return false;
    }

    @Override
    public List<User> getAll() {
        return null;
    }
}
