package Data;

import GUI.Menu.PanelMenu;
import GUI.Menu.PanelRejestracja;
import GUI.Skladowe.Powierzchnia;
import Model.Patient;
import Model.Specialization;
import UI.UserAccount;

import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.regex.Pattern;

import static UI.UserAccount.setSafeCreatePatient;

public class UserData {

    private UserData() {
    }

    public static String enterFirstName() throws InputMismatchException {
        String firstName = PanelRejestracja.getTextFieldImie().getText();
        if (firstName.length() == 0) {
            setSafeCreatePatient(true);
            PanelRejestracja.getTextFieldNazwisko().setBackground(Color.RED);
        } else {
            PanelRejestracja.getTextFieldNazwisko().setBackground(Color.WHITE);
            firstName = nameValidate(firstName);
        }
        return firstName;
    }

    public static String enterSurname() throws InputMismatchException {

        String surname = PanelRejestracja.getTextFieldNazwisko().getText();
        if (surname.length() == 0) {
            setSafeCreatePatient(true);
            PanelRejestracja.getTextFieldNazwisko().setBackground(Color.RED);
        } else {
            PanelRejestracja.getTextFieldNazwisko().setBackground(Color.WHITE);
            surname = nameValidate(surname);
        }
        return surname;
    }

    public static String enterPassword() throws InputMismatchException {

        String password;
        password = PanelRejestracja.getTextFieldHaslo().getText();
        if (passwordValidate(password)) {
            PanelRejestracja.getTextFieldHaslo().setBackground(Color.RED);
            setSafeCreatePatient(true);
        } else {
            PanelRejestracja.getTextFieldHaslo().setBackground(Color.WHITE);
        }
        return password;
    }

    public static String enterEmail() throws InputMismatchException {

        String email;
        email = PanelRejestracja.getTextFieldAdresEmail().getText();
        if (emailValidate(email)) {
            PanelRejestracja.getTextFieldAdresEmail().setBackground(Color.RED);
            setSafeCreatePatient(true);
        } else {
            PanelRejestracja.getTextFieldAdresEmail().setBackground(Color.WHITE);
        }
        return email;
    }

    public static LocalDate enterBirthdate() throws InputMismatchException {

        String birthdate;
        birthdate = PanelRejestracja.getTextFieldDataUrodzenia().getText();
        if (dateValidate(birthdate)) {
            PanelRejestracja.getTextFieldDataUrodzenia().setBackground(Color.RED);
            setSafeCreatePatient(true);
        } else {
            PanelRejestracja.getTextFieldDataUrodzenia().setBackground(Color.WHITE);
        }
        try {
            return LocalDate.parse(birthdate);
        }catch (DateTimeParseException e){
            PanelRejestracja.getTextFieldDataUrodzenia().setBackground(Color.RED);
            setSafeCreatePatient(true);
        }
        return null;
    }

    public static String enterPersonalIdNumber() throws InputMismatchException {

        String personalIdNumber;

        System.out.print("Enter personal id number: ");
        personalIdNumber = PanelRejestracja.getTextFieldPesel().getText();
        if (personalIDValidate(personalIdNumber)) {
            PanelRejestracja.getTextFieldPesel().setBackground(Color.RED);
            setSafeCreatePatient(true);
        } else {
            PanelRejestracja.getTextFieldPesel().setBackground(Color.WHITE);
        }
        return personalIdNumber;
    }

    public static String enterContactNumber() throws InputMismatchException {

        String contactNumber;

        contactNumber=PanelRejestracja.getTextFieldTelefon().getText();
        if (phoneNumberValidate(contactNumber)) {
            PanelRejestracja.getTextFieldTelefon().setBackground(Color.RED);
            setSafeCreatePatient(true);
        } else {
            PanelRejestracja.getTextFieldTelefon().setBackground(Color.WHITE);
        }
        return contactNumber;
    }

    public static String enterAddressCity() throws InputMismatchException {

        System.out.print("Enter the city you live in: ");
        String addressCity = PanelRejestracja.getTextFieldMiastoZamieszkania().getText();
        if (PanelRejestracja.getTextFieldMiastoZamieszkania().getText().length() == 0) {
            PanelRejestracja.getTextFieldMiastoZamieszkania().setBackground(Color.RED);
            setSafeCreatePatient(true);
        } else {
            PanelRejestracja.getTextFieldMiastoZamieszkania().setBackground(Color.WHITE);
            addressCity = PanelRejestracja.getTextFieldMiastoZamieszkania().getText();
        }
        return addressCity;
    }

