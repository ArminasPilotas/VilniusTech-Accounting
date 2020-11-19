package controller;

import hibernate.service.CompanyUserService;
import hibernate.service.ExpenseService;
import hibernate.service.ExpenseTypeService;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Expense;
import model.ExpenseType;
import org.hibernate.Session;
import model.CompanyUser;
import view.AlertMessage;

import java.io.IOException;
import java.util.Date;

public class ExpensesCRUD {
    Stage thisStage;
    Session session;
    @FXML
    TextField amountTextField;
    @FXML
    TextField expenseNameTextField;
    @FXML
    TextField expenseCodeTextField;
    @FXML
    ChoiceBox userChoiceBox;
    @FXML
    Button nextButton;
    private Expense expense;

    public ExpensesCRUD(Stage stage, Session session) { //add status
        this.session=session;
        thisStage = stage;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ExpensesForm.fxml"));

            // Set this class as the controller
            loader.setController(this);

            // Load the scene
            thisStage.setScene(new Scene(loader.load()));

            // Setup the window/stage
            thisStage.setTitle("Add Expense Form");

        } catch (IOException e) {
            e.printStackTrace();
        }
        initChoiceBox();
        initAdd();
    }

    public ExpensesCRUD(Stage stage, Session session, Expense expense, String status) { //show update status
        this.session=session;
        thisStage = stage;
        this.expense=expense;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ExpensesForm.fxml"));

            // Set this class as the controller
            loader.setController(this);

            // Load the scene
            thisStage.setScene(new Scene(loader.load()));

            // Setup the window/stage
            if (status.equals("Update")) {
                thisStage.setTitle(status + " Expense Form");
                initUpdate();

            } else if (status.equals("Show")) {
                thisStage.setTitle(status + " Expense Form");
                initShow();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        initChoiceBox();
    }

    private void initUpdate() {
        showInfoInFields();
        EventHandler<ActionEvent> nextEvent = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                if(isFormValid()){
                    updateExpenses();
                    goToExpenses();
                }
                else{
                    new AlertMessage().infoBox("Wrong credentials try again","Error");
                }
            }
        };
        nextButton.setOnAction(nextEvent);
    }

    private void initShow() {
        setNotEditableFields();
        showInfoInFields();

        EventHandler<ActionEvent> nextEvent = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                goToExpenses();
            }
        };
        nextButton.setOnAction(nextEvent);
    }

    private void initAdd() {

        EventHandler<ActionEvent> nextEvent = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                if(isFormValid()){
                    saveExpenses();
                    goToExpenses();
                }
                else{
                    new AlertMessage().infoBox("Wrong credentials try again","Error");
                }
            }
        };
        nextButton.setOnAction(nextEvent);
    }
    private boolean isFormValid(){
        if(expenseNameTextField.getText().isEmpty()||expenseCodeTextField.getText().isEmpty()||amountTextField.getText().isEmpty()
            ||userChoiceBox.getValue()==null){
            return false;
        }
        return true;
    }
    private void updateExpenses(){
        ExpenseType expenseType=expense.getExpenseType();
        new ExpenseTypeService(session).update(expenseType,expenseNameTextField.getText(),expenseCodeTextField.getText());
        new ExpenseService(session).update(expense,(String)userChoiceBox.getSelectionModel().getSelectedItem(),expenseType,amountTextField.getText(),new Date());
    }
    private void saveExpenses(){
        ExpenseTypeService expenseTypeService=new ExpenseTypeService(session);
        ExpenseType expenseType=expenseTypeService.create(expenseNameTextField.getText(),expenseCodeTextField.getText());
        ExpenseService expenseService=new ExpenseService(session);
        expenseService.create((String)userChoiceBox.getSelectionModel().getSelectedItem(),expenseType,amountTextField.getText(),new Date());
    }

    public void showStage() {
        thisStage.showAndWait();
    }
    private void goToExpenses(){
        ExpensesController expensesController=new ExpensesController(thisStage,session);
        expensesController.showStage();
    }
    private void initChoiceBox(){
        int i=0;
        for(CompanyUser companyUser: new CompanyUserService(session).readAll()){
            userChoiceBox.getItems().add(i,companyUser.getName() + " " +companyUser.getLastName());
            i++;
        }
    }
    private void setNotEditableFields(){
        amountTextField.setEditable(false);
        expenseCodeTextField.setEditable(false);
        expenseNameTextField.setEditable(false);
        userChoiceBox.setDisable(true);
        userChoiceBox.setOpacity(1.0d);
    }
    private void showInfoInFields(){
        ExpenseType expenseType=expense.getExpenseType();
        amountTextField.setText(expense.getAmount());
        expenseNameTextField.setText(expenseType.getExpenseName());
        expenseCodeTextField.setText(expenseType.getExpenseCode());
        userChoiceBox.setValue(expense.getName());


    }
}
