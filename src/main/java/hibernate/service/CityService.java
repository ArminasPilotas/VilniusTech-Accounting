package hibernate.service;

import model.City;
import org.hibernate.Session;

public class CityService extends Service<City> {

    public CityService(Session session) {
        super(session, City.class);
    }
    public City create(String cityMame){
        return update(new City(),cityMame);
    }
    public City update(City city,String cityMame){
        city.setCityName(cityMame);
        return update(city);
    }


}
