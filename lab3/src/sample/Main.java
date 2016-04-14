package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.time.LocalDate;

import org.apache.commons.validator.routines.*;
import org.apache.commons.validator.routines.EmailValidator;

public class Main extends Application {
    private BorderPane layout;
    private TextField usernameField, passwordField, fullNameField, emailField, phoneField;
    private Label emailErrorLabel;

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Lab 04 Solution");

        /* create the registration form */
        GridPane editArea = new GridPane();
        editArea.setPadding(new Insets(10, 10, 10, 10));
        editArea.setVgap(10);
        editArea.setHgap(10);

        Label usernameLabel = new Label("Username:");
        editArea.add(usernameLabel, 0, 0);
        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");
        editArea.add(usernameField, 1, 0);

        Label passwordLabel = new Label("Password:");
        editArea.add(passwordLabel, 0, 1);
        TextField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        editArea.add(passwordField, 1, 1);

        Label fullNameLabel = new Label("Full Name:");
        editArea.add(fullNameLabel, 0, 2);
        TextField fullNameField = new TextField();
        fullNameField.setPromptText("Full Name");
        editArea.add(fullNameField, 1, 2);

        Label emailLabel = new Label("E-Mail:");
        editArea.add(emailLabel, 0, 3);
        TextField emailField = new TextField();
        emailField.setPromptText("E-Mail");
        editArea.add(emailField, 1, 3);
        emailErrorLabel = new Label("");
        editArea.add(emailErrorLabel, 2, 3);

        Label phoneLabel = new Label("Phone #:");
        editArea.add(phoneLabel, 0, 4);
        TextField phoneField = new TextField();
        phoneField.setPromptText("Phone #");
        editArea.add(phoneField, 1, 4);

        Label dobLabel = new Label("Date of Birth:");
        editArea.add(dobLabel, 0, 5);
        DatePicker dateOfBirthPicker = new DatePicker();
        editArea.add(dateOfBirthPicker, 1, 5);

        Button registerButton = new Button("Register");
        registerButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                String username = usernameField.getText();
                String password = passwordField.getText();
                String fullName = fullNameField.getText();
                String email = emailField.getText();
                String phone = phoneField.getText();
                LocalDate dateOfBirth = dateOfBirthPicker.getValue();

                System.out.println("Username:      " + username);
                System.out.println("Password:      " + password);
                System.out.println("Full name:     " + fullName);
                System.out.println("E-Mail:        " + email);
                System.out.println("Phone #:       " + phone);
                System.out.println("Date of birth: " + dateOfBirth);

                EmailValidator emailValidator = EmailValidator.getInstance();
                boolean valid = emailValidator.isValid(email);
                System.out.println("E-Mail valid? " + valid);

                if (!valid) {
                    emailErrorLabel.setText("Invalid E-Mail Address");
                } else {
                    emailErrorLabel.setText("");
                }
            }
        });
        editArea.add(registerButton, 1, 6);

        /* arrange all components in the main user interface */
        layout = new BorderPane();
        layout.setCenter(editArea);

        Scene scene = new Scene(layout, 600, 300);
        primaryStage.setScene(scene);
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
