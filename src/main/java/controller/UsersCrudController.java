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
import javafx.stage.Stage;
import org.hibernate.Session;
import model.CompanyUser;
import view.AlertMessage;

import java.io.IOException;

public class UsersCrudController {
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
    PasswordField passwordTextField;
    @FXML
    Button nextButton;
    private Session session;
    private int index=0;
    private CompanyUser companyUser;
    public UsersCrudController(Stage stage,Session session,CompanyUser companyUser,String status){ //update show status
        this.session=session;
        thisStage=stage;
        this.companyUser=companyUser;
        this.index=index;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RegisterForm.fxml"));

            // Set this class as the controller
            loader.setController(this);

            // Load the scene
            thisStage.setScene(new Scene(loader.load()));

            // Setup the window/stage
            if(status.equals("Update")){
                thisStage.setTitle(status+ " User Form");
                initUpdate();

            }
            else if(status.equals("Show")){
                thisStage.setTitle(status+ " User Form");
                initShow();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public UsersCrudController(Stage stage,Session session){ //add
        this.session=session;
        thisStage=stage;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RegisterForm.fxml"));

            // Set this class as the controller
            loader.setController(this);

            // Load the scene
            thisStage.setScene(new Scene(loader.load()));

            // Setup the window/stage
            thisStage.setTitle("Add User Form");

        } catch (IOException e) {
            e.printStackTrace();
        }
        initAdd();
    }
    public void showStage(){
        thisStage.showAndWait();
    }
    @FXML
    public void initAdd(){
        EventHandler<ActionEvent> nextEvent = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                if(isFormValid()){
                    saveCompanyUser();
                    goToUsers();
                }
                else{
                    new AlertMessage().infoBox("Wrong credentials try again","Error");
                }
            }
        };
        nextButton.setOnAction(nextEvent);
    }
    public void initUpdate(){
        showInfoInFields();
        EventHandler<ActionEvent> nextEvent = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                if(isFormValid()){
                    updateCompanyUser();
                    goToUsers();
                }
                else{
                    new AlertMessage().infoBox("Wrong credentials try again","Error");
                }
            }
        };
        nextButton.setOnAction(nextEvent);
    }
    public void initShow(){
        setNotEditableFields();
        showInfoInFields();
        EventHandler<ActionEvent> nextEvent = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                goToUsers();
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
    private void updateCompanyUser(){
      ContactInformation contactInformation=companyUser.getContactInformation();
      Address address=contactInformation.getAddress();
      City city=address.getCity();
      Country country=address.getCountry();
      new CountryService(session).update(country,countryNameTextField.getText(),countryCodeTextField.getText());
      new CityService(session).update(city,cityNameTextField.getText());
      new AddressService(session).update(address,streetNameTextField.getText(),postCodeTextField.getText(),city,country);
      new ContactInformationService(session).update(contactInformation,emailTextField.getText(),phoneNumberTextField.getText(),address);
      new CompanyUserService(session).update(companyUser,firstNameTextField.getText(),lastNameTextField.getText(),contactInformation,usernameTextField.getText(),passwordTextField.getText());

    }
    private void goToUsers(){
        UsersController usersController=new UsersController(thisStage,session);
        usersController.showStage();
    }
    private void setNotEditableFields(){
        usernameTextField.setEditable(false);
        emailTextField.setEditable(false);
        phoneNumberTextField.setEditable(false);
        countryNameTextField.setEditable(false);
        countryCodeTextField.setEditable(false);
        cityNameTextField.setEditable(false);
        streetNameTextField.setEditable(false);
        postCodeTextField.setEditable(false);
        passwordTextField.setEditable(false);
        firstNameTextField.setEditable(false);
        lastNameTextField.setEditable(false);
    }

    private void showInfoInFields(){
        ContactInformation contactInformation=companyUser.getContactInformation();
        Address address=contactInformation.getAddress();
        Country country=address.getCountry();
        City city=address.getCity();

        usernameTextField.setText(companyUser.getUsername());
        emailTextField.setText(contactInformation.getEmail());
        phoneNumberTextField.setText(contactInformation.getPhoneNumber());
        countryNameTextField.setText(country.getCountryName());
        countryCodeTextField.setText(country.getCountryCode());
        cityNameTextField.setText(city.getCityName());
        streetNameTextField.setText(address.getStreetName());
        postCodeTextField.setText(address.getPostCode());
       passwordTextField.setText(companyUser.getPassword());
       firstNameTextField.setText(companyUser.getName());
       lastNameTextField.setText(companyUser.getLastName());

    }
}
