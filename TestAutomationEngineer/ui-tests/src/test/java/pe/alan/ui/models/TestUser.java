package pe.alan.ui.models;

public class TestUser {
    public String firstName;
    public String lastName;
    public String address;
    public String city;
    public String state;
    public String zipCode;
    public String phone;
    public String ssn;
    public String username;
    public String password;

    public String fullName() {
        return firstName + " " + lastName;
    }
}
