package app.gui;

import app.dao.SearchEngine;
import app.entities.Film;
import app.gui.Context;
import app.services.FilmService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StartController {
    @FXML
    TextField searchField;
    @FXML
    Button searchBtn;
    @FXML
    ScrollPane scrollPane;
    @FXML
    ImageView view1, view2, view3, view4, view5, loginPic;
    @FXML
    Label filmName1, filmName2, filmName3, filmName4, filmName5;

    ImageView[] listOfImg = new ImageView[]{view1,view2,view3,view4,view5};

    private Stage infoStage;
    private Stage loginStage;
    private Context currentContext;
    private SearchEngine se;
    private FilmService _filmService = new FilmService();
    private ExecutorService _executorService = Executors.newSingleThreadExecutor(Thread::new);
    private Map<Label, ImageView> filmNames = new HashMap<>();
    private Map<Film, ImageView> filmViews = new HashMap<>();
    private final String filmDir = "C:\\Универ\\sem4\\Analysis & Design of UML\\moviexplorer\\src\\resources\\filmPics\\";
    private final String relativeFilmDir = "resources/filmPics/";
    private final Random rnd = new Random();

    public StartController() {
        currentContext = Context.getInstance();
        se = new SearchEngine();
    }

    @FXML
    private void initialize() {
        //generate 5 random films for start view
        List<Film> allFilms = _filmService.getAll();
//        int[] indexes = rnd.ints(allFilms.size(), 0, allFilms.size()).distinct().limit(5).toArray();
//        for (int i : indexes) rndFilms.put(allFilms.get(i), listOfImg[i]);
        view1.setImage(new Image(relativeFilmDir + allFilms.get(0).getTitle() + ".jpg"));
        view2.setImage(new Image(relativeFilmDir + allFilms.get(1).getTitle() + ".jpg"));
        view3.setImage(new Image(relativeFilmDir + allFilms.get(2).getTitle() + ".jpg"));
        view4.setImage(new Image(relativeFilmDir + allFilms.get(3).getTitle() + ".jpg"));
        view5.setImage(new Image(relativeFilmDir + allFilms.get(4).getTitle() + ".jpg"));
        currentContext.setRndFilms(allFilms);
        allFilms.get(0).setPic(view1);
        allFilms.get(1).setPic(view2);
        allFilms.get(2).setPic(view3);
        allFilms.get(3).setPic(view4);
        allFilms.get(4).setPic(view5);

        loginPic.setOnMouseEntered(event -> loginPic.setCursor(Cursor.HAND));
        loginPic.setOnMousePressed(event -> openLoginDialog());

        allFilms.forEach(film -> {
            film.getPic().setOnMousePressed(event -> openInfoDialog(film));
            film.getPic().setOnMouseEntered(event -> film.getPic().setCursor(Cursor.HAND));
        });

        //tunes ScrollPane's behavior
        _executorService.execute(() -> {
            try {
                while (!Thread.currentThread().getState().equals(Thread.State.WAITING)) {
                    if (scrollPane.getHvalue() < 1.0) {
                        Thread.sleep(5000);
                        scrollPane.hvalueProperty().set(scrollPane.getHvalue() + 0.33);
                    } else {
                        Thread.sleep(500);
                        scrollPane.setHvalue(0.0);
                        Thread.sleep(500);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    private void openLoginDialog() {
        try {
            loginStage = new Stage();
            Parent loginParent = FXMLLoader.load(getClass().getResource("Authorisation.fxml"));
            loginStage.setScene(new Scene(loginParent));
            loginStage.setTitle("Login");
        } catch (IOException e) {
            e.printStackTrace();
        }
        loginStage.showAndWait();
    }

    public void doSearch() {
        List<Film> res = new ArrayList<>();
        if (searchValidate(searchField.getText())) {
            se.search(searchField.getText());
            for (Long id : se.getFilms()) res.add(_filmService.getById(id));
        }
        res.forEach(film -> System.out.println(film.getTitle()));
    }

    public void openInfoDialog(Film film) {
        currentContext.setSelectedFilm(film);
        try {
            infoStage = new Stage();
            Parent infoParent = FXMLLoader.load(getClass().getResource("filmInfo.fxml"));
            infoStage.setScene(new Scene(infoParent));
            infoStage.setTitle("INFO");
        } catch (IOException e) {
            e.printStackTrace();
        }
        infoStage.show();
    }

    private boolean searchValidate(String str) {
        return !str.contains("*") && !str.isEmpty();
    }
}
