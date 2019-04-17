package app.dao;

import app.entities.User;

public class UserRepo implements Repository<User> {
    @Override
    public boolean create(User obj) {
        return false;
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
}
