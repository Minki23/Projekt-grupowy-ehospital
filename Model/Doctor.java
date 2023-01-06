package Model;

import java.io.Serializable;
import java.time.LocalDate;

public class Doctor extends Person implements Serializable {

    private Specialization specialization;
    private int doctorId;

    public Doctor() {
        super();
        this.specialization = null;
        this.doctorId = 0;
    }

    public Doctor(String firstName, String surname, String password, String personalIdNumber, String email, String contactNumber, String city, String street, int houseNumber, LocalDate birthdate, char gender, Specialization specialization, int doctorId) {
        super(firstName, surname, password, personalIdNumber, email, contactNumber, city, street, houseNumber, birthdate, gender);
        this.specialization = specialization;
        this.doctorId = doctorId;
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    @Override
    public String toString() {
        return "Doctor [" +
                super.toString() +
                ", doctor id: " + doctorId + ", " +
                "specialization: " + specialization +
                ']';
    }
}