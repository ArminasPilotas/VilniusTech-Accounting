package controller;

import hibernate.service.CategoryService;
import model.Category;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import model.Expense;
import model.Income;
import model.CompanyUser;
import org.hibernate.Session;
import view.AlertMessage;

import javax.persistence.EntityManagerFactory;
import java.io.IOException;

public class CategoryController {
    Stage thisStage;
    private Session session;
    @FXML
    ListView categoryViewList;
    @FXML
    ListView incomeViewList;
    @FXML
    ListView expenseViewList;
    @FXML
    ListView userViewList;
    @FXML
    Button addCategoryButton;
    @FXML
    Button addUserButton;
    @FXML
    Button addIncomeButton;
    @FXML
    Button addExpenseButton;
    @FXML
    Button mainMenuButton;
    @FXML
    Button deleteCategoryButton;

    public CategoryController(Stage stage, Session session) {
        thisStage = stage;
        this.session=session;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CategoriesTreeView.fxml"));

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
    private void init() {
        categoryViewList.getItems().addAll(new CategoryService(session).readAll());

        categoryViewList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            showResponsibleUsers((Category)newValue);
            showIncomes((Category)newValue);
            showExpenses((Category)newValue);
        });


        EventHandler<ActionEvent> addCategoryEvent = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                AddCategoryController addCategoryController = new AddCategoryController(thisStage, session);
                addCategoryController.showStage();
            }
        };
        EventHandler<ActionEvent> deleteCategoryEvent = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                if(isCategorySelected()){
                   Category category=getSelectedCategory();
                   category.getExpenses().clear();
                   category.getIncomes().clear();
                   category.getCompanyUsers().clear();
                   new CategoryService(session).delete(category);
                    CategoryController categoryController=new CategoryController(thisStage,session);
                    categoryController.showStage();
                }
                new AlertMessage().infoBox("You need to select category first, try again","Error");
            }
        };

        EventHandler<ActionEvent> addResponsibleUserEvent = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                if (isCategorySelected()) {
                    AddResponsibleUserController addResponsibleUserController = new AddResponsibleUserController(thisStage,session,getSelectedCategory());
                    addResponsibleUserController.showStage();
                }
                new AlertMessage().infoBox("You need to choose category first", "Error");
            }
        };

        EventHandler<ActionEvent> addIncomeEvent = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                if (isCategorySelected()) {
                    AddIncomeController addIncomeController = new AddIncomeController(thisStage,session,getSelectedCategory());
                    addIncomeController.showStage();
                }
                new AlertMessage().infoBox("You need to choose category first", "Error");
            }
        };

        EventHandler<ActionEvent> addExpenseEvent = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                if (isCategorySelected()) {
                    AddExpenseController addExpenseController = new AddExpenseController(thisStage, session,getSelectedCategory());
                    addExpenseController.showStage();
                }
                new AlertMessage().infoBox("You need to choose category first", "Error");
            }
        };
        EventHandler<ActionEvent> goToMainMenuEvent = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                MainPageController mainPageController = new MainPageController(thisStage, session);
                mainPageController.showStage();
            }
        };
        addUserButton.setOnAction(addResponsibleUserEvent);
        addCategoryButton.setOnAction(addCategoryEvent);
        addIncomeButton.setOnAction(addIncomeEvent);
        addExpenseButton.setOnAction(addExpenseEvent);
        mainMenuButton.setOnAction(goToMainMenuEvent);
        deleteCategoryButton.setOnAction(deleteCategoryEvent);
    }

    private boolean isCategorySelected() {
        if (categoryViewList.getSelectionModel().isEmpty()) {
            return false;
        }
        return true;
    }
    private Category getSelectedCategory(){
        return (Category)categoryViewList.getSelectionModel().getSelectedItem();
    }
    private void showResponsibleUsers(Category category){
        userViewList.getItems().clear();
        userViewList.getItems().addAll(category.getCompanyUsers());

    }
    private void showIncomes(Category category){
        incomeViewList.getItems().clear();
        incomeViewList.getItems().addAll(category.getIncomes());

    }
    private void showExpenses(Category category){
        expenseViewList.getItems().clear();
        expenseViewList.getItems().addAll(category.getExpenses());

    }

}
