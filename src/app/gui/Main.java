package app.gui;

import app.dao.repositories.FilmRepo;
import app.dao.repositories.PersonRepo;
import app.dao.SearchEngine;
import app.entities.Film;
import app.entities.Person;
import app.enums.Genre;
import app.enums.MovieType;
import app.enums.RatingMPAA;
import app.enums.Role;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

public class Main extends Application {

    public static void main(String[] args) {
        FilmRepo fr = new FilmRepo();
        PersonRepo pr = new PersonRepo();
        SearchEngine se = new SearchEngine();
        Person tobey = new Person("Tobey Maguire", Set.of(Role.ACTOR, Role.PRODUCER), LocalDate.of(1975, 6,27), "USA");
        Person carey = new Person("Carey Malligun", Set.of(Role.ACTOR), LocalDate.of(1985, 5,28), "UK");
        Person baz = new Person("Baz Luhrmann", Set.of(Role.ACTOR, Role.DIRECTOR, Role.SCREENWRITER, Role.PRODUCER), LocalDate.of(1962, 9,17), "Australia");
        Person dicaprio = new Person("DiCaprio", Set.of(Role.ACTOR), LocalDate.of(1974, 11,11), "USA");
        Person kate = new Person("Kate Winslet", Set.of(Role.ACTOR), LocalDate.of(1975, 10,21), "UK");
        Person lucas = new Person("George Lucas", Set.of(Role.SCREENWRITER, Role.DIRECTOR, Role.PRODUCER), LocalDate.of(1944, 5,14), "USA");
        Person ford = new Person("Harrison Ford", Set.of(Role.ACTOR), LocalDate.of(1942, 7,13), "USA");
        Person stiven = new Person("Steven Spielberg", Set.of(Role.PRODUCER, Role.DIRECTOR, Role.SCREENWRITER), LocalDate.of(1946, 12,18), "USA");
        Person zem = new Person("Robert Zemeckis", Set.of(Role.PRODUCER, Role.DIRECTOR, Role.SCREENWRITER), LocalDate.of(1951, 5,14), "USA");
        pr.create(dicaprio);
        pr.create(tobey);
        pr.create(carey);
        pr.create(baz);
        pr.create(kate);
        pr.create(lucas);
        pr.create(ford);
        pr.create(stiven);
        pr.create(zem);
        Film sw = new Film("Star Wars", MovieType.FULL_LENGTH, 150, Set.of(Genre.FANTASY), Map.of(lucas, Role.DIRECTOR, ford, Role.ACTOR), LocalDate.of(1977, 5, 25), "USA", BigDecimal.valueOf(11000), BigDecimal.valueOf(775398), RatingMPAA.PG, 8.11f, new ImageView());
        Film gatsby = new Film("The great Gatsby", MovieType.FULL_LENGTH, 120, Set.of(Genre.DRAMA), Map.of(dicaprio, Role.ACTOR,tobey, Role.ACTOR, baz, Role.DIRECTOR, carey, Role.ACTOR), LocalDate.of(2013,5,1), "USA", BigDecimal.valueOf(105000), BigDecimal.valueOf(351040), RatingMPAA.PG_13, 7.940f, new ImageView());
        Film tit = new Film("Titanic", MovieType.FULL_LENGTH, 194, Set.of(Genre.DRAMA, Genre.MELODRAMA), Map.of(dicaprio, Role.ACTOR, kate, Role.ACTOR), LocalDate.of(1997,11,1), "USA", BigDecimal.valueOf(200000), BigDecimal.valueOf(2185372), RatingMPAA.PG_13, 8.369f, new ImageView());
        Film indiana = new Film("Indiana Jones and the Last Crusade", MovieType.FULL_LENGTH, 127, Set.of(Genre.FANTASY, Genre.COMEDY), Map.of(lucas, Role.SCREENWRITER, ford, Role.ACTOR, stiven, Role.DIRECTOR), LocalDate.of(2011,5,24), "USA", BigDecimal.valueOf(48000), BigDecimal.valueOf(474172), RatingMPAA.PG_13, 8.066f, new ImageView());
        Film back = new Film("Back to the future", MovieType.FULL_LENGTH, 116, Set.of(Genre.FANTASY, Genre.COMEDY), Map.of(zem, Role.DIRECTOR, stiven, Role.PRODUCER), LocalDate.of(1985,7,3), "USA", BigDecimal.valueOf(19000), BigDecimal.valueOf(381109), RatingMPAA.PG, 8.625f, new ImageView());
        fr.create(gatsby);
        fr.create(tit);
        fr.create(sw);
        fr.create(indiana);
        fr.create(back);
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("start.fxml"));
        primaryStage.setTitle("Moviexplorer");
        primaryStage.setScene(new Scene(root, 800, 540));
        primaryStage.setMinWidth(640);
        primaryStage.setMinHeight(400);
        primaryStage.setOnCloseRequest(event -> System.exit(0));
        primaryStage.show();

//        se.search("a");
//        for (Long id : se.getFilms()) {
//            System.out.println(fr.getById(id));
//        }
//        for (Long id : se.getPersons()) {
//            System.out.println(pr.getById(id));
//        }
//        System.exit(0);
    }
}
