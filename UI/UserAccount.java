package UI;

import Model.*;
import Data.*;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;

public final class UserAccount {

    private static final  String[] bloodGroups = {"A+", "A-", "B+", "B-", "0+", "0-", "AB+", "AB-"};
    private static Patient loggedPatient;
    private static Doctor loggedDoctor;
    private static Admin loggedAdmin;

    private UserAccount() {}

    public static Admin getLoggedAdmin() {
        return loggedAdmin;
    }

    public static Patient getLoggedPatient() {
        return loggedPatient;
    }

    public static Doctor getLoggedDoctor() {
        return loggedDoctor;
    }

    public static void patientRegister() {

        boolean safeCreate;

        String firstName = null;
        String surname = null;
        String password = null;
        String personalID = null;
        String email = null;
        String contactNumber = null;
        String addressCity = null;
        String addressStreet = null;
        String bloodGroup = null;
        int addressHouseNumber = 0;
        int weight = 0;
        int height = 0;
        LocalDate birthdate = null;
        char gender = 0;

        System.out.println("\nPATIENT REGISTER\n");

        do {
            safeCreate = false;

            try {
                System.out.print("Enter first name: ");
                firstName = getStringInput();
                firstName = nameValidate(firstName);

                System.out.print("Enter surname: ");
                surname = getStringInput();
                surname = nameValidate(surname);

                System.out.print("Enter password: ");
                password = getStringInput();
                while (passwordValidate(password)) {
                    System.out.println("Password must contains at least 8 characters!");
                    System.out.print("Enter new password: ");
                    password = getStringInput();
                }

                System.out.print("Enter email: ");
                email = getStringInput();
                while (emailValidate(email)) {
                    System.out.println("Invalid email");
                    System.out.print("Enter correct email: ");
                    email = getStringInput();
                }

                System.out.print("Enter your date of birth (YYYY-MM-DD): ");
                String birthdateInput = getStringInput();
                while (dateValidate(birthdateInput)) {
                    System.out.println("Invalid date");
                    System.out.print("Enter correct date: ");
                    birthdateInput = getStringInput();
                }
                birthdate = LocalDate.parse(birthdateInput);

                System.out.print("Enter personal id number: ");
                personalID = getStringInput();
                while (personalIDValidate(personalID)) {
                    System.out.println("Invalid personal ID number");
                    System.out.print("Enter correct personal ID number: ");
                    personalID = getStringInput();
                }

                System.out.print("Enter contact number: ");
                contactNumber = getStringInput();
                while (phoneNumberValidate(contactNumber)) {
                    System.out.println("Invalid phone number");
                    System.out.print("Enter correct phone number: ");
                    contactNumber = getStringInput();
                }

                System.out.print("Enter the city you live in: ");
                addressCity = getStringInput();
                addressCity = nameValidate(addressCity);

                System.out.print("Enter the street you live in: ");
                addressStreet = getStringInput();
                addressStreet = nameValidate(addressStreet);

                System.out.print("Enter your house number: ");
                addressHouseNumber = getIntInput();
                while (positiveNumberValidate(addressHouseNumber)) {
                    System.out.println("Invalid house number");
                    System.out.print("Enter correct house number: ");
                    addressHouseNumber = getIntInput();
                }

                System.out.println("Enter your gender:\nType M for male, or F for female");
                System.out.print(": ");
                gender = getCharInput();
                while (genderValidate(gender)) {
                    System.out.println("Invalid input");
                    System.out.print("Enter valid gender: ");
                    gender = getCharInput();
                }

                System.out.print("Enter your weight: ");
                weight = getIntInput();
                while (positiveNumberValidate(weight)) {
                    System.out.println("Invalid input");
                    System.out.print("Enter valid weight");
                    weight = getIntInput();
                }

                System.out.print("Enter your height: ");
                height = getIntInput();
                while (!heightValidate(height)) {
                    System.out.println("Invalid input");
                    System.out.print("Enter valid height");
                }

                for (int i = 0; i < bloodGroups.length; i++) {
                    System.out.println(i + 1 + ": " + bloodGroups[i]);
                }
                System.out.print("Choose your blood group: ");
                int choice = getIntInput();
                while (choice > 8 || choice < 1) {
                    System.out.println("Invalid input");
                    System.out.print("Enter valid input: ");
                    choice = getIntInput();
                }
                bloodGroup = bloodGroups[choice - 1];
            } catch (InputMismatchException InvalidInput) {
                safeCreate = true;
                System.out.println("\nInvalid data. Try again\n");
            }
        } while (safeCreate);

        Connection.getPatients().add(new Patient(firstName, surname, password, personalID, email, contactNumber, addressCity, addressStreet, addressHouseNumber, birthdate, gender, bloodGroup, weight, height));
        Connection.savePatients();
        System.out.println("Account created successfully!");
    }

