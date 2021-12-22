package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

public class LoginScreen implements Initializable {
    @FXML Text loginScreenLabel;
    @FXML TextField usernameTextField;
    @FXML PasswordField passwordField;
    @FXML Text usernameLabel;
    @FXML Text passwordLabel;
    @FXML Button logInButton;
    @FXML Label userLocationLabel;
    @FXML Text invalidUsernamePasswordLabel;
    private String invalidLoginMessage = "Invalid username or password";
    private boolean validLogin = true;

    /**
     * This method translates all the labels to French if the users Language is set to french
     * The current location label is also set with this method
     */
    public void checkLanguage() {
        ZoneId userZone = ZoneId.systemDefault();
        userLocationLabel.setText("Current Location: " + userZone);
        Locale France = new Locale("fr", "FR");
        ResourceBundle rb = ResourceBundle.getBundle("controller/language", Locale.FRENCH);
        if(Locale.getDefault().getLanguage().equals("fr")) {
            Locale.setDefault(France);
            loginScreenLabel.setText((rb.getString("Login,Screen")).replaceAll(",", " "));
            usernameLabel.setText((rb.getString("Username")).replaceAll(",", " "));
            passwordLabel.setText(rb.getString("Password").replaceAll(",", " "));
            usernameTextField.setPromptText((rb.getString("Username")).replaceAll(",", " "));
            passwordField.setPromptText(rb.getString("Password").replaceAll(",", " "));
            logInButton.setText(rb.getString("Login"));
            int indexOfSeparation = (userZone.toString()).indexOf("/");
            String countryText = (userZone.toString()).substring(0, indexOfSeparation);
            String countryToPrintFR;
            if (countryText.equals("Pacific") || countryText.equals("America") || countryText.equals("Europe")) {
                countryToPrintFR = (rb.getString(countryText));
                int index = (userZone.toString()).length();
                String locationText = (userZone.toString()).substring(indexOfSeparation, index);
                userLocationLabel.setText((rb.getString("Current,Location")).replaceAll(",", " ") +
                        ": " + countryToPrintFR + locationText
                );
            }
            else {
                userLocationLabel.setText((rb.getString("Current,Location")).replaceAll(",", " ") +
                        ": " + userZone);
            }
            invalidLoginMessage = rb.getString("Invalid,username,or,password").replaceAll(",", " ");
        }
    }

    /**
     * @param event this method checks if login information is valid
     * @throws IOException if exception has occurred
     * @throws SQLException if exception has occurred
     */
    public void verifyLogin(ActionEvent event) throws IOException, SQLException {
        String username = usernameTextField.getText();
        String password = passwordField.getText();
        if (!username.equals("test") || !password.equals("test")) {
            invalidUsernamePasswordLabel.setText(invalidLoginMessage);
            invalidUsernamePasswordLabel.setVisible(true);
            validLogin = false;
        }
        else {
            Parent root = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 800, 600));
            stage.show();
            validLogin = true;
        }
        logActivity();
    }

    /**
     * This method writes login activity to a file
     * @throws IOException if exception has occurred
     */
    public void logActivity() throws IOException {
        FileWriter fileWriter = new FileWriter("login_activity.txt", true);
        PrintWriter outputFile = new PrintWriter(fileWriter);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        if (validLogin) {
            outputFile.print("Successful Login: Username:" + usernameTextField.getText() + " | Password:" +
                    passwordField.getText() + " | Timestamp: " + simpleDateFormat.format(date) + "\n");
        }
        else {
            outputFile.print("Unsuccessful Login: Username:" + usernameTextField.getText() + " | Password:" +
                    passwordField.getText() + " | Timestamp: " + simpleDateFormat.format(date) + "\n");
        }
        outputFile.close();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        checkLanguage();
    }
}
