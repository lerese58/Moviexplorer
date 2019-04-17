package app.gui;

import app.entities.Film;
import app.enums.Genre;
import app.enums.MovieType;
import app.enums.RatingMPAA;
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
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
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
    ImageView film1, film2, film3, film4, film5;
    @FXML
    Label filmName1, filmName2, filmName3, filmName4, filmName5;

    private Stage infoStage;
    private Context currentContext;
    private FilmService _filmService = new FilmService();
    private ExecutorService _executorService = Executors.newSingleThreadExecutor(Thread::new);
    private Map<Label, ImageView> films;
    private final String filmDir = "C:\\Универ\\sem4\\Analysis & Design of UML\\moviexplorer\\src\\resources\\filmPics\\";
    private final String relativeFilmDir = "resources/filmPics/";
    private final Random rnd = new Random();

    public StartController() throws IOException {
        currentContext = Context.getInstance();
    }

    @FXML
    private void initialize() {
        films = Map.of(filmName1, film1, filmName2, film2, filmName3, film3, filmName4, film4, filmName5, film5);
        String[] paths = new File(filmDir).list();
        if (paths == null)
            return;
        int[] indexes = rnd.ints(100, 0, paths.length).distinct().limit(films.size()).toArray();
        int i = 0;
        for (Map.Entry<Label, ImageView> entry : films.entrySet()) {
            Label label = entry.getKey();
            ImageView imageView = entry.getValue();
            int index = indexes[i++];
            imageView.setOnMousePressed(event -> {
                if (event.getClickCount() == 2) {
                    openInfoDialog(new Film(1, "Sex on friendship", MovieType.FULL_LENGTH, 112, Set.of(Genre.COMEDY, Genre.DRAMA), Map.of(),
                            LocalDate.of(2016, 5, 5), "Russia", BigDecimal.valueOf(1000000L), BigDecimal.valueOf(2000000L), RatingMPAA.R,
                            4.5f, film1));
                }
            });
            imageView.setOnMouseEntered(event -> imageView.setCursor(Cursor.HAND));
            imageView.setImage(new Image((relativeFilmDir + paths[index])));
            String url = imageView.getImage().getUrl();
            imageView.setId(url.substring(url.lastIndexOf("/") + 1, url.indexOf(".jpg")).replace("%20", " "));
            label.setText(imageView.getId());
            label.setTextFill(Paint.valueOf("#FFFFFF"));
            imageView.setOnMousePressed(event -> searchField.setText(imageView.getId()));
        }
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

    public List<Film> doSearch() {
        openInfoDialog(
                new Film(1, "Billion", MovieType.FULL_LENGTH, 112, Set.of(Genre.COMEDY, Genre.DRAMA), Map.of(),
                        LocalDate.of(2016, 5, 5), "Russia", BigDecimal.valueOf(1000000L), BigDecimal.valueOf(2000000L), RatingMPAA.R,
                        4.5f, film1));
        return Collections.emptyList();
//        String ptrn = searchField.getText();
//        if (searchValidate(ptrn)) {
//            searchField.setText("found!");
//            return _filmService.getByName(ptrn);
//        }
//        return Collections.emptyList();
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
