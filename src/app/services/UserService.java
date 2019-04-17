package app.services;

import app.dao.repositories.UserRepo;
import app.entities.User;

import java.util.List;

public class UserService implements Service<User> {
    UserRepo _userRepo = new UserRepo();
    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public List<User> getByName(String pattern) {
        return null;
    }

    @Override
    public boolean create(User obj) {
        return _userRepo.create(obj);
    }

    @Override
    public User getById(long id) {
        return _userRepo.getById(id);
    }

    @Override
    public boolean delete(User obj) {
        return _userRepo.delete(obj);
    }

    @Override
    public boolean update(long id, User obj) {
        return _userRepo.update(id, obj);
    }
}