    public static void patientLogin() {

        System.out.println("\nPATIENT LOGIN\n");

        String personalIDNumber, password;

        while (true) {
            try {
                System.out.print("Enter your personal ID number: ");
                personalIDNumber = getStringInput();
            } catch (InputMismatchException invalidInput) {
                System.out.println("Invalid input - try again.");
                continue;
            }
            if (!isNumeric(personalIDNumber)) {
                System.out.println("Your input does not contain only digits");
            } else if (personalIDNumber.length() > 11) {
                System.out.println("You entered too many digits");
            }
            else if (personalIDNumber.length() < 11) {
                System.out.println("You entered not enough digits");
            }
            else break;
        }

        while (true) {
            try {
                System.out.print("Enter your password: ");
                password = getStringInput();
            } catch (InputMismatchException invalidInput) {
                System.out.println("Invalid input - try again.");
                continue;
            }
            break;
        }

        boolean found = false;
        for (int i = 0; i < Connection.getPatients().size(); i++) {
            if (Connection.getPatients().get(i).getPersonalIdNumber().equals(personalIDNumber) && Connection.getPatients().get(i).getPassword().equals(password)) {
                found = true;
                loggedPatient = Connection.getPatients().get(i);
                break;
            }
        }

        if (found) {
            System.out.println("Access granted");
        } else {
            System.out.println("Access denied! Invalid personal ID number or password ");
        }
    }

    public static void doctorRegister() {

        boolean safeCreate;

        String firstName = null;
        String surname = null;
        String password = null;
        String personalID = null;
        String email = null;
        String contactNumber = null;
        String addressCity = null;
        String addressStreet = null;
        Specialization specialization = null;
        int addressHouseNumber = 0;
        LocalDate birthdate = null;
        char gender = 0;

        System.out.println("\nDOCTOR REGISTER\n");

        do {
            safeCreate = false;

            try {
                System.out.print("Enter first name: ");
                firstName = getStringInput();
                firstName = nameValidate(firstName);

                System.out.print("Enter surname: ");
                surname = getStringInput();
                surname = nameValidate(surname);

                System.out.print("Enter password: ");
                password = getStringInput();
                while (passwordValidate(password)) {
                    System.out.println("Password must contains at least 8 characters!");
                    System.out.print("Enter new password: ");
                    password = getStringInput();
                }

                System.out.print("Enter email: ");
                email = getStringInput();
                while (emailValidate(email)) {
                    System.out.println("Invalid email");
                    System.out.print("Enter correct email: ");
                    email = getStringInput();
                }

                System.out.print("Enter your date of birth (YYYY-MM-DD): ");
                String birthdateInput = getStringInput();
                while (dateValidate(birthdateInput)) {
                    System.out.println("Invalid date");
                    System.out.print("Enter correct date: ");
                    birthdateInput = getStringInput();
                }
                birthdate = LocalDate.parse(birthdateInput);

                System.out.print("Enter personal id number: ");
                personalID = getStringInput();
                while (personalIDValidate(personalID)) {
                    System.out.println("Invalid personal ID number");
                    System.out.print("Enter correct personal ID number: ");
                    personalID = getStringInput();
                }

                System.out.print("Enter contact number: ");
                contactNumber = getStringInput();
                while (phoneNumberValidate(contactNumber)) {
                    System.out.println("Invalid phone number");
                    System.out.print("Enter correct phone number: ");
                    contactNumber = getStringInput();
                }

                System.out.print("Enter the city you live in: ");
                addressCity = getStringInput();
                addressCity = nameValidate(addressCity);

                System.out.print("Enter the street you live in: ");
                addressStreet = getStringInput();
                addressStreet = nameValidate(addressStreet);

                System.out.print("Enter your house number: ");
                addressHouseNumber = getIntInput();
                while (positiveNumberValidate(addressHouseNumber)) {
                    System.out.println("Invalid house number");
                    System.out.print("Enter correct house number: ");
                    addressHouseNumber = getIntInput();
                }

                System.out.println("Enter your gender:\nType M for male, or F for female");
                System.out.print(": ");
                gender = getCharInput();
                while (genderValidate(gender)) {
                    System.out.println("Invalid input");
                    System.out.print("Enter valid gender: ");
                    gender = getCharInput();
                }

                System.out.println("Choose your specialization form below:");
                System.out.println("1: family doctor\n2: heart specialist\n3: orthopedist\n4: gynecologist");
                System.out.print(": ");
                int choice = getIntInput();
                while (specializationValidate(choice)) {
                    System.out.println("Invalid input");
                    System.out.print("Enter valid choice: ");
                    choice = getIntInput();
                }

                switch (choice) {
                    case 1 -> specialization = new Specialization("family doctor");
                    case 2 -> specialization = new Specialization("heart specialist");
                    case 3 -> specialization = new Specialization("orthopedist");
                    case 4 -> specialization = new Specialization("gynecologist");
                    default -> specialization = new Specialization();
                }
            } catch (InputMismatchException invalidInput) {
                safeCreate = true;
                System.out.println("\nInvalid data. Try again\n");
            }
        } while (safeCreate);

        Random random = new Random();
        int doctorID = random.nextInt(1000, 9999);

        Connection.getDoctorsRegisterRequests().add(new Doctor(firstName, surname, password, personalID, email, contactNumber, addressCity, addressStreet, addressHouseNumber, birthdate, gender, specialization, doctorID));

        System.out.println("Account created successfully!\nYour registration will be considered by an admin, after that you will be able to login");
        Connection.saveDoctorsRequests();
    }

