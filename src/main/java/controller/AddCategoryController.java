package controller;

import hibernate.service.CategoryService;
import model.Category;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.hibernate.Session;
import view.AlertMessage;

import javax.persistence.EntityManagerFactory;
import java.io.IOException;

public class AddCategoryController {
    Stage thisStage;
    private Session session;
    @FXML
    TextField categoryNameTextField;
    @FXML
    Button nextButton;

    public AddCategoryController(Stage stage, Session session) {
        thisStage = stage;
        this.session=session;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Categories.fxml"));

            // Set this class as the controller
            loader.setController(this);

            // Load the scene
            thisStage.setScene(new Scene(loader.load()));

            // Setup the window/stage
            thisStage.setTitle("Manage Categories");

        } catch (IOException e) {
            e.printStackTrace();
        }
        init();
    }

    public void showStage() {
        thisStage.showAndWait();
    }
    @FXML
    public void init(){
        EventHandler<ActionEvent> nextButtonEvent = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                if(isFormValid()) {
                    saveForm();
                    CategoryController categoryController = new CategoryController(thisStage, session);
                    categoryController.showStage();
                }
                new AlertMessage().infoBox("Category text field can't be empty","Error");
            }
        };
        nextButton.setOnAction(nextButtonEvent);
    }
    private boolean isFormValid(){
        if(categoryNameTextField.getText().isEmpty()){
            return false;
        }
        else return true;
    }
    private void saveForm(){
        new CategoryService(session).create(categoryNameTextField.getText(),null,null,null);

    }

}
