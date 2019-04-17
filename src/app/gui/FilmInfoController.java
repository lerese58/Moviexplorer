package app.gui;

import app.entities.Film;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class FilmInfoController {
//    @FXML
//    WebView webView;
    WebEngine _webEngine;
    @FXML
    ImageView filmPic;

    Film _film;

    public Film getFilm() {
        return _film;
    }

    public void setFilm(Film film) {
        _film = film;
    }

    @FXML
    private void initialize() {
//        _webEngine = webView.getEngine();
        String html = String.format("<html>" +
                "<h1>%s</h1>" +
                "<h2>%s</h2>" +
                "<tr>%s</tr>" +
                "</html>", _film.getTitle(), _film.getRating(), _film.getDurationInMinutes());
        _webEngine.loadContent(html);
        filmPic.setImage(_film.getPic().getImage());
    }
}
