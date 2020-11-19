package model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Address implements Serializable {
    private City city;
    private Country country;
    private String streetName;
    private String postCode;
    private int id;
    private Set<ContactInformation> contactInformations = new HashSet<>();

    public Set<ContactInformation>  getContactInformations() {
        return contactInformations;
    }

    public void setContactInformations(Set<ContactInformation>  contactInformations) {
        this.contactInformations = contactInformations;
    }

    public Address(City city, Country country, String streetName, String postCode, int id, Set<ContactInformation> contactInformations) {
        this.city = city;
        this.country = country;
        this.streetName = streetName;
        this.postCode = postCode;
        this.id = id;
        this.contactInformations = contactInformations;
    }

    public Address() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public void setCity(City city) {
        this.city = city;
    }
    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public City getCity() {
        return city;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    @Override
    public String toString() {
        return
                "city=" + city +
                ", country=" + country +
                ", streetName='" + streetName + '\'' +
                ", postCode='" + postCode + '\'';
    }
}
