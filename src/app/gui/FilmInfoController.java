package app.gui;

import app.entities.Film;
import app.gui.Context;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class FilmInfoController {
    @FXML
    WebView webView;
    WebEngine _webEngine;
    @FXML
    ImageView filmPic;

    Context _context = Context.getInstance();

    @FXML
    private void initialize() {
        String html = String.format("<html>" +
                "<h2>Title:%s</h2>"+
                "<h4>Rating:%s</h4>" +
                "</html>", _context.getSelectedFilm().getTitle(), _context.getSelectedFilm().getRating());
        _webEngine = webView.getEngine();
        _webEngine.loadContent(html);
    }
}
