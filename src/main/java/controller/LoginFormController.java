package controller;

import javafx.application.Platform;
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
import model.User;
import org.hibernate.Session;
import view.AlertMessage;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.IOException;

public class LoginFormController {

    @FXML
    TextField username;
    private Stage thisStage;
    @FXML
    PasswordField passwordTextField;
    @FXML
    Button loginButton;
    @FXML
    Button registerButton;
    @FXML
    AnchorPane loginAnchorPane;
    private Session session;

    public LoginFormController(Stage stage, Session session) {
        thisStage = stage;
        this.session=session;

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/LoginForm.fxml"));

            // Set this class as the controller
            loader.setController(this);

            // Load the scene
            thisStage.setScene(new Scene(loader.load()));

            // Setup the window/stage
            thisStage.setTitle("Login Form");

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
        EventHandler<ActionEvent> registerEvent = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                RegisterFormController registerFormController = new RegisterFormController(thisStage,session);
                registerFormController.showStage();
            }
        };
        EventHandler<ActionEvent> loginEvent = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                if(validateCompanyUser()){
                    System.out.println("Successfully logged in");
                    MainPageController mainPageController=new MainPageController(thisStage,session);
                    mainPageController.showStage();
                }
                else{
                    new AlertMessage().infoBox("Wrong credentials try again","Error");
                }
            }
        };
        registerButton.setOnAction(registerEvent);
        loginButton.setOnAction(loginEvent);
    }
    private boolean validateCompanyUser() {
        EntityManagerFactory emf= session.getEntityManagerFactory();
        CriteriaBuilder criteriaBuilder=session.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery=criteriaBuilder.createQuery(User.class);
        Root<User> root=criteriaQuery.from(User.class);
        Predicate passwordpredicate=criteriaBuilder.equal(root.get("password"),passwordTextField.getText());
        Predicate usernamepredicate=criteriaBuilder.equal(root.get("username"),username.getText());

        criteriaQuery=criteriaQuery.select(root).where(passwordpredicate,usernamepredicate);

        EntityManager em=emf.createEntityManager();
        TypedQuery<User> typedQuery=em.createQuery(criteriaQuery);
        if(typedQuery.getResultList().isEmpty()) {
            return false;
        }
            return true;
    }
}
