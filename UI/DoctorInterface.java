package UI;

import Data.Connection;
import Data.Input;
import Data.UserData;
import GUI.DoctorGraphicInterface;
import Model.Doctor;
import Model.Patient;
import Model.Specialization;
import Service.Appointment;
import Service.Opinion;
import org.w3c.dom.ls.LSOutput;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class DoctorInterface {

    public static void mainInterface() {

        UserAccount.doctorLogin();
        if (UserAccount.getLoggedDoctor() != null) {
            Doctor loggedDoctor = UserAccount.getLoggedDoctor();
            System.out.println("\nYou are logged as: " + loggedDoctor);

            int choice;
            do {
                DoctorGraphicInterface.displayDoctorInterface();
                System.out.print(": ");
                choice = Input.getInt();
                switch (choice) {
                    case 0 -> UserAccount.setLoggedDoctor(null);
                    case 1 -> displayMyProfile(loggedDoctor);
                    case 2 -> editMyProfile(loggedDoctor);
                    case 3 -> displayPatients(searchForPatients());
                    case 4 ->{} //showMyCalendar(loggedDoctor);
                    case 5 -> showMyOpinions(loggedDoctor);
                    default -> throw new IllegalStateException("Unexpected value: " + choice);
                }
                System.out.println();
            } while (choice != 0);
        }
    }

    public static void displayMyProfile(Doctor loggedDoctor) {
        System.out.println("\n" + loggedDoctor);
    }

    public static void editMyProfile(Doctor loggedDoctor) {
        int choice;
        do {
            System.out.println("\nYou are logged as: " + loggedDoctor);
            System.out.println("\nChoose data to change");
            System.out.println("""
                    \t1: First name
                    \t2: Surname
                    \t3: Password
                    \t4: Personal ID number
                    \t5: E-mail
                    \t6: Contact number
                    \t7: City
                    \t8: Street
                    \t9: House number
                    \t10: Date of birth
                    \t11: Gender
                    \t12: Specialization
                    \t0: Exit
                    """);
            System.out.print("Choice: ");
            choice = Input.getInt();
            System.out.println();

            try {
                switch (choice) {
                    case 0 -> {}
                    case 1 -> changeFirstName(loggedDoctor);
                    case 2 -> changeSurname(loggedDoctor);
                    case 3 -> changePassword(loggedDoctor);
                    case 4 -> changePersonalIdNumber(loggedDoctor);
                    case 5 -> changeEmail(loggedDoctor);
                    case 6 -> changeContactNumber(loggedDoctor);
                    case 7 -> changeCity(loggedDoctor);
                    case 8 -> changeStreet(loggedDoctor);
                    case 9 -> changeHouseNumber(loggedDoctor);
                    case 10 -> changeDateOfBirth(loggedDoctor);
                    case 11 -> changeGender(loggedDoctor);
                    case 12 -> changeSpecialization(loggedDoctor);
                    default -> throw new IllegalStateException("Unexpected value: " + choice);
                }
            }
            catch (InputMismatchException invalidInput) {
                System.out.println("\nInvalid data\n");
            }
        }
        while (choice != 0);
    }

    public static void changeFirstName(Doctor loggedDoctor) {
        System.out.print("Enter new first name: ");
        String newFirstName = UserData.nameValidate(Input.getString());
        loggedDoctor.setFirstName(newFirstName);
        System.out.println("\nFirst name changed successfully");
    }

    public static void changeSurname(Doctor loggedDoctor) {
        System.out.print("Enter new surname: ");
        String newSurname = UserData.nameValidate(Input.getString());
        loggedDoctor.setFirstName(newSurname);
        System.out.println("\nSurname changed successfully");
    }

    public static void changePassword(Doctor loggedDoctor) {
        System.out.print("Enter old password: ");
        String oldPassword = Input.getString();
        if (oldPassword.equals(loggedDoctor.getPassword())) {
            System.out.print("Enter new password: ");
            String newPassword = Input.getString();
            if (UserData.passwordValidate(newPassword)) {
                System.out.println("\nPassword must contain at least 8 characters!");
                System.out.println("Password has not been changed");
            }
            else {
                loggedDoctor.setPassword(newPassword);
                System.out.println("\nPassword changed successfully");
            }
        }
        else {
            System.out.println("\nIncorrect password");
        }
    }

    public static void changePersonalIdNumber(Doctor loggedDoctor) {
        System.out.print("Enter new personal ID number: ");
        String newPersonalIdNumber = Input.getString();
        if (UserData.personalIDValidate(newPersonalIdNumber)) {
            System.out.println("\nIncorrect personal ID number");
            System.out.println("Personal ID number has not been changed");
        }
        else {
            loggedDoctor.setPersonalIdNumber(newPersonalIdNumber);
            System.out.println("\nPersonal ID number changed successfully");
        }
    }

    public static void changeEmail(Doctor loggedDoctor) {
        System.out.print("Enter new e-mail: ");
        String newEmail = Input.getString();
        if (UserData.emailValidate(newEmail)) {
            System.out.println("\nIncorrect e-mail address");
        }
        else {
            loggedDoctor.setEmail(newEmail);
            System.out.println("\nE-mail changed successfully");
        }
    }

    public static void changeContactNumber(Doctor loggedDoctor) {
        System.out.print("Enter new contact number: ");
        String newContactNumber = Input.getString();
        if (UserData.phoneNumberValidate(newContactNumber)) {
            System.out.println("\nIncorrect contact number");
            System.out.println("Contact number has not been changed");
        }
        else {
            loggedDoctor.setContactNumber(newContactNumber);
            System.out.println("\nContact number changed successfully");
        }
    }

    public static void changeCity(Doctor loggedDoctor) {
        System.out.print("Enter new city: ");
        String newCity = UserData.nameValidate(Input.getString());
        loggedDoctor.setCity(newCity);
        System.out.println("\nCity changed successfully");
    }

    public static void changeStreet(Doctor loggedDoctor) {
        System.out.print("Enter new street name: ");
        String newStreet = UserData.nameValidate(Input.getString());
        loggedDoctor.setStreet(newStreet);
        System.out.println("\nStreet name changed successfully");
    }

    public static void changeHouseNumber(Doctor loggedDoctor) {
        System.out.print("Enter new house number: ");
        int newHouseNumber = Input.getInt();
        if (UserData.positiveNumberValidate(newHouseNumber)) {
            System.out.println("\nIncorrect house number");
            System.out.println("House number has not been changed");
        }
        else {
            loggedDoctor.setHouseNumber(newHouseNumber);
            System.out.println("\nHouse number changed successfully");
        }
    }

    public static void changeDateOfBirth(Doctor loggedDoctor) {
        System.out.print("Enter new date of birth (YYY-MM-DD): ");
        String newDateOfBirth = Input.getString();
        if (UserData.dateValidate(newDateOfBirth)) {
            System.out.println("\nIncorrect date of birth");
            System.out.println("Date of birth has not been changed");
        }
        else {
            LocalDate parsedNewDateOfBirth = LocalDate.parse(newDateOfBirth);
            loggedDoctor.setBirthdate(parsedNewDateOfBirth);
            System.out.println("\nDate of birth changed successfully");
        }
    }

    public static void changeGender(Doctor loggedDoctor) {
        System.out.print("Enter new gender (M - male / F - female): ");
        char newGender = Input.getChar();
        if (UserData.genderValidate(newGender)) {
            System.out.println("\nIncorrect gender");
            System.out.println("Gender has not been changed");
        }
        else {
            loggedDoctor.setGender(newGender);
            System.out.println("\nGender changed successfully");
        }
    }

    public static void changeSpecialization(Doctor loggedDoctor) {
        System.out.println("""
                Choose new specialization:
                \t1. Family doctor
                \t2. Heart specialist
                \t3. Orthopedist
                \t4. Lungs Specialist
                """);
        System.out.print("\nChoice: ");
        int newChoice = Input.getInt();
        if (UserData.specializationValidate(newChoice)) {
            System.out.println("\nIncorrect value");
            System.out.println("Specialization has not been changed");
        }
        else {
            String specializationName = "";
            switch (newChoice) {
                case 1 -> specializationName = "Family doctor";
                case 2 -> specializationName = "Heart specialist";
                case 3 -> specializationName = "Orthopedist";
                case 4 -> specializationName = "Gynecologist";
            }
            loggedDoctor.setSpecialization(new Specialization(specializationName));
            System.out.println("\nSpecialization changed successfully");
        }
    }

    public static List<Patient> searchForPatients() {
        List<Patient> patients = new ArrayList<>();

        System.out.println("Enter first name and surname or personal ID number of the patient");
        String userInput = Input.getString();
        if (UserData.isNumeric(userInput)) {
            for (int i = 0; i < Connection.getPatients().size(); i++) {
                if (userInput.equals(Connection.getPatients().get(i).getPersonalIdNumber()))
                    patients.add(Connection.getPatients().get(i));
            }
        } else {
            String[] name = userInput.split(" ");
            if (name.length != 2) {
                System.out.println("First name and last name should be separated by a space!");
                return patients;
            }
            for (int i = 0; i < Connection.getPatients().size(); i++) {
                if ((name[0].equals(Connection.getPatients().get(i).getFirstName())) &&
                        (name[1].equals(Connection.getPatients().get(i).getSurname())))
                    patients.add(Connection.getPatients().get(i));
            }
        }

        return patients;
    }

    public static void displayPatients(List<Patient> patients) {
        if (patients.size() == 0) {
            System.out.println("Patient not found");
        } else if (patients.size() == 1) {
            System.out.println(patients.get(0).toString());
        } else {
            System.out.println("Multiple patients have been found:");
            for (int i = 0; i < patients.size(); i++)
                System.out.println((i + 1) + ". " + patients.get(i).toString());
        }
    }

    //public static void showMyCalendar(Doctor loggedDoctor){
    //    System.out.println(Doctor.CreateMyCalendar());
    //}
    public static void showMyOpinions(Doctor loggedDoctor) {
        System.out.println("\nMy opinions:");
        for (Opinion opinion : loggedDoctor.getOpinions()) {
            System.out.println("\t" + opinion);
        }
        System.out.println("\nMy average opinion: " + loggedDoctor.getAverageOpinion());
    }
}
