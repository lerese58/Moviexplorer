package app.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class FilmInfoController {
    @FXML
    WebView webView;
    WebEngine _webEngine;
    @FXML
    ImageView filmPic;
    @FXML
    Button cancelBtn, watchBtn, likeBtn, wantBtn;

    Context _context = Context.getInstance();

    @FXML
    private void initialize() {
        if (_context.getCurrentUser() == null) {
            watchBtn.setVisible(false);
            wantBtn.setVisible(false);
            likeBtn.setVisible(false);
        }
        String html = String.format("<html>" +
                "<h2>Title:%s</h2>" +
                "<h4>Rating:%s</h4>" +
                "</html>", _context.getSelectedFilm().getTitle(), _context.getSelectedFilm().getRating());
        _webEngine = webView.getEngine();
        _webEngine.loadContent(html);
        filmPic.setImage(_context.getSelectedFilm().getPic().getImage());
    }


    public void onCancel(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    public void onWantToSee(ActionEvent actionEvent) {
    }

    public void onLike(ActionEvent actionEvent) {

    }
}
