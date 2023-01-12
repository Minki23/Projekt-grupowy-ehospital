package App;

import GUI.InitialGraphicInterface;
import UI.*;
import Data.*;

public class Hospital {

    public Hospital() {

        int choice;
        do {
            InitialGraphicInterface.displayInitialInterface();
            System.out.print(": ");
            choice = Input.getInt();
            switch (choice) {
                case 0 -> {}
                case 1 -> UserAccount.patientRegister();
                case 2 -> new PatientInterface();
                case 3 -> UserAccount.doctorRegister();
                case 4 -> new DoctorInterface();
                case 5 -> new AdminInterface();
                default -> throw new IllegalStateException("Unexpected value: " + choice);
            }
            System.out.println();
        } while (choice != 0);
    }
}