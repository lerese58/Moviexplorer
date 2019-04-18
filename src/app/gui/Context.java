package app.gui;

import app.entities.Film;
import app.entities.Person;
import app.entities.User;
import javafx.scene.image.ImageView;

import java.util.List;
import java.util.Map;

public class Context {
    private User currentUser;
    private Film selectedFilm;
    private Person selectedPerson;
    private static volatile Context instance;
    private List<Film> rndFilms;
    private String toSearch;
    static final String filmDir = "C:\\Универ\\sem4\\Analysis & Design of UML\\moviexplorer\\src\\resources\\filmPics\\";
    static final String relativeFilmDir = "resources/filmPics/";

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

    public List<Film> getRndFilms() {
        return rndFilms;
    }

    public void setRndFilms(List<Film> rndFilms) {
        this.rndFilms = rndFilms;
    }

    public String getToSearch() {
        return toSearch;
    }

    public void setToSearch(String toSearch) {
        this.toSearch = toSearch;
    }
}
