package controller;

import hibernate.service.IncomeTypeService;
import hibernate.service.IncomesService;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import model.Income;
import model.IncomeType;
import org.hibernate.Session;
import model.CompanyUser;

import java.io.IOException;

public class IncomesController {
    Stage thisStage;
    Session session;
    @FXML
    ListView incomesViewList;
    @FXML
    Button addButton;
    @FXML Button showButton;
    @FXML Button deleteButton;
    @FXML Button updateButton;
    @FXML Button mainMenuButton;

    public IncomesController(Stage stage,Session session){
        thisStage=stage;
        this.session=session;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ManageIncomesListForm.fxml"));

            // Set this class as the controller
            loader.setController(this);

            // Load the scene
            thisStage.setScene(new Scene(loader.load()));

            // Setup the window/stage
            thisStage.setTitle("Manage Incomes");

        } catch (IOException e) {
            e.printStackTrace();
        }
        init();
    }

    private void init() {
        incomesViewList.getItems().addAll(new IncomesService(session).readAll());
        EventHandler<ActionEvent> addIncomesEvent = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                IncomesCRUD incomesCRUD=new IncomesCRUD(thisStage,session);
                incomesCRUD.showStage();

            }
        };
        EventHandler<ActionEvent> updateIncomesEvent = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                Income income=(Income)incomesViewList.getSelectionModel().getSelectedItem();
                IncomesCRUD incomesCRUD=new IncomesCRUD(thisStage,session,income,"Update");
                incomesCRUD.showStage();
            }
        };
        EventHandler<ActionEvent> deleteIncomesEvent = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                Income income=(Income)incomesViewList.getSelectionModel().getSelectedItem();
                IncomeType incomeType=income.getIncomeType();
                new IncomesService(session).delete(income);
                new IncomeTypeService(session).delete(incomeType);
                reloadPage();
            }
        };
        EventHandler<ActionEvent> showIncomesEvent = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                Income income=(Income)incomesViewList.getSelectionModel().getSelectedItem();
                IncomesCRUD incomesCRUD=new IncomesCRUD(thisStage,session,income,"Show");
                incomesCRUD.showStage();
            }
        };
        EventHandler<ActionEvent> backToMenuEvent = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                MainPageController mainPageController=new MainPageController(thisStage,session);
                mainPageController.showStage();
            }
        };
        mainMenuButton.setOnAction(backToMenuEvent);
        updateButton.setOnAction(updateIncomesEvent);
        deleteButton.setOnAction(deleteIncomesEvent);
        addButton.setOnAction(addIncomesEvent);
        showButton.setOnAction(showIncomesEvent);
    }

    public void showStage() {
        thisStage.showAndWait();
    }
    private void reloadPage(){
        IncomesController incomesController=new IncomesController(thisStage,session);
        incomesController.showStage();
    }

}
