package UI;

import Data.Connection;
import Data.Input;
import GUI.AdminGraphicInterface;
import Model.Admin;
import Model.Doctor;
import Model.Patient;

public class AdminInterface {

    public static void mainInterface() {

        UserAccount.adminLogin();
        if (UserAccount.getLoggedAdmin() != null) {
            Admin loggedAdmin = UserAccount.getLoggedAdmin();
            System.out.println("You are logged as: " + loggedAdmin);
            System.out.println();

            int choice;
            do {
                AdminGraphicInterface.displayAdminInterface();
                System.out.print(": ");
                choice = Input.getInt();
                switch (choice) {
                    case 0 -> {
                        UserAccount.setLoggedAdmin(null);
                    }
                    case 1 -> {
                        removePatientInterface(loggedAdmin);
                        continueButton();
                    }
                    case 2 -> {
                        removeDoctorInterface(loggedAdmin);
                        continueButton();
                    }
                    case 3 -> {
                        acceptDoctorInterface(loggedAdmin);
                        continueButton();
                    }
                    case 4 -> {
                        displayAllPatients();
                        continueButton();
                    }
                    case 5 -> {
                        displayAllDoctors();
                        continueButton();
                    }
                    default -> throw new IllegalStateException("Unexpected value: " + choice);
                }
                System.out.println();
            } while (choice != 0);
        }
    }

    public static void continueButton() {

        System.out.print("Press any key to continue...");
        String continueButton = Input.getString();
    }

    public static void removePatientInterface(Admin loggedAdmin) {

        System.out.println("Choose patient to remove:");
        for (int i = 0; i < Connection.getPatients().size(); i++) {
            System.out.println((i + 1) + ": " + Connection.getPatients().get(i));
        }
        System.out.print(": ");
        int patientToRemove = Input.getInt();
        loggedAdmin.removePatient(Connection.getPatients().get(patientToRemove - 1));
    }

    public static void removeDoctorInterface(Admin loggedAdmin) {

        System.out.println("Choose doctor to remove");
        for (int i = 0; i < Connection.getDoctors().size(); i++) {
            System.out.println((i + 1) + ": " + Connection.getDoctors().get(i));
        }
        System.out.print(": ");
        int doctorToRemove = Input.getInt();
        loggedAdmin.removeDoctor(Connection.getDoctors().get(doctorToRemove - 1));
    }

    public static void acceptDoctorInterface(Admin loggedAdmin) {

        System.out.println("Doctors pending requests:");
        int doctorToAccept;
        do {
            if (Connection.getDoctorsRegisterRequests().size() == 0) {
                System.out.println("There are not any doctors on the request list");
                break;
            }
            for (int i = 0; i < Connection.getDoctorsRegisterRequests().size(); i++) {
                System.out.println((i + 1) + ": " + Connection.getDoctorsRegisterRequests().get(i));
            }
            System.out.println("Enter 0 to exit");
            System.out.print("Enter index of the doctor you want to accept: ");
            doctorToAccept = Input.getInt();
            if (doctorToAccept != 0) {
                loggedAdmin.acceptDoctor(Connection.getDoctorsRegisterRequests().get(doctorToAccept - 1));
            }
        } while (doctorToAccept != 0);
    }

    public static void displayAllPatients() {

        System.out.println("Patients registered in hospital: " + Connection.getPatients().size());
        System.out.println("Patients:");
        for (Patient patient : Connection.getPatients()) {
            System.out.println(patient);
        }
        System.out.println();
    }

    public static void displayAllDoctors() {

        System.out.println("Doctors registered in hospital: " + Connection.getDoctors().size());
        System.out.println("Doctors:");
        for (Doctor doctor : Connection.getDoctors()) {
            System.out.println(doctor);
        }
        System.out.println();
    }
}