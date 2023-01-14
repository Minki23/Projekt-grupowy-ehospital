package GUI.PanelPacjenta;

import GUI.Menu.PanelMenu;
import GUI.Menu.PanelRejestracja;
import GUI.Skladowe.PanelOgolny;
import GUI.Skladowe.Powierzchnia;
import UI.UserAccount;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Objects;

import static UI.UserAccount.*;

public class PanelWyswietlProfil extends PanelOgolny {
    //konstruktor
    public PanelWyswietlProfil() throws IOException {
        //Buttony
        JButton ButtonWyloguj = new JButton("Wyloguj");
        JButton ButtonZmienTelefon = new JButton("Zmien");
        JButton ButtonZmienWage = new JButton("Zmien");
        JButton ButtonZmienWzrost = new JButton("Zmien");
        JButton ButtonZmienAdres = new JButton("Zmien");
        JButton ButtonZmienEmail = new JButton("Zmien");
        JButton ButtonZatwierdz = new JButton("Zatwierdz Zmiany");
        ButtonZatwierdz.setVisible(false);
        //Pola danych
        JLabel LabelPlec=new JLabel("Plec:");
        JLabel LabelgetPlec=new JLabel(String.valueOf(UserAccount.getLoggedPatient().getGender()).toUpperCase(Locale.ROOT));
        JLabel LabelWyswietlProfil = new JLabel("Twoj Profil");
        LabelWyswietlProfil.setFont(new Font("Now", Font.BOLD, 20));

        JLabel LabelImieNazwisko = new JLabel("Imie i Nazwisko: ");
        JLabel LabelgetImieNazwisko = new JLabel(UserAccount.getLoggedPatient().getFirstName()+" "+ UserAccount.getLoggedPatient().getSurname());

        JLabel LabelAdresEmail = new JLabel("Adres Email: ");
        JLabel LabelgetAdresEmail = new JLabel(UserAccount.getLoggedPatient().getEmail());
        JTextField TextFieldAdresEmail = new JTextField(LabelgetAdresEmail.getText());
        TextFieldAdresEmail.setVisible(false);

        JLabel LabelGrupaKrwi = new JLabel("Grupa Krwi: ");
        JLabel LabelgetGrupaKrwi = new JLabel(UserAccount.getLoggedPatient().getBloodGroup());

        JLabel LabelTelefon = new JLabel("Nr Telefonu: ");
        JLabel LabelgetTelefon = new JLabel(UserAccount.getLoggedPatient().getContactNumber());
        JTextField TextFieldTelefon = new JTextField(LabelgetTelefon.getText());
        TextFieldTelefon.setVisible(false);

        JLabel LabelAdresZamieszkania = new JLabel("Adres Zamieszkania: ");
        JLabel LabelgetAdresZamieszkania = new JLabel(UserAccount.getLoggedPatient().getCity()+" "+UserAccount.getLoggedPatient().getStreet()+" "+UserAccount.getLoggedPatient().getHouseNumber());
        JTextField TextFieldAdresZamieszkania = new JTextField(LabelgetAdresZamieszkania.getText());
        TextFieldAdresZamieszkania.setVisible(false);

        JLabel LabelWaga = new JLabel("Waga: ");
        JLabel LabelgetWaga = new JLabel(UserAccount.getLoggedPatient().getWeight() + " kg");
        JTextField TextFieldWaga = new JTextField();
        TextFieldWaga.setVisible(false);

        JLabel LabelWzrost = new JLabel("Wzrost: ");
        JLabel LabelgetWzrost = new JLabel(UserAccount.getLoggedPatient().getHeight() + " cm");
        JTextField TextFieldWzrost = new JTextField();
        TextFieldWzrost.setVisible(false);

        JLabel LabelPesel = new JLabel("Pesel: ");
        JLabel LabelgetPesel = new JLabel(UserAccount.getLoggedPatient().getPersonalIdNumber());

        String password = PanelRejestracja.getTextFieldHaslo().getText();
        if (!passwordValidate(password)) {
            UserAccount.getLoggedPatient().setPassword(password);
        }

        //Button actions
        ButtonZmienAdres.addActionListener(e -> {
            LabelgetAdresZamieszkania.setVisible(false);
            ButtonZmienAdres.setVisible(false);
            TextFieldAdresZamieszkania.setVisible(true);
            ButtonZatwierdz.setVisible(true);
        });
        ButtonZmienEmail.addActionListener(e -> {
            LabelgetAdresEmail.setVisible(false);
            TextFieldAdresEmail.setVisible(true);
            ButtonZmienEmail.setVisible(false);
            ButtonZatwierdz.setVisible(true);
        });
        ButtonZmienWage.addActionListener(e -> {
            LabelgetWaga.setVisible(false);
            TextFieldWaga.setVisible(true);
            ButtonZmienWage.setVisible(false);
            ButtonZatwierdz.setVisible(true);
        });
        ButtonZmienWzrost.addActionListener(e -> {
            LabelgetWzrost.setVisible(false);
            TextFieldWzrost.setVisible(true);
            ButtonZmienWzrost.setVisible(false);
            ButtonZatwierdz.setVisible(true);
        });
        ButtonZmienTelefon.addActionListener(e -> {
            LabelgetTelefon.setVisible(false);
            ButtonZmienTelefon.setVisible(false);
            TextFieldTelefon.setVisible(true);
            ButtonZatwierdz.setVisible(true);
        });
        ButtonZmienAdres.addActionListener(e -> {
            LabelgetAdresZamieszkania.setVisible(false);
            TextFieldAdresZamieszkania.setVisible(true);
            ButtonZmienAdres.setVisible(false);
            ButtonZatwierdz.setVisible(true);
        });
        ButtonZatwierdz.addActionListener(e -> {
            if (TextFieldAdresZamieszkania.isVisible()) {
                LabelgetAdresZamieszkania.setText(TextFieldAdresZamieszkania.getText());
                ButtonZmienAdres.setVisible(true);
                TextFieldAdresZamieszkania.setVisible(false);
                LabelgetAdresZamieszkania.setVisible(true);
            }
            if (TextFieldAdresEmail.isVisible()) {
                String email = TextFieldAdresEmail.getText();
                if (!emailValidate(email)) {
                    UserAccount.getLoggedPatient().setEmail(email);
                    LabelgetAdresEmail.setText(UserAccount.getLoggedPatient().getEmail());
                    repaint();
                }
                TextFieldAdresEmail.setText(LabelgetAdresEmail.getText());
                ButtonZmienEmail.setVisible(true);
                TextFieldAdresEmail.setVisible(false);
                LabelgetAdresEmail.setVisible(true);
            }
            if (TextFieldWaga.isVisible()) {
                try {
                    int weight = Integer.parseInt(TextFieldWaga.getText());
                    if (heightValidate(weight)) {
                        UserAccount.getLoggedPatient().setWeight(weight);
                        LabelgetWaga.setText(weight +" kg");
                    }
                    TextFieldWaga.setText(String.valueOf(weight));
                } catch (NumberFormatException ignored) {}
                ButtonZmienWage.setVisible(true);
                TextFieldWaga.setVisible(false);
                LabelgetWaga.setVisible(true);
            }
            if (TextFieldWzrost.isVisible()) {
                try {
                    int height = Integer.parseInt(TextFieldWzrost.getText());
                    if (heightValidate(height)) {
                        UserAccount.getLoggedPatient().setHeight(height);
                        LabelgetWzrost.setText(height +" cm");
                    }
                } catch (NumberFormatException ignored) {}
                TextFieldWzrost.setText(LabelgetWzrost.getText());
                ButtonZmienWzrost.setVisible(true);
                TextFieldWzrost.setVisible(false);
                LabelgetWzrost.setVisible(true);
            }
            if (TextFieldTelefon.isVisible()) {
                ButtonZmienTelefon.setVisible(true);
                String contactNumber = TextFieldTelefon.getText();
                if (!phoneNumberValidate(contactNumber)) {
                    getLoggedPatient().setContactNumber(contactNumber);
                    LabelgetTelefon.setText(contactNumber);
                    repaint();
                }
                TextFieldTelefon.setText(LabelgetTelefon.getText());
                TextFieldTelefon.setVisible(false);
                LabelgetTelefon.setVisible(true);
            }
            ButtonZatwierdz.setVisible(false);
        });
        ButtonWyloguj.addActionListener(e -> {
            PanelChorobyILeki.getHistoria().dispatchEvent(new WindowEvent(PanelChorobyILeki.getHistoria(), WindowEvent.WINDOW_CLOSING));
            PanelChorobyILeki.getLeki().dispatchEvent(new WindowEvent(PanelChorobyILeki.getLeki(), WindowEvent.WINDOW_CLOSING));
            try {
                Powierzchnia.getRamka().set(new PanelMenu());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        //Dodawanie elemoentow
        add(LabelWyswietlProfil, getC(7, 0, 0, 0, 0, 0));
        add(LabelPesel, getC(5, 2, 0, 0, 0, 0));
        add(LabelgetPesel, getC(5, 3, 0, 0, 0, 0));
        add(LabelAdresZamieszkania, getC(5, 5, 0, 0, 0, 0));
        add(LabelgetAdresZamieszkania, getC(5, 6, 0, 0, 0, 0));
        add(TextFieldAdresZamieszkania, getC(5, 6, 0, 0, 0, 0));
        add(ButtonZmienAdres, getC(6, 5, 0, 0, 0, -10));
        add(LabelTelefon, getC(5, 8, 0, 0, 0, 0));
        add(LabelgetTelefon, getC(5, 9, 0, 0, 0, 0));
        add(TextFieldTelefon, getC(5, 9, 0, 0, 0, 0));
        add(LabelGrupaKrwi, getC(5, 11, 0, 0, 0, 0));
        add(LabelgetGrupaKrwi, getC(5, 12, 0, 0, 0, 0));
        add(ButtonZmienTelefon, getC(6, 8, 0, 0, 0, -10));
        add(LabelImieNazwisko, getC(10, 2, 0, 0, 0, 0));
        add(LabelgetImieNazwisko, getC(10, 3, 0, 0, 0, 0));
        add(LabelPlec,getC(11,2,0,0,0,0));
        add(LabelgetPlec,getC(11,3,0,0,0,0));
        add(LabelAdresEmail, getC(10, 5, 0, 0, 100, 0));
        add(LabelgetAdresEmail, getC(10, 6, 0, 0, 0, 0));
        add(TextFieldAdresEmail, getC(10, 6, 0, 0, 0, -10));
        add(ButtonZmienEmail, getC(11, 5, 0, 0, 0, -10));
        add(LabelWaga, getC(10, 8, 0, 0, 0, 0));
        add(LabelgetWaga, getC(10, 9, 0, 0, 0, 0));
        add(TextFieldWaga, getC(10, 9, 0, 0, 0, 0));
        add(ButtonZmienWage, getC(11, 8, 0, 0, 0, -10));
        add(LabelWzrost, getC(10, 11, 0, 0, 0, 0));
        add(LabelgetWzrost, getC(10, 12, 0, 0, 0, 0));
        add(TextFieldWzrost, getC(10, 12, 0, 0, 0, 0));
        add(ButtonZmienWzrost, getC(11, 11, 0, 0, 0, -10));
        add(ButtonZatwierdz, getC(7, 14, 0, 0, 0, -10));
        add(ButtonWyloguj, getC(14, 1, 0, 0, 0, 0));
    }
}
