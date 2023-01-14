package App;

import GUI.InitialGraphicInterface;
import GUI.Menu.PanelMenu;
import GUI.Skladowe.Powierzchnia;
import Model.Doctor;
import Model.Patient;
import UI.*;
import Data.*;

import java.io.IOException;

public class Hospital {

    public static void main(String[] args) throws IOException {


        Connection.loadAllData();
        Powierzchnia.getRamka().set(new PanelMenu());
        System.out.println("Patients number: " + Connection.getPatients().size());
        System.out.println("Doctor number: " + Connection.getDoctors().size());
        System.out.println(Connection.getAdmins().toString().replace("[", "").replace("]", ""));
        System.out.println();

        displayAllPatients();
        displayAllDoctors();

        int choice;
        do {
            InitialGraphicInterface.displayInitialInterface();
            System.out.print(": ");
            choice = UserAccount.getIntInput();
            switch (choice) {
                case 0 -> {}
                case 1 -> UserAccount.patientRegister();
                case 2 -> PatientInterface.mainInterface();
                case 3 -> UserAccount.doctorRegister();
                case 4 -> DoctorInterface.mainInterface();
                case 5 -> AdminInterface.mainInterface();
                default -> throw new IllegalStateException("Unexpected value: " + choice);
            }
            System.out.println();
        } while (choice != 0);

        Connection.saveAllData();
    }

    public static void displayAllPatients() {

        System.out.println("Patients:");
        for (Patient patient : Connection.getPatients()) {
            System.out.println(patient);
        }
        System.out.println();
    }

    public static void displayAllDoctors() {

        System.out.println("Doctors:");
        for (Doctor doctor : Connection.getDoctors()) {
            System.out.println(doctor);
        }
        System.out.println();
    }
}