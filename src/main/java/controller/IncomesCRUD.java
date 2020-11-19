package controller;

import hibernate.service.CompanyUserService;
import hibernate.service.IncomeTypeService;
import hibernate.service.IncomesService;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Income;
import model.IncomeType;
import org.hibernate.Session;
import model.CompanyUser;
import view.AlertMessage;

import java.io.IOException;
import java.util.Date;

public class IncomesCRUD {
    Stage thisStage;
    Session session;
    Income income;
    @FXML
    TextField amountTextField;
    @FXML
    TextField incomeNameTextField;
    @FXML
    TextField incomeCodeTextField;
    @FXML
    ChoiceBox userChoiceBox;
    @FXML
    Button nextButton;

    public IncomesCRUD(Stage stage, Session session) { //add status
        this.session=session;
        thisStage = stage;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/IncomeForm.fxml"));

            // Set this class as the controller
            loader.setController(this);

            // Load the scene
            thisStage.setScene(new Scene(loader.load()));

            // Setup the window/stage
            thisStage.setTitle("Add Income Form");

        } catch (IOException e) {
            e.printStackTrace();
        }
        initChoiceBox();
        initAdd();
    }

    public IncomesCRUD(Stage stage, Session session, Income income, String status) { //show update status
        this.session=session;
        thisStage = stage;
        this.income=income;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/IncomeForm.fxml"));

            // Set this class as the controller
            loader.setController(this);

            // Load the scene
            thisStage.setScene(new Scene(loader.load()));

            // Setup the window/stage
            if (status.equals("Update")) {
                thisStage.setTitle(status + " Income Form");
                initUpdate();

            } else if (status.equals("Show")) {
                thisStage.setTitle(status + " Income Form");
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
                    updateIncomes();
                    goToIncomes();
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
                goToIncomes();
            }
        };
        nextButton.setOnAction(nextEvent);
    }

    private void initAdd() {
        EventHandler<ActionEvent> nextEvent = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                if(isFormValid()){
                    saveIncomes();
                    goToIncomes();
                }
                else{
                    new AlertMessage().infoBox("Wrong credentials try again","Error");
                }
            }
        };
        nextButton.setOnAction(nextEvent);
    }
    private boolean isFormValid(){
        if(incomeNameTextField.getText().isEmpty()||incomeCodeTextField.getText().isEmpty()||amountTextField.getText().isEmpty()
                ||userChoiceBox.getValue()==null){
            return false;
        }
        return true;
    }
    private void updateIncomes(){
        IncomeType incomeType=income.getIncomeType();
        new IncomeTypeService(session).update(incomeType,incomeNameTextField.getText(),incomeCodeTextField.getText());
        new IncomesService(session).update(income,(String)userChoiceBox.getSelectionModel().getSelectedItem(),incomeType,amountTextField.getText(),new Date());
    }
    private void saveIncomes(){
        IncomeTypeService incomeTypeService=new IncomeTypeService(session);
        IncomeType incomeType=incomeTypeService.create(incomeNameTextField.getText(),incomeCodeTextField.getText());
        IncomesService incomesService=new IncomesService(session);
        incomesService.create((String)userChoiceBox.getSelectionModel().getSelectedItem(),incomeType,amountTextField.getText(),new Date());
    }

    public void showStage() {
        thisStage.showAndWait();
    }
    private void goToIncomes(){
        IncomesController incomesController=new IncomesController(thisStage,session);
        incomesController.showStage();
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
        incomeCodeTextField.setEditable(false);
        incomeNameTextField.setEditable(false);
        userChoiceBox.setDisable(true);
        userChoiceBox.setOpacity(1.0d);
    }
    private void showInfoInFields(){
        IncomeType incomeType=income.getIncomeType();
        amountTextField.setText(income.getAmount());
        incomeNameTextField.setText(incomeType.getIncomeName());
        incomeCodeTextField.setText(incomeType.getIncomeCode());
        userChoiceBox.setValue(income.getName());


    }
}