    public static String enterAddressStreet() throws InputMismatchException {

        System.out.print("Enter the street you live in: ");
        String addressStreet = PanelRejestracja.getTextFieldAdresZamieszkania().getText();
        if (addressStreet.length() == 0) {
            PanelRejestracja.getTextFieldAdresZamieszkania().setBackground(Color.RED);
            setSafeCreatePatient(true);
        } else {
            PanelRejestracja.getTextFieldAdresZamieszkania().setBackground(Color.WHITE);
        }

        return addressStreet;
    }

    public static int enterAddressHouseNumber() throws InputMismatchException {

        int addressHouseNumber = 0;
        if (PanelRejestracja.getTextFieldNumerZamieszkania().getText().length() == 0) {
            PanelRejestracja.getTextFieldNumerZamieszkania().setBackground(Color.RED);
            setSafeCreatePatient(true);
        } else {
            addressHouseNumber = Integer.parseInt(PanelRejestracja.getTextFieldNumerZamieszkania().getText());
            PanelRejestracja.getTextFieldNumerZamieszkania().setBackground(Color.WHITE);
        }
        return addressHouseNumber;
    }

    public static char enterGender() throws InputMismatchException {

        char gender = PanelRejestracja.getGender();
        if (PanelRejestracja.getGender() == 'b') {
            PanelRejestracja.getRadioButtonKobieta().setBackground(Color.RED);
            PanelRejestracja.getRadioButtonKobieta().setOpaque(true);
            PanelRejestracja.getRadioButtonMezczyzna().setBackground(Color.RED);
            PanelRejestracja.getRadioButtonMezczyzna().setOpaque(true);
            setSafeCreatePatient(true);
        } else {
            PanelRejestracja.getRadioButtonKobieta().setBackground(Color.GRAY);
            PanelRejestracja.getRadioButtonKobieta().setOpaque(false);
            PanelRejestracja.getRadioButtonMezczyzna().setBackground(Color.GRAY);
            PanelRejestracja.getRadioButtonMezczyzna().setOpaque(false);
        }
        return gender;
    }

    public static int enterWeight() throws InputMismatchException {
        int weight = 0;
        try {
            weight = Integer.parseInt(PanelRejestracja.getTextFieldWaga().getText());
            if (positiveNumberValidate(weight)) {
                PanelRejestracja.getTextFieldWaga().setBackground(Color.RED);
                setSafeCreatePatient(true);
            } else {
                PanelRejestracja.getTextFieldWaga().setBackground(Color.WHITE);
            }
        } catch (NumberFormatException e) {
            PanelRejestracja.getTextFieldWaga().setBackground(Color.RED);
            setSafeCreatePatient(true);
        }
        return weight;
    }

    public static int enterHeight() throws InputMismatchException {
        int height = 0;
        try {
            height = Integer.parseInt(PanelRejestracja.getTextFieldWzrost().getText());
            if (positiveNumberValidate(height)) {
                PanelRejestracja.getTextFieldWzrost().setBackground(Color.RED);
                setSafeCreatePatient(true);
            } else {
                PanelRejestracja.getTextFieldWzrost().setBackground(Color.WHITE);
            }
        } catch (NumberFormatException e) {
            PanelRejestracja.getTextFieldWzrost().setBackground(Color.RED);
            setSafeCreatePatient(true);
        }
        return height;
    }

    public static String enterBloodGroup() throws InputMismatchException {

        String bloodGroup = (String) PanelRejestracja.getComboBoxBloodGroup().getSelectedItem();
        if (Objects.equals(bloodGroup, "Wybierz grupe krwi")) {
            PanelRejestracja.getComboBoxBloodGroup().setBackground(Color.RED);
            setSafeCreatePatient(true);
        } else {
            PanelRejestracja.getComboBoxBloodGroup().setBackground(Color.WHITE);
        }
        return bloodGroup;
    }


    public static Specialization enterSpecialization() throws InputMismatchException {
        return new Specialization(UserAccount.getSpetiality()[PanelRejestracja.getComboBoxSpetialities().getSelectedIndex()]);
    }

    public static int enterID() throws InputMismatchException {

        int doctorID = 0;
        try {
            doctorID = Integer.parseInt(PanelRejestracja.getTextFieldDoctorID().getText());
            if (fourDigitIDValidation(doctorID)) {
                PanelRejestracja.getTextFieldDoctorID().setBackground(Color.RED);
                UserAccount.setSafeCreatePatient(true);
            } else {
                PanelRejestracja.getTextFieldDoctorID().setBackground(Color.white);
            }
        }catch (NumberFormatException e){
            PanelRejestracja.getTextFieldDoctorID().setBackground(Color.RED);
            UserAccount.setSafeCreatePatient(true);
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