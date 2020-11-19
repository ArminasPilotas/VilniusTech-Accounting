package model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Country implements Serializable {
    private String countryName;
    private String countryCode;
    private int id;
    private Set<Address> addresses = new HashSet<>();

    public Country(String countryName, String countryCode, int id, Set<Address> addresses) {
        this.countryName = countryName;
        this.countryCode = countryCode;
        this.id = id;
        this.addresses = addresses;
    }

    public Country() {
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }


    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    @Override
    public String toString() {
        return
                "countryName='" + countryName + '\'' +
                ", countryCode='" + countryCode + '\'';
    }
}
