package Model;

import java.io.Serializable;
import java.time.LocalDate;

public class Patient extends Person implements Serializable {
    private String bloodGroup;
    private int weight;
    private int height;

    public Patient() {
        super();
        this.bloodGroup = "";
        this.weight = 0;
        this.height = 0;
    }

    public Patient(String firstName, String surname, String password, String personalIdNumber, String email, String contactNumber, String city, String street, int houseNumber, LocalDate birthdate, char gender, String bloodGroup, int weight, int height) {
        super(firstName, surname, password, personalIdNumber, email, contactNumber, city, street, houseNumber, birthdate, gender);
        this.bloodGroup = bloodGroup;
        this.weight = weight;
        this.height = height;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return  "Patient [" +
                super.toString() + ", " +
                "bloodGroup: " + bloodGroup +
                ", weight: " + weight +
                ", height: " + height +
                ']';
    }
}