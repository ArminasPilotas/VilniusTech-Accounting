package controller;

import hibernate.service.*;
import model.Address;
import model.City;
import model.ContactInformation;
import model.Country;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.CompanyUser;
import model.PhysicalUser;
import org.hibernate.Session;
import view.AlertMessage;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.io.IOException;

public class RegisterFormController {
    private Stage thisStage;
    @FXML
    TextField firstNameTextField;
    @FXML
    TextField lastNameTextField;
    @FXML
    TextField usernameTextField;
    @FXML
    TextField emailTextField;
    @FXML
    TextField phoneNumberTextField;
    @FXML
    public TextField countryNameTextField;
    @FXML
    public TextField countryCodeTextField;
    @FXML
    TextField cityNameTextField;
    @FXML
    TextField streetNameTextField;
    @FXML
    public TextField postCodeTextField;
    @FXML
    Button nextButton;
    @FXML
    public Button registerPhysicalUserButton;
    @FXML
    PasswordField passwordTextField;
    Session session;

    public RegisterFormController(Stage stage,Session session) {
        this.session=session;
        thisStage=stage;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RegisterForm.fxml"));

            // Set this class as the controller
            loader.setController(this);

            // Load the scene
            thisStage.setScene(new Scene(loader.load()));

            // Setup the window/stage
            thisStage.setTitle("Register Form");

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
        EventHandler<ActionEvent> nextEvent = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                if(isFormValid()){
                    saveCompanyUser();
                    goToLogin();
                }
                else{
                    new AlertMessage().infoBox("Wrong credentials try again","Error");
                }
            }
        };
        nextButton.setOnAction(nextEvent);


    }
    public boolean isFormValid(){
        if(usernameTextField.getText().isEmpty()||emailTextField.getText().isEmpty()||phoneNumberTextField.getText().isEmpty()||
        countryNameTextField.getText().isEmpty()||countryCodeTextField.getText().isEmpty()||cityNameTextField.getText().isEmpty()||
        streetNameTextField.getText().isEmpty()||postCodeTextField.getText().isEmpty()||passwordTextField.getText().isEmpty()||
        firstNameTextField.getText().isEmpty()||lastNameTextField.getText().isEmpty()){
            return false;
        }
        return true;
    }
    private void saveCompanyUser(){
        CityService cityService=new CityService(session);
        City city=cityService.create(cityNameTextField.getText());

        CountryService countryService=new CountryService(session);
        Country country=countryService.create(countryNameTextField.getText(),countryCodeTextField.getText());

        AddressService addressService=new AddressService(session);
        Address address=addressService.create(streetNameTextField.getText(),postCodeTextField.getText(),city,country);


        ContactInformationService contactInformationService=new ContactInformationService(session);
        ContactInformation contactInformation=contactInformationService.create(emailTextField.getText(),phoneNumberTextField.getText(),address);

        CompanyUserService companyUserService=new CompanyUserService(session);
        CompanyUser companyUser=companyUserService.create(firstNameTextField.getText(),lastNameTextField.getText(),contactInformation,usernameTextField.getText(),passwordTextField.getText());

    }
    private void goToLogin(){
        LoginFormController loginFormController=new LoginFormController(thisStage,session);
        loginFormController.showStage();
    }


}
