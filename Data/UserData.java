package Data;

import Model.Specialization;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.regex.Pattern;

public class UserData {

    private UserData() {}

    public static String enterFirstName() throws InputMismatchException {

        System.out.print("Enter first name: ");
        String firstName = Input.getString();
        firstName = nameValidate(firstName);

        return firstName;
    }

    public static String enterSurname() throws InputMismatchException {

        System.out.print("Enter surname: ");
        String surname = Input.getString();
        surname = nameValidate(surname);

        return surname;
    }

    public static String enterPassword() throws InputMismatchException {

        String password;

        System.out.print("Enter password: ");
        while (true) {
            password = Input.getString();

            if (passwordValidate(password)) {
                System.out.println("Password must contains at least 8 characters!");
                System.out.print("Enter new password: ");
            } else {
                break;
            }
        }
        return password;
    }

    public static String enterEmail() throws InputMismatchException {

        String email;

        System.out.print("Enter email: ");
        while (true) {
            email = Input.getString();

            if (emailValidate(email)) {
                System.out.println("Invalid email");
                System.out.print("Enter correct email: ");
            } else {
                break;
            }
        }
        return email;
    }

    public static LocalDate enterBirthdate() throws InputMismatchException {

        String birthdate;

        System.out.print("Enter your date of birth (YYYY-MM-DD): ");
        while (true) {
            birthdate = Input.getString();

            if (dateValidate(birthdate)) {
                System.out.println("Invalid date");
                System.out.print("Enter correct date: ");
            } else {
                break;
            }
        }
        return LocalDate.parse(birthdate);
    }

    public static String enterPersonalIdNumber() throws InputMismatchException {

        String personalIdNumber;

        System.out.print("Enter personal id number: ");
        while (true) {
            personalIdNumber = Input.getString();

            if (personalIDValidate(personalIdNumber)) {
                System.out.println("Invalid personal ID number");
                System.out.print("Enter correct personal ID number: ");
            } else {
                break;
            }
        }
      return personalIdNumber;
    }

    public static String enterContactNumber() throws InputMismatchException {

        String contactNumber;

        System.out.print("Enter contact number: ");
        while (true) {
            contactNumber = Input.getString();

            if (phoneNumberValidate(contactNumber)) {
                System.out.println("Invalid phone number");
                System.out.print("Enter correct phone number: ");
            } else {
                break;
            }
        }
        return contactNumber;
    }

    public static String enterAddressCity() throws InputMismatchException {

        System.out.print("Enter the city you live in: ");
        String addressCity = Input.getString();
        addressCity = nameValidate(addressCity);

        return addressCity;
    }

    public static String enterAddressStreet() throws InputMismatchException {

        System.out.print("Enter the street you live in: ");
        String addressStreet = Input.getString();
        addressStreet = nameValidate(addressStreet);

        return addressStreet;
    }

    public static int enterAddressHouseNumber() throws InputMismatchException {

        int addressHouseNumber;

        System.out.print("Enter your house number: ");
        while (true) {
            addressHouseNumber = Input.getInt();

            if (positiveNumberValidate(addressHouseNumber)) {
                System.out.println("Invalid house number");
                System.out.print("Enter correct house number: ");
            } else {
                break;
            }
        }
        return addressHouseNumber;
    }

    public static char enterGender() throws InputMismatchException {

        char gender;

        System.out.println("Enter your gender:\nType M for male, or F for female");
        System.out.print(": ");
        while (true) {
            gender = Input.getChar();

            if (genderValidate(gender)) {
                System.out.println("Invalid input");
                System.out.print("Enter valid gender: ");
            } else {
                break;
            }
        }
      return gender;
    }

    public static int enterWeight() throws InputMismatchException {

        int weight;

        System.out.print("Enter your weight: ");
        while (true) {
            weight = Input.getInt();

            if (positiveNumberValidate(weight)) {
                System.out.println("Invalid input");
                System.out.print("Enter valid weight");
            } else {
                break;
            }
        }
        return weight;
    }

    public static int enterHeight() throws InputMismatchException {

        int height;

        System.out.print("Enter your height: ");
        while (true) {
            height = Input.getInt();

            if (heightValidate(height)) {
                System.out.println("Invalid input");
                System.out.print("Enter valid height");
            } else {
                break;
            }
        }
        return height;
    }

    public static String enterBloodGroup() throws InputMismatchException {

        int choice;
        String[] bloodTypes = { "A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-" };

        for (int i = 0; i < bloodTypes.length; i++) {

            System.out.println((i + 1) + ": " + bloodTypes[i]);
        }
        System.out.print("Choose your blood group: ");
        while (true) {
            choice = Input.getInt();

            if (choice < 1 || choice > bloodTypes.length) {
                System.out.println("Invalid input");
                System.out.print("Enter valid input: ");
            } else {
                break;
            }
        }
        return bloodTypes[choice - 1];
    }

    public static Specialization enterSpecialization() throws InputMismatchException {

        int choice;
        Specialization specialization;

        System.out.println("""
            Choose your specialization:
            \t1: family doctor
            \t2: heart specialist
            \t3: orthopedist
            \t4: gynecologist
            """);
        System.out.print(": ");
        while (true) {
            choice = Input.getInt();

            if (specializationValidate(choice)) {
                System.out.println("Invalid input");
                System.out.print("Enter valid choice: ");
            } else {
                break;
            }
        }
        switch (choice) {
            case 1 -> specialization = new Specialization("family doctor");
            case 2 -> specialization = new Specialization("heart specialist");
            case 3 -> specialization = new Specialization("orthopedist");
            case 4 -> specialization = new Specialization("gynecologist");
            default -> specialization = new Specialization();
        }
        return specialization;
    }

    public static int enterID() throws InputMismatchException {

        int doctorID;
        System.out.print("Enter your ID: ");

        while (true) {
            doctorID = Input.getInt();

            if (fourDigitIDValidation(doctorID)) {
                System.out.println("Invalid input");
                System.out.print("Enter valid doctorID: ");
            } else {
                break;
            }
        }
        return doctorID;
    }

    public static boolean fourDigitIDValidation(int doctorID) {

        return doctorID > 9999 || doctorID < 1000;
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

        return height < 0 || height > 250;
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
