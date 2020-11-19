package controller;

import hibernate.service.ExpenseService;
import hibernate.service.ExpenseTypeService;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import model.Expense;
import model.ExpenseType;
import org.hibernate.Session;

import java.io.IOException;

public class ExpensesController {
    Stage thisStage;
    Session session;
    @FXML
    ListView expensesViewList;
    @FXML
    Button addButton;
    @FXML Button showButton;
    @FXML Button deleteButton;
    @FXML Button updateButton;
    @FXML Button mainMenuButton;

    public ExpensesController(Stage stage,Session session){
        thisStage=stage;
        this.session=session;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ManageExpensesListForm.fxml"));

            // Set this class as the controller
            loader.setController(this);

            // Load the scene
            thisStage.setScene(new Scene(loader.load()));

            // Setup the window/stage
            thisStage.setTitle("Manage Expenses");

        } catch (IOException e) {
            e.printStackTrace();
        }
        init();
    }

    private void init() {
        expensesViewList.getItems().addAll(new ExpenseService(session).readAll());
        EventHandler<ActionEvent> addExpensesEvent = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                ExpensesCRUD expensesCRUD=new ExpensesCRUD(thisStage,session);
                expensesCRUD.showStage();

            }
        };
        EventHandler<ActionEvent> updateExpensesEvent = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                Expense expense=(Expense)expensesViewList.getSelectionModel().getSelectedItem();
                ExpensesCRUD expensesCRUD=new ExpensesCRUD(thisStage,session,expense,"Update");
                expensesCRUD.showStage();
            }
        };
        EventHandler<ActionEvent> deleteExpensesEvent = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                Expense expense=(Expense)expensesViewList.getSelectionModel().getSelectedItem();
                ExpenseType expenseType=expense.getExpenseType();
                new ExpenseService(session).delete(expense);
                new ExpenseTypeService(session).delete(expenseType);
                reloadPage();
            }
        };
        EventHandler<ActionEvent> showExpensesEvent = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                Expense expense=(Expense)expensesViewList.getSelectionModel().getSelectedItem();
                ExpensesCRUD expensesCRUD=new ExpensesCRUD(thisStage,session,expense,"Show");
                expensesCRUD.showStage();
            }
        };
        EventHandler<ActionEvent> backToMenuEvent = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                MainPageController mainPageController=new MainPageController(thisStage,session);
                mainPageController.showStage();
            }
        };
        mainMenuButton.setOnAction(backToMenuEvent);
        updateButton.setOnAction(updateExpensesEvent);
        deleteButton.setOnAction(deleteExpensesEvent);
        addButton.setOnAction(addExpensesEvent);
        showButton.setOnAction(showExpensesEvent);
    }

    public void showStage() {
        thisStage.showAndWait();
    }
    private void reloadPage(){
        ExpensesController expensesController=new ExpensesController(thisStage,session);
        expensesController.showStage();
    }
}
