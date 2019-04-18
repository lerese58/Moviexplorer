package app.gui;

import app.dao.SearchEngine;
import app.entities.Film;
import app.entities.Person;
import app.services.FilmService;
import app.services.PersonService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;

public class SearchResultListController {
    public ListView<Film> filmList;
    public ListView<Person> personList;
    ObservableList<Film> observableFilms = FXCollections.observableArrayList();
    ObservableList<Person> observablePersons = FXCollections.observableArrayList();
    private FilmService _filmService = new FilmService();
    private PersonService _personService = new PersonService();

    Context context = Context.getInstance();
    SearchEngine se = new SearchEngine();

    @FXML
    private void initialize() {
        se.search(context.getToSearch());
        se.getFilms().forEach(id -> observableFilms.add(_filmService.getById(id)));
        se.getPersons().forEach(id -> observablePersons.add(_personService.getById(id)));
        filmList.setItems(observableFilms);
        personList.setItems(observablePersons);
        filmList.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                context.setSelectedPerson(null);
                context.setSelectedFilm(filmList.getSelectionModel().getSelectedItem());
                openInfoDialog();
            }
        });
        personList.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                context.setSelectedFilm(null);
                context.setSelectedPerson(personList.getSelectionModel().getSelectedItem());
                openInfoDialog();
            }
        });
    }

    private void openInfoDialog() {
        Stage stage = new Stage();
        try {
            Parent info = FXMLLoader.load(getClass().getResource("filmInfo.fxml"));
            stage.setScene(new Scene(info));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.show();
    }
}
