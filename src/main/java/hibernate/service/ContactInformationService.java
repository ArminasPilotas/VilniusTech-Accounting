package hibernate.service;

import model.Address;
import model.City;
import model.ContactInformation;
import org.hibernate.Session;

public class ContactInformationService extends Service<ContactInformation> {
    public ContactInformationService(Session session) {
        super(session, ContactInformation.class);
    }
    public ContactInformation create(String email,String phoneNumber,Address address){
        return update(new ContactInformation(),email,phoneNumber,address);
    }
    public ContactInformation update(ContactInformation contactInformation, String email, String phoneNumber, Address address){
        contactInformation.setAddress(address);
        contactInformation.setEmail(email);
        contactInformation.setPhoneNumber(phoneNumber);
        return update(contactInformation);
    }

}
