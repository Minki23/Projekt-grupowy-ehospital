package UI;

import GUI.Menu.PanelLogowanieAdmin;
import GUI.Menu.PanelLogowaniePacjent;
import GUI.Menu.PanelMenu;
import GUI.Menu.PanelRejestracja;
import GUI.PanelAdmina.PanelAdmin;
import GUI.Skladowe.Powierzchnia;
import Model.*;
import Data.*;

import java.awt.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;

public final class UserAccount {

    public static String[] getBloodGroups() {
        return bloodGroups;
    }

    private static String[] bloodGroups = {"Choose your blood type", "A+", "A-", "B+", "B-", "0+", "0-", "AB+", "AB-"};
    private static String[] spetiality = {"Choose your spetiality", "family doctor", "heart specialist", "orthopedist", "gynecologist"};
    private static Patient loggedPatient;
    private static Doctor loggedDoctor;
    private static Admin loggedAdmin;

    private UserAccount() {
    }

    public static Admin getLoggedAdmin() {
        return loggedAdmin;
    }

    public static Patient getLoggedPatient() {
        return loggedPatient;
    }

    public static Doctor getLoggedDoctor() {
        return loggedDoctor;
    }

    public static String[] getSpetiality() {
        return spetiality;
    }

    public static boolean found = false;
    static String firstName = null;
    static String surname = null;
    static String password = null;
    static String personalID = null;
    static String email = null;
    static String contactNumber = null;
    static String addressCity = null;
    static String addressStreet = null;
    static String bloodGroup = null;
    static int addressHouseNumber = 0;
    static int weight = 0;
    static int height = 0;
    static LocalDate birthdate = null;
    static char gender;
    private static boolean safeCreate;
    static Specialization specialization;
    static int doctorID;

    public static void setSafeCreatePatient(boolean safeCreate) {
        UserAccount.safeCreate = safeCreate;
    }

    public static void generalRegister() throws InputMismatchException {

        firstName = UserData.enterFirstName();
        surname = UserData.enterSurname();
        password = UserData.enterPassword();
        email = UserData.enterEmail();
        birthdate = UserData.enterBirthdate();
        personalID = UserData.enterPersonalIdNumber();
        contactNumber = UserData.enterContactNumber();
        addressCity = UserData.enterAddressCity();
        addressStreet = UserData.enterAddressStreet();
        addressHouseNumber = UserData.enterAddressHouseNumber();
        gender = UserData.enterGender();
    }

    public static void patientRegister() throws IOException {

        System.out.println("\nPATIENT REGISTER\n");
        safeCreate = false;
        generalRegister();
        height = UserData.enterHeight();
        weight = UserData.enterWeight();
        bloodGroup = UserData.enterBloodGroup();
        if (!safeCreate) {
            Connection.getPatients().add(new Patient(firstName, surname, password, personalID, email, contactNumber, addressCity, addressStreet, addressHouseNumber, birthdate, gender, bloodGroup, weight, height));
            Connection.savePatients();
            System.out.println("Account created successfully!");
            Powierzchnia.getRamka().set(new PanelMenu());
            safeCreate = true;
        }
    }

    public static void patientLogin() {
        String personalIDNumber, password;

        // while (true) {
        //     try {
        //         System.out.print("Enter your personal ID number: ");
        //     } catch (InputMismatchException invalidInput) {
        //         System.out.println("Invalid input - try again.");
        //         continue;
        //     }
        //     if (!isNumeric(personalIDNumber)) {
        //         System.out.println("Your input does not contain only digits");
        //     } else if (personalIDNumber.length() > 11) {
        //         System.out.println("You entered too many digits");
        //     } else if (personalIDNumber.length() < 11) {
        //         System.out.println("You entered not enough digits");
        //     } else break;
        // }
        //     try {
        //         System.out.print("Enter your password: ");
        //     } catch (InputMismatchException invalidInput) {
        //         System.out.println("Invalid input - try again.");
        //     }
        personalIDNumber = PanelLogowaniePacjent.getTextFieldPeselPacjent().getText();
        password = String.valueOf(PanelLogowaniePacjent.getTextFieldHasloPacjent().getPassword());
        System.out.println(password);

        for (int i = 0; i < Connection.getPatients().size(); i++) {
            if (Connection.getPatients().get(i).getPersonalIdNumber().equals(personalIDNumber) && Connection.getPatients().get(i).getPassword().equals(password)) {
                found = true;
                loggedPatient = Connection.getPatients().get(i);
            }
        }
        if (found) {
            System.out.println("Access granted");
        } else {
            System.out.println("Access denied! Invalid personal ID number or password ");
        }
    }

