package hibernate.service;

import model.City;
import model.CompanyUser;
import model.ContactInformation;
import org.hibernate.Session;

public class CompanyUserService extends Service<CompanyUser> {
    public CompanyUserService(Session session) {
        super(session, CompanyUser.class);
    }
    public CompanyUser create(String name,String lastName,ContactInformation contactInformation,String username,String password){
        return update(new CompanyUser(),name,lastName,contactInformation,username,password);
    }
    public CompanyUser update(CompanyUser companyUser, String name, String lastName,ContactInformation contactInformation ,String username,String password){
        companyUser.setName(name);
        companyUser.setLastName(lastName);
        companyUser.setContactInformation(contactInformation);
        companyUser.setUsername(username);
        companyUser.setPassword(password);
        return update(companyUser);
    }
}
