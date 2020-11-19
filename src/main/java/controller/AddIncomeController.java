package controller;

import hibernate.service.CategoryService;
import hibernate.service.IncomesService;
import model.Category;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import model.Expense;
import model.Income;
import model.CompanyUser;
import org.hibernate.Session;
import view.AlertMessage;

import javax.persistence.EntityManagerFactory;
import java.io.IOException;

public class AddIncomeController {
    Stage thisStage;
    Session session;
    Category category;
    @FXML
    ChoiceBox incomeChoiceBox;
    @FXML
    Button nextButton;

    public AddIncomeController(Stage stage, Session session, Category category) {
        thisStage = stage;
        this.session=session;
        this.category=category;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Incomes.fxml"));

            // Set this class as the controller
            loader.setController(this);

            // Load the scene
            thisStage.setScene(new Scene(loader.load()));

            // Setup the window/stage
            thisStage.setTitle("Add Incomes");

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
        for (Income income:new IncomesService(session).readAll()){
            incomeChoiceBox.getItems().add(income);
        }
        EventHandler<ActionEvent> nextButtonEvent = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                if (isFormValid()) {
                    saveForm();
                    CategoryController categoryController = new CategoryController(thisStage, session);
                    categoryController.showStage();
                }
                new AlertMessage().infoBox("Income name is not chosen, try again", "Error");
            }
        };
        nextButton.setOnAction(nextButtonEvent);
    }

    private boolean isFormValid() {
        return !incomeChoiceBox.getSelectionModel().isEmpty();
    }

    private void saveForm() {
        category.getIncomes().add((Income)incomeChoiceBox.getSelectionModel().getSelectedItem());
        new CategoryService(session).update(category, category.getName(),category.getCompanyUsers(),category.getIncomes(),category.getExpenses());
    }
}