    public static void doctorLogin() {

        System.out.println("\nDOCTOR LOGIN\n");

        int doctorID;
        String password;

        while (true) {
            try {
                System.out.print("Enter your doctor ID: ");
                doctorID = getIntInput();
            } catch (InputMismatchException invalidInput) {
                System.out.println("Invalid input - try again.");
                continue;
            }
            if (doctorID > 9999) {
                System.out.println("You entered too many digits");
            }
            else if (doctorID < 1000) {
                System.out.println("You entered not enough digits");
            }
            else break;
        }
        while (true) {
            try {
                System.out.print("Enter your password: ");
                password = getStringInput();
            } catch (InputMismatchException invalidInput) {
                System.out.println("Invalid input - try again.");
                continue;
            }
            break;
        }

        boolean found = false;
        for (int i = 0; i < Connection.getDoctors().size(); i++) {
            if (Connection.getDoctors().get(i).getDoctorId() == doctorID && Connection.getDoctors().get(i).getPassword().equals(password)) {
                found = true;
                loggedDoctor = Connection.getDoctors().get(i);
                break;
            }
        }

        if (found) {
            System.out.println("Access granted");
        } else {
            System.out.println("Access denied!");
        }
    }

    public static void createAdmin(int doctorID, String password) {

        Connection.getAdmins().add(new Admin(doctorID, password));
    }

    public static void adminLogin() {

        System.out.println("\nADMIN LOGIN\n");

        int adminID;
        String password;

        while (true) {
            try {
                System.out.print("Enter admin ID: ");
                adminID = getIntInput();
            } catch (InputMismatchException invalidInput) {
                System.out.println("Invalid input - try again.");
                continue;
            }
            if (adminID > 9999) {
                System.out.println("You entered too many digits");
            }
            else if (adminID < 1000) {
                System.out.println("You entered not enough digits");
            }
            else break;
        }
        while (true) {
            try {
                System.out.print("Enter admin password: ");
                password = getStringInput();
            } catch (InputMismatchException invalidInput) {
                System.out.println("Invalid input - try again.");
                continue;
            }
            break;
        }

        boolean found = false;
        for (int i = 0; i < Connection.getAdmins().size(); i++) {
            if (Connection.getAdmins().get(i).getAdminID() == adminID && Connection.getAdmins().get(i).getPassword().equals(password)) {
                found = true;
                loggedAdmin = Connection.getAdmins().get(i);
                break;
            }
        }

        if (found) {
            System.out.println("Access granted");
        } else {
            System.out.println("Access denied! Invalid ID or password ");
        }
    }

    public static String getStringInput() {

        return new Scanner(System.in).nextLine();
    }

    public static int getIntInput() {

        return new Scanner(System.in).nextInt();
    }

    public static char getCharInput() {

        return new Scanner(System.in).next().charAt(0);
    }

    public static String nameValidate(String name) {

        if (name == null || name.length() == 0) return name;
        return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
    }

    public static boolean passwordValidate(String password) {

        return password.length() < 8;
    }

    public static boolean emailValidate(String email) {

        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null) return true;
        return !pat.matcher(email).matches();
    }

    public static boolean dateValidate(String date) {

        String dateRegex = "^((19|20)\\d\\d)-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])$";

        Pattern pat = Pattern.compile(dateRegex);

        if (date == null) return true;
        return !pat.matcher(date).matches();
    }

    public static boolean personalIDValidate(String personalID) {

        return personalID.length() != 11;
    }

    public static boolean phoneNumberValidate(String phoneNumber) {

        return phoneNumber.length() != 9;
    }

    public static boolean positiveNumberValidate(int houseNumber) {

        return houseNumber <= 0;
    }

    public static boolean genderValidate(char gender) {

        return gender != 'M' && gender != 'F';
    }

    public static boolean heightValidate(int height) {

        return height > 0 && height < 250;
    }

    public static boolean specializationValidate(int choice) {

        return choice > 4 || choice < 0;
    }

    public static boolean isNumeric(String strNum) {

        Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

        if (strNum == null) {
            return false;
        }
        return pattern.matcher(strNum).matches();
    }
}