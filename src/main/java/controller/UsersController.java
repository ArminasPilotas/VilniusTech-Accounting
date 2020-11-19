package controller;

import hibernate.service.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import model.*;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.IOException;

public class UsersController {
    Stage thisStage;
    Session session;
    @FXML
    ListView usersViewList;
    @FXML
    Button addButton;
    @FXML
    Button updateButton;
    @FXML
    Button deleteButton;
    @FXML
    Button showButton;
    @FXML
    Button mainMenuButton;
    public UsersController(Stage stage,Session session){
        thisStage = stage;
        this.session=session;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ManageUsersListForm.fxml"));

            // Set this class as the controller
            loader.setController(this);

            // Load the scene
            thisStage.setScene(new Scene(loader.load()));

            // Setup the window/stage
            thisStage.setTitle("Manage Users");

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

        usersViewList.getItems().addAll(new CompanyUserService(session).readAll());

        EventHandler<ActionEvent> deleteUserEvent = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
               // int index =usersViewList.getSelectionModel().getSelectedIndex();
                CompanyUser companyUser=(CompanyUser)usersViewList.getSelectionModel().getSelectedItem();
                ContactInformation contactInformation=companyUser.getContactInformation();
                Address address=contactInformation.getAddress();
                City city=address.getCity();
                Country country=address.getCountry();
                new CompanyUserService(session).delete(companyUser);
                new ContactInformationService(session).delete(contactInformation);
                new AddressService(session).delete(address);
                new CountryService(session).delete(country);
                new CityService(session).delete(city);
                reloadPage();
            }
        };
        EventHandler<ActionEvent> backToMenuEvent = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                MainPageController mainPageController=new MainPageController(thisStage,session);
                mainPageController.showStage();
            }
        };

        EventHandler<ActionEvent> addUserEvent = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                UsersCrudController usersCrudController=new UsersCrudController(thisStage,session);
                usersCrudController.showStage();

            }
        };

        EventHandler<ActionEvent> updateUserEvent = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                CompanyUser companyUser=(CompanyUser)usersViewList.getSelectionModel().getSelectedItem();
                UsersCrudController usersCrudController=new UsersCrudController(thisStage,session,companyUser,"Update");
                usersCrudController.showStage();
            }
        };
        EventHandler<ActionEvent> showUserEvent = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                CompanyUser companyUser=(CompanyUser)usersViewList.getSelectionModel().getSelectedItem();
                UsersCrudController usersCrudController=new UsersCrudController(thisStage,session,companyUser,"Show");
                usersCrudController.showStage();
            }
        };
        updateButton.setOnAction(updateUserEvent);
        showButton.setOnAction(showUserEvent);
        addButton.setOnAction(addUserEvent);
        mainMenuButton.setOnAction(backToMenuEvent);
        deleteButton.setOnAction(deleteUserEvent);
    }
    public void reloadPage(){
        UsersController usersController=new UsersController(thisStage,session);
        usersController.showStage();
    }


}

