import controller.LoginFormController;
import controller.RegisterFormController;
import javafx.application.Application;
import javafx.stage.Stage;
import model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Launcher extends Application {


    public static void main(String[] args) {
/*
        Configuration cfg =new Configuration().configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = cfg.buildSessionFactory();
        Session session = sessionFactory.openSession();
        EntityManagerFactory emf= session.getEntityManagerFactory();
        createCity(emf,"Poznan");
        createCity(emf,"Kaunas");
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<City> criteriaQuery = criteriaBuilder.createQuery(City.class);
        Root<City> root = criteriaQuery.from(City.class);
        criteriaQuery = criteriaQuery.select(root).where(criteriaBuilder.like(root.get("cityName"),"Kaunas"));



        EntityManager em=emf.createEntityManager();
        TypedQuery<City> typedQuery=em.createQuery(criteriaQuery);

        System.out.println(typedQuery.getResultList());
*/
        launch(args);
        /*Configuration cfg =new Configuration().configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = cfg.buildSessionFactory();
        Session session = sessionFactory.openSession();
        EntityManagerFactory emf= session.getEntityManagerFactory();
        EntityManager em=emf.createEntityManager();
        em.getTransaction().begin();

        City city=new City();
        city.setCityName("labanu");
                city=em.merge(city);

        Country country=new Country();
        country.setCountryCode("kazkoks");
        country.setCountryName("Lithuania");
        country=em.merge(country);

        Address address=new Address();
        address.setCountry(country);
        address.setCity(city);
        address.setPostCode("post code");
        address.setStreetName("some street name");
        address=em.merge(address);

        ContactInformation contactInformation=new ContactInformation();
        contactInformation.setPhoneNumber("5471257");
        contactInformation.setEmail("fsdf@kfsk.co");
        contactInformation.setAddress(address);
        contactInformation=em.merge(contactInformation);

        CompanyUser companyUser=new CompanyUser();
        companyUser.setName("Arminas");
        companyUser.setLastName("blabla");
        companyUser.setContactInformation(contactInformation);
        companyUser.setUsername("Arminas");
        companyUser.setPassword("haha");


        em.persist(companyUser);
        em.getTransaction().commit();
        em.close();

         */
    }

    private static void createCity(EntityManagerFactory emf,String name) {
        EntityManager em= emf.createEntityManager();
        City city=new City();
        city.setCityName(name);
        System.out.println(city.getId());
        em.getTransaction().begin();
        city=em.merge(city); //pasiemam is db
        System.out.println(city.getId());
        em.persist(city); //irasom i db
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Configuration cfg =new Configuration().configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = cfg.buildSessionFactory();
        Session session = sessionFactory.openSession();
        LoginFormController loginFormController=new LoginFormController(new Stage(),session);
        loginFormController.showStage();
    }
}
