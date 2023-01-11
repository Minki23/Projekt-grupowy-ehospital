package Model;

import Data.Connection;
import Service.Appointment;
import Service.Opinion;
import UI.UserAccount;

import java.util.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Random;

public class Doctor extends Person implements Serializable {

    private Specialization specialization;
    private int doctorId;
    private ArrayList<Opinion> opinions = new ArrayList<>();

    private ArrayList<Appointment> doctorAppointments= new ArrayList<>();
    private double averageOpinion;

    public Doctor() {
        super();
        this.specialization = null;
        this.doctorId = 0;
        this.opinions = null;
        this.averageOpinion = 0.0;
    }

    public Doctor(String firstName, String surname, String password, String personalIdNumber, String email, String contactNumber, String city, String street, int houseNumber, LocalDate birthdate, char gender, Specialization specialization, int doctorId) {
        super(firstName, surname, password, personalIdNumber, email, contactNumber, city, street, houseNumber, birthdate, gender);
        this.specialization = specialization;
        this.doctorId = doctorId;

        Random random = new Random();
        for (int i = 1; i <= 10; i++) {
            int value = random.nextInt(10) + 1;
            String content = "Opinion " + i;
            opinions.add(new Opinion(value, content));
        }
        this.averageOpinion = this.calculateAverageOpinion();
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

    public ArrayList<Appointment>getDoctorAppointments(){
        return doctorAppointments;
    }

    public ArrayList<Opinion> getOpinions() {
        return opinions;
    }

    public void setOpinions(ArrayList<Opinion> opinions) {
        this.opinions = opinions;
    }

    public double getAverageOpinion() {
        return averageOpinion;
    }

    public void setAverageOpinion(double averageOpinion) {
        this.averageOpinion = averageOpinion;
    }

    @Override
    public String toString() {
        return "Doctor [" +
                super.toString() +
                ", doctor id: " + doctorId + ", " +
                "specialization: " + specialization + ", " +
                "average opinion: " + averageOpinion +
                ']';
    }

    public double calculateAverageOpinion() {
        double sum = 0.0;
        for (Opinion opinion : opinions) {
            sum += opinion.getValue();
        }
        return Math.round(sum / opinions.size() * 100) / 100.0;
    }

    public static void generateDoctors(int quantity) {
        Random random = new Random();
        String[] firstnames = {"Tom", "James", "Adam", "John", "Michael", "Francis", "Charles", "Will", "Robert"};
        String[] surnames = {"Smith", "Jones", "Williams", "Taylor", "Brown", "Thomas"};
        String[] streets = {"Long", "Short", "Blue", "Main", "Church", "High", "Park"};
        String[] cities = {"New York", "Washington", "Miami", "Boston", "Atlantic City", "Richmond"};
        Specialization[] specializations = {new Specialization("family doctor"), new Specialization("heart specialist"),
                new Specialization("orthopedist"), new Specialization("gynecologist")};

        String firstname;
        String surname;
        String password = "12345678";
        String personalIDNumber;
        String email;
        String contactNumber;
        String city;
        String street;
        int houseNumber;
        LocalDate birthdate;
        char gender = 'M';
        Specialization specialization;
        int doctorId;

        for (int i = 0; i < quantity; i++) {
            firstname = firstnames[random.nextInt(firstnames.length)];
            surname = surnames[random.nextInt(surnames.length)];
            personalIDNumber = Long.toString(random.nextLong(10000000000L, 100000000000L));
            email = firstname.substring(0, 3).toLowerCase() + surname.substring(0, 4).toLowerCase() + random.nextInt(10, 100) + "@gmail.com";
            contactNumber = Integer.toString(random.nextInt(100000000, 1000000000));
            city = cities[random.nextInt(cities.length)];
            street = streets[random.nextInt(streets.length)];
            houseNumber = random.nextInt(100);
            birthdate = LocalDate.parse(random.nextInt(1960, 2000) + "-0" + random.nextInt(1, 10) + "-" + random.nextInt(10, 29));
            specialization = specializations[random.nextInt(specializations.length)];
            doctorId = random.nextInt(1000, 10000);

            Connection.getDoctors().add(new Doctor(firstname, surname, password, personalIDNumber, email, contactNumber, city, street, houseNumber, birthdate, gender, specialization, doctorId));
        }
    }
}