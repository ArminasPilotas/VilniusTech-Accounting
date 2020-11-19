package hibernate.service;

import model.City;
import model.Country;
import org.hibernate.Session;

public class CountryService extends Service<Country> {
    public CountryService(Session session) {
        super(session, Country.class);
    }
    public Country create(String countryName,String countryCode){
        return update(new Country(),countryName,countryCode);
    }
    public Country update(Country country,String countryName,String countryCode){
        country.setCountryName(countryName);
        country.setCountryCode(countryCode);
        return update(country);
    }
}