    public static void doctorRegister() throws IOException {
        safeCreate = false;
        generalRegister();
        specialization = UserData.enterSpecialization();
        doctorID = UserData.enterID();
        if (!safeCreate) {
            Connection.getDoctorsRegisterRequests().add(new Doctor(firstName, surname, password, personalID, email,
                    contactNumber, addressCity, addressStreet, addressHouseNumber, birthdate, gender, specialization, doctorID));
            System.out.println("Account created successfully!\nYour registration will be considered by an admin, after that you will be able to login");
            Connection.saveDoctorsRequests();
            System.out.println("Account created successfully!");
            Powierzchnia.getRamka().set(new PanelMenu());
            safeCreate = true;
        }
    }

    public static void doctorLogin() {

        System.out.println("\nDOCTOR LOGIN\n");

        int doctorID;
        String password;

        while (true) {
            try {
                System.out.print("Enter your doctor ID: ");
                doctorID = getIntInput();
            } catch (InputMismatchException invalidInput) {
                System.out.println("Invalid input - try again.");
                continue;
            }
            if (doctorID > 9999) {
                System.out.println("You entered too many digits");
            } else if (doctorID < 1000) {
                System.out.println("You entered not enough digits");
            } else break;
        }
        while (true) {
            try {
                System.out.print("Enter your password: ");
                password = getStringInput();
            } catch (InputMismatchException invalidInput) {
                System.out.println("Invalid input - try again.");
                continue;
            }
            break;
        }

        boolean found = false;
        for (int i = 0; i < Connection.getDoctors().size(); i++) {
            if (Connection.getDoctors().get(i).getDoctorId() == doctorID && Connection.getDoctors().get(i).getPassword().equals(password)) {
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

    public static void adminLogin() throws IOException {

        System.out.println("\nADMIN LOGIN\n");

        int adminID = 0;
        String password;
        try {
            adminID = Integer.parseInt(PanelLogowanieAdmin.getTextFieldIDLekarz().getText());
        } catch (NumberFormatException ignored) {
        }
        ;
        password = String.valueOf(PanelLogowanieAdmin.getTextFieldHasloLekarz().getPassword());
        for (int i = 0; i < Connection.getAdmins().size(); i++) {
            if (Connection.getAdmins().get(i).getAdminID() == adminID && Connection.getAdmins().get(i).getPassword().equals(password)) {
                loggedAdmin = Connection.getAdmins().get(i);
                Powierzchnia.getRamka().set(new PanelAdmin());
                password = null;
                adminID = 0;
                break;
            } else {
                PanelLogowanieAdmin.getLabelBlednyLoginLubHaslo().setVisible(true);
            }
        }
        if (!found) {
            PanelLogowanieAdmin.getLabelBlednyLoginLubHaslo().setForeground(Color.RED);
            PanelLogowanieAdmin.getLabelBlednyLoginLubHaslo().repaint();
        }
    }

    public static String getStringInput() {

        return new Scanner(System.in).nextLine();
    }

    public static int getIntInput() {

        return new Scanner(System.in).nextInt();
    }

    public static char getCharInput() {

        return new Scanner(System.in).next().charAt(0);
    }

    public static String nameValidate(String name) {

        if (name == null || name.length() == 0) return name;
        return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
    }

    public static boolean passwordValidate(String password) {

        return password.length() < 8;
    }

    public static boolean emailValidate(String email) {

        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null) return true;
        return !pat.matcher(email).matches();
    }

    public static boolean dateValidate(String date) {

        String dateRegex = "^((19|20)\\d\\d)-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])$";

        Pattern pat = Pattern.compile(dateRegex);

        if (date == null) return true;
        return !pat.matcher(date).matches();
    }

    public static boolean personalIDValidate(String personalID) {

        return personalID.length() != 11;
    }

    public static boolean phoneNumberValidate(String phoneNumber) {

        return phoneNumber.length() != 9;
    }

    public static boolean positiveNumberValidate(int houseNumber) {

        return houseNumber <= 0;
    }

    public static boolean genderValidate(char gender) {

        return gender != 'M' && gender != 'F';
    }

    public static boolean heightValidate(int height) {

        return height > 0 && height < 250;
    }

    public static boolean specializationValidate(int choice) {

        return choice > 4 || choice < 0;
    }

    public static boolean isNumeric(String strNum) {

        Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

        if (strNum == null) {
            return false;
        }
        return pattern.matcher(strNum).matches();
    }
}