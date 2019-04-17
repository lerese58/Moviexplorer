package app.gui;

import app.entities.Film;
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
                "<h1>%s</h1>"+
                "</html>", _context.getSelectedFilm().getTitle());
        _webEngine = webView.getEngine();
        _webEngine.loadContent(html);
    }
}
