package app.gui;

import app.entities.User;
import app.services.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;

public class AuthorisationController {
    @FXML
    TextField login;
    @FXML
    PasswordField password;
    @FXML
    Button submit,
            regIn,
            logOutBtn;
    @FXML
    Label message;

    UserService service = new UserService();
    Context context = Context.getInstance();

    @FXML
    private void initialize() {
        if (context.getCurrentUser() != null) {
            login.setText(context.getCurrentUser().getLogin());
            password.setText(context.getCurrentUser().getPassword());
            submit.setVisible(false);
            regIn.setVisible(false);
            logOutBtn.setVisible(true);
        }
    }

    public void submit(ActionEvent actionEvent) {
        User u = service.getByLoginPassword(login.getText(), password.getText());
        if (u != null) {
            context.setCurrentUser(u);
            ((Stage) submit.getScene().getWindow()).close();
        }
        else {
            login.setText("");
            password.setText("");
            login.setPromptText("Incorrect");
            password.setPromptText("Incorrect");
        }
    }

    public void register(ActionEvent actionEvent) {
        User u = service.getByLogin(login.getText());
        if (u == null) {
            u = new User(login.getText(), password.getText(), LocalDate.now());
            service.create(new User(login.getText(), password.getText(), LocalDate.now()));
            context.setCurrentUser(u);
            ((Stage) submit.getScene().getWindow()).close();
        }
        else {
            login.setText("");
            password.setText("");
            login.setPromptText("Login");
            password.setPromptText("Login");
        }
    }

    public void onLogOut(ActionEvent actionEvent) {
        context.setCurrentUser(null);
        ((Stage) submit.getScene().getWindow()).close();
    }
}
