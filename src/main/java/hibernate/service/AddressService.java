package hibernate.service;

import model.Address;
import model.City;
import model.Country;
import org.hibernate.Session;

public class AddressService extends Service<Address> {
    public AddressService(Session session) {
        super(session, Address.class);
    }
    public Address create(String streetName,String postCode,City city,Country country){
        return update(new Address(),streetName,postCode,city,country);
    }
    public Address update(Address address, String streetName, String postCode, City city, Country country){
        address.setStreetName(streetName);
        address.setPostCode(postCode);
        address.setCity(city);
        address.setCountry(country);
        return update(address);
    }
}
