package model;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class CompanyUser extends User implements Serializable {
    private ContactInformation contactInformation;
    private String name;
    private String lastName;
    private Set<Category> categories = new HashSet<>();


    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }



    public CompanyUser() {

    }

    public CompanyUser(String username, String password, int id, ContactInformation contactInformation, String name, String lastName, Set<Category> categories) {
        super(username, password, id);
        this.contactInformation = contactInformation;
        this.name = name;
        this.lastName = lastName;
        this.categories = categories;
    }

    public CompanyUser(ContactInformation contactInformation, String name, String lastName, Set<Category> categories) {
        this.contactInformation = contactInformation;
        this.name = name;
        this.lastName = lastName;
        this.categories = categories;
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

    public ContactInformation getContactInformation() {
        return contactInformation;
    }

    @Override
    public String toString() {
        return  "Company User "+ name + " " + lastName;
    }
}
