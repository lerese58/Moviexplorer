package app.gui;

import app.entities.Film;
import app.entities.Person;
import app.enums.Genre;
import app.enums.Role;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class FilmInfoController {
    @FXML
    WebView webView;
    @FXML
    ImageView pic;
    @FXML
    Button cancelBtn, watchBtn, likeBtn, wantBtn;

    private Context _context = Context.getInstance();

    @FXML
    private void initialize() {
        if (_context.getCurrentUser() == null) {
            watchBtn.setVisible(false);
            wantBtn.setVisible(false);
            likeBtn.setVisible(false);
        }
        Film f = _context.getSelectedFilm();
        Person p = _context.getSelectedPerson();
        String html;
        if (f == null) {
            pic.setImage(new Image("resources/defaultIcons/defaultActor.jpg"));
            StringBuilder roles = new StringBuilder();
            for (Role role : p.getRoles()) roles.append(role).append(",");
            roles.delete(roles.lastIndexOf(","), roles.length());
            html = String.format("<html>" +
                    "<h2>%s</h2>" +
                    "<tr>Occupation: %s <br></tr>" +
                    "<tr>Date of birth: %s<br></tr>" +
                    "<tr>Place of birth: %s<br></tr>" +
                    "</html>", p.getName(), roles, p.getBirthDay(), p.getCountryFrom());
        } else {
            f.setPic(new ImageView(new Image(Context.relativeFilmDir + f.getTitle() + ".jpg")));
            pic.setImage(f.getPic().getImage());
            StringBuilder genres = new StringBuilder();
            for (Genre genre : f.getGenres()) genres.append(genre).append(",");
            genres.delete(genres.lastIndexOf(","), genres.length());
            html = String.format("<html>" +
                    "<h2>%s</h2>" +
                    "<tr>Rating: %s <br></tr>" +
                    "<tr>Duration: %smins<br></tr>" +
                    "<tr>Country: %s<br></tr>" +
                    "<tr>Rating MPAA: %s<br></tr>" +
                    "<tr>Genres: %s<br></tr>" +
                    "<tr>Premiere: %s</tr>" +
                    "</html>", f.getTitle(), f.getRating(), f.getDurationInMinutes(),f.getCountry(), f.getMpaaRating(),genres, f.getPremiereDate().toString());
        }
        WebEngine webEngine = webView.getEngine();
        webEngine.loadContent(html);
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
