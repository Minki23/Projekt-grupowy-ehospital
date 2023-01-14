package App;

import Data.Connection;
import GUI.Menu.PanelMenu;
import GUI.Skladowe.Powierzchnia;

import java.io.IOException;

import static UI.AdminInterface.AdminInterface.displayAllDoctors;
import static UI.AdminInterface.AdminInterface.displayAllPatients;

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
    }
}