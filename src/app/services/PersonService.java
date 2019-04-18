package app.services;

import app.dao.repositories.PersonRepo;
import app.entities.Person;

import java.util.List;

public class PersonService implements Service<Person> {

    PersonRepo _personRepo = new PersonRepo();

    @Override
    public List<Person> getAll() {
        return _personRepo.getAll();
    }

    @Override
    public List<Person> getByName(String pattern) {
        return null;
    }

    @Override
    public boolean create(Person obj) {
        return _personRepo.create(obj);
    }

    @Override
    public Person getById(long id) {
        return _personRepo.getById(id);
    }

    @Override
    public boolean delete(Person obj) {
        return _personRepo.delete(obj);
    }

    @Override
    public boolean update(long id, Person obj) {
        return _personRepo.update(id, obj);
    }
}
