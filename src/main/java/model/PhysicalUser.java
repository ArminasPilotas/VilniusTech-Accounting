package model;


import java.io.Serializable;

public class PhysicalUser extends User implements Serializable {
    private ContactInformation contactInformation;
    private String name;
    private String lastName;

    public PhysicalUser(ContactInformation contactInformation, String name, String lastName) {
        this.contactInformation = contactInformation;
        this.name = name;
        this.lastName = lastName;
    }

    public PhysicalUser() {
    }

    public ContactInformation getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(ContactInformation contactInformation) {
        this.contactInformation = contactInformation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Physical User "+ name + " " + lastName;
    }
}
