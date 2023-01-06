package Data;

import Model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public final class Connection {

    private static final String filenameAdmins = "DODAC SWOJA SCIEZKE DO PLIKU Z ADMINAMI";
    private static final String filenamePatients = "DODAC SWOJA SCIEZKE DO PLIKU Z PACJENTAMI";
    private static final String filenameDoctors = "DODAC SWOJA SCIEZKE DO PLIKU Z LEKARZAMI";
    private static final String filenameDoctorsRequests = "DODAC SWOJA SCIEZKE DO PLIKU Z LEKARZAMI CZEKAJACYMI NA AKCEPTACJE";
    private static List<Admin> admins = new ArrayList<>();
    private static List<Patient> patients = new ArrayList<>();
    private static List<Doctor> doctors = new ArrayList<>();
    private static List<Doctor> doctorsRegisterRequests = new ArrayList<>();

    private Connection() {}

    public static String getFilenamePatients() {
        return filenamePatients;
    }

    public static String getFilenameDoctors() {
        return filenameDoctors;
    }

    public static List<Patient> getPatients() {
        return patients;
    }

    public static List<Doctor> getDoctors() {
        return doctors;
    }

    public static List<Admin> getAdmins() {
        return admins;
    }

    public static List<Doctor> getDoctorsRegisterRequests() {
        return doctorsRegisterRequests;
    }

    public static void loadAllData() {

        loadDoctors();
        loadPatients();
        loadAdmins();
        loadDoctorsRequests();
    }

    public static void saveAllData() {

        saveDoctors();
        savePatients();
        saveDoctorsRequests();
        saveAdmins();
    }

    public static void savePatients() {

        try {
            FileOutputStream fos = new FileOutputStream(filenamePatients);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(patients);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static void loadPatients() {

        try {
            FileInputStream fis = new FileInputStream(filenamePatients);
            ObjectInputStream ois = new ObjectInputStream(fis);
            patients = (List<Patient>) ois.readObject();
        } catch (IOException | ClassNotFoundException exception) {
            if (!patients.isEmpty()) exception.printStackTrace();
        }
    }

    public static void saveDoctors() {

        try {
            FileOutputStream fos = new FileOutputStream(filenameDoctors);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(doctors);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static void loadDoctors() {

        try {
            FileInputStream fis = new FileInputStream(filenameDoctors);
            ObjectInputStream ois = new ObjectInputStream(fis);
            doctors = (List<Doctor>) ois.readObject();
        } catch (IOException | ClassNotFoundException exception) {
            if (!doctors.isEmpty()) exception.printStackTrace();
        }
    }

    public static void saveAdmins() {

        try {
            FileOutputStream fos = new FileOutputStream(filenameAdmins);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(admins);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static void loadAdmins() {

        try {
            FileInputStream fis = new FileInputStream(filenameAdmins);
            ObjectInputStream ois = new ObjectInputStream(fis);
            admins = (List<Admin>) ois.readObject();
        } catch (IOException | ClassNotFoundException exception) {
            if (!admins.isEmpty()) exception.printStackTrace();
        }
    }

    public static void saveDoctorsRequests() {

        try {
            FileOutputStream fos = new FileOutputStream(filenameDoctorsRequests);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(doctorsRegisterRequests);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static void loadDoctorsRequests() {

        try {
            FileInputStream fis = new FileInputStream(filenameDoctorsRequests);
            ObjectInputStream ois = new ObjectInputStream(fis);
            doctorsRegisterRequests = (List<Doctor>) ois.readObject();
        } catch (IOException | ClassNotFoundException exception) {
            if (!doctorsRegisterRequests.isEmpty()) exception.printStackTrace();
        }
    }
}