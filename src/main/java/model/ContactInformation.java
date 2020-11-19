package model;

import java.io.Serializable;

public class ContactInformation implements Serializable {
private Address address;
private String email;
private String phoneNumber;

    public ContactInformation(Address address, String email, String phoneNumber, int id) {
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.id = id;
    }

    public ContactInformation() {
    }

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return
                address + ", email='" + email + '\'' + ", phoneNumber='" + phoneNumber + '\'';
    }
}
