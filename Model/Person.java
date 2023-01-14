package Model;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

public abstract class Person implements Serializable {

    @Serial
    private static final long serialVersionUID = 8405575017400263966L;
    private static String[] Blood={"A+", "B+", "AB+", "O+","A-", "B-", "AB-", "O-"};
    private String firstName;
    private String surname;
    private String password;
    private String personalIdNumber;
    private String email;
    private String contactNumber;
    private String city;
    private String street;
    private int houseNumber;
    private LocalDate birthdate;
    private char gender;

    public Person() {
        this.firstName = "";
        this.surname = "";
        this.password = "";
        this.personalIdNumber = "";
        this.email = "";
        this.contactNumber = "";
        this.city = "";
        this.street = "";
        this.houseNumber = 0;
        this.birthdate = LocalDate.of(2000, 1, 1);
        this.gender = 'x';
    }

    public Person(String firstName, String surname, String password, String personalIdNumber, String email, String contactNumber, String city, String street, int houseNumber, LocalDate birthdate, char gender) {
        this.firstName = firstName;
        this.surname = surname;
        this.password = password;
        this.personalIdNumber = personalIdNumber;
        this.email = email;
        this.contactNumber = contactNumber;
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
        this.birthdate = birthdate;
        this.gender = gender;
    }

    public static String[] getBlood() {
        return Blood;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPersonalIdNumber() {
        return personalIdNumber;
    }

    public void setPersonalIdNumber(String personalIdNumber) {
        this.personalIdNumber = personalIdNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "first name: " + firstName +
                ", surname: " + surname +
                ", password: " + password +
                ", personal id number: " + personalIdNumber +
                ", email: " + email +
                ", contact number: " + contactNumber +
                ", city: " + city +
                ", street: " + street +
                ", house number: " + houseNumber +
                ", date of birth: " + birthdate +
                ", gender: " + gender;
    }
}