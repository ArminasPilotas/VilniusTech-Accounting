package controller;

import hibernate.service.CategoryService;
import hibernate.service.CompanyUserService;
import model.Category;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import model.CompanyUser;
import org.hibernate.Session;
import view.AlertMessage;

import java.io.IOException;

public class AddResponsibleUserController {
    Stage thisStage;
    Category category;
    Session session;
    @FXML
    ChoiceBox responsibleUserChoiceBox;
    @FXML
    Button nextButton;
    public AddResponsibleUserController(Stage stage,Session session,Category category){
        thisStage=stage;
        this.session=session;
        this.category=category;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ResponsibleUsers.fxml"));

            // Set this class as the controller
            loader.setController(this);

            // Load the scene
            thisStage.setScene(new Scene(loader.load()));

            // Setup the window/stage
            thisStage.setTitle("Add Responsible Users");

        } catch (IOException e) {
            e.printStackTrace();
        }
        init();
    }
    public void showStage(){
        thisStage.showAndWait();
    }
    @FXML
    public void init(){

        for(CompanyUser companyUser: new CompanyUserService(session).readAll()){
                responsibleUserChoiceBox.getItems().add(companyUser);
        }
        EventHandler<ActionEvent> nextButtonEvent = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                if(isFormValid()) {
                        saveForm();
                        CategoryController categoryController = new CategoryController(thisStage, session);
                        categoryController.showStage();
                }
                new AlertMessage().infoBox("Responsible person is not chosen, try again","Error");
            }
        };
        nextButton.setOnAction(nextButtonEvent);
    }
    private boolean isFormValid() {
        return !responsibleUserChoiceBox.getSelectionModel().isEmpty();
    }
    private void saveForm(){
        category.getCompanyUsers().add((CompanyUser)responsibleUserChoiceBox.getSelectionModel().getSelectedItem());
        new CategoryService(session).update(category,category.getName(),category.getCompanyUsers(), category.getIncomes(), category.getExpenses());

    }
}
