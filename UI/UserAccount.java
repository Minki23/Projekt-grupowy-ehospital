package UI;

import Model.*;
import Data.*;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Random;

public final class UserAccount {

    private static Patient loggedPatient;
    private static Doctor loggedDoctor;
    private static Admin loggedAdmin;

    static String firstName;
    static String surname;
    static String password;
    static String personalID;
    static String email;
    static String contactNumber;
    static String addressCity;
    static String addressStreet;
    static int addressHouseNumber;
    static LocalDate birthdate;
    static char gender;

    static String bloodGroup;
    static int weight;
    static int height;

    static Specialization specialization;

    private UserAccount() {}

    public static Admin getLoggedAdmin() {
        return loggedAdmin;
    }

    public static Patient getLoggedPatient() {
        return loggedPatient;
    }

    public static Doctor getLoggedDoctor() {
        return loggedDoctor;
    }

    public static void setLoggedPatient(Patient loggedPatient) {
        UserAccount.loggedPatient = loggedPatient;
    }

    public static void setLoggedDoctor(Doctor loggedDoctor) {
        UserAccount.loggedDoctor = loggedDoctor;
    }

    public static void setLoggedAdmin(Admin loggedAdmin) {
        UserAccount.loggedAdmin = loggedAdmin;
    }

    public static void generalRegister() throws InputMismatchException {

        firstName = UserData.enterFirstName();
        surname = UserData.enterSurname();
        password = UserData.enterPassword();
        email = UserData.enterEmail();
        birthdate = UserData.enterBirthdate();
        personalID = UserData.enterPersonalIdNumber();
        contactNumber = UserData.enterContactNumber();
        addressCity =  UserData.enterAddressCity();
        addressStreet =  UserData.enterAddressStreet();
        addressHouseNumber = UserData.enterAddressHouseNumber();
        gender = UserData.enterGender();
    }

    public static void patientRegister() {

        boolean safeCreate;

        System.out.println("\nPATIENT REGISTER\n");
        do {
            safeCreate = false;
            try {
                generalRegister();
                height = UserData.enterHeight();
                weight = UserData.enterWeight();
                bloodGroup = UserData.enterBloodGroup();
            } catch (InputMismatchException InvalidInput) {
                safeCreate = true;
                System.out.println("\nInvalid data. Try again\n");
            }
        } while (safeCreate);

        Connection.getPatients().add(new Patient(firstName, surname, password, personalID, email, contactNumber,
                addressCity, addressStreet, addressHouseNumber, birthdate, gender, bloodGroup, weight, height));
        System.out.println("Account created successfully!");
    }

    public static void patientLogin() {

        System.out.println("\nPATIENT LOGIN\n");

        String personalIDNumber = null;
        String password = null;
        boolean safeLogin;

        do {
            safeLogin = false;

            try {
                personalIDNumber = UserData.enterPersonalIdNumber();
            } catch (InputMismatchException invalidInput) {
                System.out.println("Invalid input - try again.");
                safeLogin = true;
            }
        } while (safeLogin);

        password = getLoginPassword();

        boolean found = false;
        for (int i = 0; i < Connection.getPatients().size(); i++) {
            if (Connection.getPatients().get(i).getPersonalIdNumber().equals(personalIDNumber) &&
                    Connection.getPatients().get(i).getPassword().equals(password)) {
                found = true;
                loggedPatient = Connection.getPatients().get(i);
                break;
            }
        }

        if (found) {
            System.out.println("Access granted");
        } else {
            System.out.println("Access denied! Invalid personal ID number or password ");
        }
    }

    public static void doctorRegister() {

        boolean safeCreate;

        System.out.println("\nDOCTOR REGISTER\n");

        do {
            safeCreate = false;

            try {
                generalRegister();
                specialization = UserData.enterSpecialization();
                } catch (InputMismatchException invalidInput) {
                safeCreate = true;
                System.out.println("\nInvalid data. Try again\n");
            }
        } while (safeCreate);

        Random random = new Random();
        int doctorID = random.nextInt(1000, 9999);

        Connection.getDoctorsRegisterRequests().add(new Doctor(firstName, surname, password, personalID, email,
                contactNumber, addressCity, addressStreet, addressHouseNumber, birthdate, gender, specialization, doctorID));
        System.out.println("Account created successfully!\nYour registration will be considered by an admin, after that you will be able to login");
    }

    public static void doctorLogin() {

        System.out.println("\nDOCTOR LOGIN\n");

        boolean safeLogin;
        int doctorID = getLoginID();
        String password = getLoginPassword();

        boolean found = false;
        for (int i = 0; i < Connection.getDoctors().size(); i++) {
            if (Connection.getDoctors().get(i).getDoctorId() == doctorID &&
                    Connection.getDoctors().get(i).getPassword().equals(password)) {
                found = true;
                loggedDoctor = Connection.getDoctors().get(i);
                break;
            }
        }

        if (found) {
            System.out.println("Access granted");
        } else {
            System.out.println("Access denied!");
        }
    }

    public static void createAdmin(int doctorID, String password) {
        Connection.getAdmins().add(new Admin(doctorID, password));
    }

    public static void adminLogin() {

        System.out.println("\nADMIN LOGIN\n");

        int adminID = getLoginID();
        String password = getLoginPassword();

        boolean found = false;
        for (int i = 0; i < Connection.getAdmins().size(); i++) {
            if (Connection.getAdmins().get(i).getAdminID() == adminID &&
                    Connection.getAdmins().get(i).getPassword().equals(password)) {
                found = true;
                loggedAdmin = Connection.getAdmins().get(i);
                break;
            }
        }

        if (found) {
            System.out.println("Access granted");
        } else {
            System.out.println("Access denied! Invalid ID or password ");
        }
    }

    public static String getLoginPassword() {

        boolean safeLogin;
        String password = null;

        do {
            safeLogin = false;

            try {
                System.out.print("Enter your password: ");
                password = Input.getString();
            } catch (InputMismatchException invalidInput) {
                System.out.println("Invalid input - try again.");
                safeLogin = true;
            }
        } while (safeLogin);
        return password;
    }

    public static int getLoginID() {

        boolean safeLogin;
        int doctorID = 0;

        do {
            safeLogin = false;
            try {
                doctorID = UserData.enterID();
            } catch (InputMismatchException invalidInput) {
                System.out.println("Invalid input - try again.");
                safeLogin = true;
            }
        } while (safeLogin);
        return doctorID;
    }
}