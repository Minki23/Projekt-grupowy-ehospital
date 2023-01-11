package UI;


import GUI.PatientGraphicInterface;
import Model.Doctor;
import Model.Patient;
import Model.Specialization;
import Service.Appointment;

import java.util.ArrayList;

public final class PatientInterface {

    private PatientInterface() {
    }


    public static void mainInterface() {

        UserAccount.patientLogin();
        if (UserAccount.getLoggedPatient() != null) {
            Patient loggedPatient = UserAccount.getLoggedPatient();
            System.out.println("You are logged as: " + loggedPatient);
            int choice;
            do {
                PatientGraphicInterface.displayPatientInterface();
                System.out.print(": ");
                choice = UserAccount.getIntInput();
                switch (choice) {
                    case 0 -> {}
                    case 1 -> displayMyProfile(loggedPatient);
                    case 2 -> makeAnAppointment(loggedPatient);
                    default -> throw new IllegalStateException("Unexpected value: " + choice);
                }
                System.out.println();
            } while (choice != 0);
        }
    }

    public static void displayMyProfile(Patient loggedPatient) {
        System.out.println(loggedPatient);
    }
    public static void makeAnAppointment(Patient loggedPatient){
        System.out.println("|Choose Your specialist|");
        System.out.println("-----------------------");
        System.out.println("| '1' Family Doctor    |");
        System.out.println("| '2' Heart Specialist |");
        System.out.println("| '3' Orthopedist      |");
        System.out.println("| '4' Lungs Specialist |");
        String patientChoice = UserAccount.getStringInput();
        switch (patientChoice){
            case "1":{}
            case "2":{}
            case "3":{}
            case "4":{}
        }

    }
}
