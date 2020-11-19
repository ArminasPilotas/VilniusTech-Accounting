package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.hibernate.Session;

import java.io.IOException;

public class MainPageController {
    @FXML
    TextField loggedUsername;
    @FXML
    Button manageUserButton;
    @FXML
    Button manageIncomesButton;
    @FXML
    Button manageExpensesButton;
    @FXML
    Button manageCategoriesButton;
    @FXML
    Button saveAndExitButton;
    Session session;
    Stage thisStage;

    public MainPageController(Stage stage, Session session) {
        this.session = session;
        thisStage = stage;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainPage.fxml"));

            // Set this class as the controller
            loader.setController(this);

            // Load the scene
            thisStage.setScene(new Scene(loader.load()));

            // Setup the window/stage
            thisStage.setTitle("Main Page");

        } catch (IOException e) {
            e.printStackTrace();
        }
        init();
    }

    public void showStage() {
        thisStage.showAndWait();
    }

    @FXML
    public void init() {
        EventHandler<ActionEvent> manageUserEvent = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                UsersController usersController = new UsersController(thisStage, session);
                usersController.showStage();
            }
        };
        EventHandler<ActionEvent> saveAndExitEvent = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                Platform.exit();
                System.exit(0);
            }
        };

        EventHandler<ActionEvent> manageIncomesEvent = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                IncomesController incomesController = new IncomesController(thisStage, session);
                incomesController.showStage();
            }
        };

        EventHandler<ActionEvent> manegeExpensesEvent = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                ExpensesController expensesController = new ExpensesController(thisStage, session);
                expensesController.showStage();
            }
        };

        EventHandler<ActionEvent> manageCategoriesEvent = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                CategoryController categoryController = new CategoryController(thisStage, session);
                categoryController.showStage();
            }
        };


        manageCategoriesButton.setOnAction(manageCategoriesEvent);
        manageExpensesButton.setOnAction(manegeExpensesEvent);
        manageIncomesButton.setOnAction(manageIncomesEvent);
        manageUserButton.setOnAction(manageUserEvent);
        saveAndExitButton.setOnAction(saveAndExitEvent);
    }


}
