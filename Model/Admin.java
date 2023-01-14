package Model;

import Data.Connection;

import java.io.Serial;
import java.io.Serializable;

public class    Admin implements Serializable {

    @Serial
    private static final long serialVersionUID = -1258337360769044482L;
    private int adminID;
    private String password;

    public Admin() {

        this.adminID = 0;
        this.password = "";
    }

    public Admin(int adminID, String password) {

        this.adminID = adminID;
        this.password = password;
    }

    public int getAdminID() {
        return adminID;
    }

    public void setAdminID(int adminID) {
        this.adminID = adminID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Admin [" +
                "adminID: " + adminID +
                ", password: " + password +
                ']';
    }

    public void removeDoctor(Doctor doctor) {

        Connection.getDoctors().remove(doctor);
    }

    public void removePatient(Patient patient) {

        Connection.getPatients().remove(patient);
    }

    public void acceptDoctor(Doctor doctor) {

        Connection.getDoctors().add(doctor);

        Connection.getDoctorsRegisterRequests().remove(doctor);
    }
}
