package UI;


import Data.Connection;
import Data.Input;
import GUI.PatientGraphicInterface;
import Model.Doctor;
import Model.Patient;
import Service.Opinion;

import java.util.ArrayList;
import java.util.Objects;

public class PatientInterface {

    public static void mainInterface() {

        UserAccount.patientLogin();
        if (UserAccount.getLoggedPatient() != null) {
            Patient loggedPatient = UserAccount.getLoggedPatient();
            System.out.println("You are logged as: " + loggedPatient);
            int choice;
            do {
                PatientGraphicInterface.displayPatientInterface();
                System.out.print(": ");
                choice = Input.getInt();
                switch (choice) {
                    case 0 -> {}
                    case 1 -> displayMyProfile(loggedPatient);
                    case 2 -> makeAnAppointment(loggedPatient);
                    case 3 -> giveFeedback(loggedPatient);
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
        String patientChoice = Input.getString();
        switch (patientChoice){
            case "1":{}
            case "2":{}
            case "3":{}
            case "4":{}
        }
    }
    public static void giveFeedback(Patient loggedPatient) {
        System.out.println("All doctors: ");
        int doctorToReview;
        do {
            for (int i = 0; i < Connection.getDoctors().size(); i++) {
                System.out.println((i + 1) + ":" + Connection.getDoctors().get(i));
            }
            System.out.println("Enter 0 to exit");
            System.out.println("Enter index of the doctor you want to review: ");
            doctorToReview = Input.getInt();
            if (doctorToReview != 0) {
                System.out.println("Enter a number in scale 1 - 5");
                int value = Input.getInt();
                while (value < 0 || value > 5) {
                    System.out.println("Please enter a number in scale 1 - 5");
                    value = Input.getInt();
                }
                System.out.println("Give a comment");
                String comment = Input.getString();
                Opinion opinion = new Opinion(value, comment);
                //Doctor.setOpinion(opinion);
                System.out.println("Thank your for your opinion!");
                System.out.println("Do you want to give another opinion? Enter Y  or N");
                String nextOpinion = Input.getString();
                if (Objects.equals(nextOpinion, "N")) {
                    doctorToReview = 0;
                }
            }
        } while (doctorToReview != 0);


    }
}
