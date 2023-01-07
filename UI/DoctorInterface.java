package UI;

import Data.Connection;
import GUI.DoctorGraphicInterface;
import Model.Doctor;
import Model.Patient;

import java.util.ArrayList;
import java.util.List;

public final class DoctorInterface {

    private DoctorInterface() {
    }

    public static void mainInterface() {

        UserAccount.doctorLogin();
        if (UserAccount.getLoggedDoctor() != null) {
            Doctor loggedDoctor = UserAccount.getLoggedDoctor();
            System.out.println("You are logged as: " + loggedDoctor);

            int choice;
            do {
                DoctorGraphicInterface.displayDoctorInterface();
                System.out.print(": ");
                choice = UserAccount.getIntInput();
                switch (choice) {
                    case 0 -> {}
                    case 1 -> displayMyProfile(loggedDoctor);
                    case 2 -> {} //edycja profilu
                    case 3 -> displayPatients(searchForPatients());
                    case 4 -> {} //wizyty
                    case 5 -> {} //opinie
                    default -> throw new IllegalStateException("Unexpected value: " + choice);
                }
                System.out.println();
            } while (choice != 0);
        }
    }

    public static void displayMyProfile(Doctor loggedDoctor) {
        System.out.println(loggedDoctor);
    }

    public static List<Patient> searchForPatients() {
        List<Patient> patients = new ArrayList<>();

        System.out.println("Enter first name and surname or personal ID number of the patient");
        String userInput = UserAccount.getStringInput();
        if (UserAccount.isNumeric(userInput)) {
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
                if ((name[0].equals(Connection.getPatients().get(i).getFirstName())) && (name[1].equals(Connection.getPatients().get(i).getSurname())))
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
}
