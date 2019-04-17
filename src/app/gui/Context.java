package app.gui;

import app.entities.Film;
import app.entities.Person;
import app.entities.User;

public class Context {
    private User currentUser;
    private Film selectedFilm;
    private Person selectedPerson;
    private static volatile Context instance;

    private Context() {

    }

    public static Context getInstance() {
        Context localInstance = instance;
        if (localInstance == null) {
            synchronized (Context.class) {
                localInstance  = instance;
                if (localInstance == null) {
                    instance = localInstance = new Context();
                }
            }
        }
        return localInstance;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public Film getSelectedFilm() {
        return selectedFilm;
    }

    public void setSelectedFilm(Film selectedFilm) {
        this.selectedFilm = selectedFilm;
    }

    public Person getSelectedPerson() {
        return selectedPerson;
    }

    public void setSelectedPerson(Person selectedPerson) {
        this.selectedPerson = selectedPerson;
    }
}
