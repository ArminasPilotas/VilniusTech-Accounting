package model;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class City implements Serializable {

    private String cityName;
    private int id;
    private Set<Address> addresses = new HashSet<>();

    public int getId() {
        return id;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    public void setId(int id) {
        this.id = id;
    }

    public City(String cityName, int id) {
        this.cityName = cityName;
        this.id = id;
    }

    public City() {
    }

    public City(String cityName, int id, Set<Address> addresses) {
        this.cityName = cityName;
        this.id = id;
        this.addresses = addresses;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public String toString() {
        return
                "cityName='" + cityName + '\'';
    }
}
