package GUI.Menu;

import GUI.Skladowe.PanelOgolny;
import GUI.Skladowe.PanelPusty;
import UI.UserAccount;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class PanelRejestracja extends PanelOgolny {
    private static JLabel LabelRejestracja = new JLabel("<html>Register<br/> as Patient</html");
    private static JLabel LabelImie = new JLabel("Imie: ");
    private static JTextField TextFieldImie = new JTextField();
    private static JLabel LabelAdresEmail = new JLabel("Adres Email ");
    private static JTextField TextFieldAdresEmail = new JTextField();
    private static JLabel LabelDoctorID = new JLabel("ID: ");
    private static JTextField TextFieldDoctorID = new JTextField();
    private static JLabel LabelTelefon = new JLabel("Nr Telefonu: ");
    private static JTextField TextFieldTelefon = new JTextField();
    private static JLabel LabelDataUrodzenia = new JLabel("Data Urodzenia: ");
    private static JTextField TextFieldDataUrodzenia = new HintTextField("YYYY-MM-DD");
    private static JLabel LabelAdresZamieszkania = new JLabel("Adres Zamieszkania: ");
    private static JTextField TextFieldAdresZamieszkania = new JTextField();
    private static JLabel LabelMiastoZamieszkania = new JLabel("Miasto Zamieszkania: ");
    private static JTextField TextFieldMiastoZamieszkania = new JTextField();
    private static JLabel LabelNumerZamieszkania = new JLabel("Numer Zamieszkania: ");
    private static JTextField TextFieldNumerZamieszkania = new JTextField();
    private static JLabel LabelWaga = new JLabel("Waga: ");
    private static JTextField TextFieldWaga = new JTextField();
    private static JLabel LabelWzrost = new JLabel("Wzrost: ");
    private static JTextField TextFieldWzrost = new JTextField();
    private static JLabel LabelGrupaKrwi = new JLabel("Grupa Krwi: ");
    private static JLabel LabelSpetiality = new JLabel("Spetiality: ");
    private static JTextField TextFieldGrupaKrwi = new JTextField();
    private static JLabel LabelNazwisko = new JLabel("Nazwisko: ");
    private static JTextField TextFieldNazwisko = new JTextField();
    private static JLabel LabelHaslo = new JLabel("Haslo: ");
    private static JTextField TextFieldHaslo = new JTextField();
    private static JLabel LabelPesel = new JLabel("Pesel: ");
    private static JTextField TextFieldPesel = new JTextField();
    private static JLabel LabelWybierzPlec = new JLabel("Wybierz plec: ");
    private static JButton ButtonRejestruj = new JButton("Rejestruj");
    private static JRadioButton RadioButtonKobieta = new JRadioButton("Kobieta");
    private static JRadioButton RadioButtonMezczyzna = new JRadioButton("Mezczyzna");
    private static ButtonGroup group = new ButtonGroup();
    private static JComboBox ComboBoxBloodGroup = new JComboBox(UserAccount.getBloodGroups());

    public static JComboBox getComboBoxBloodGroup() {
        return ComboBoxBloodGroup;
    }

    public static JComboBox ComboBoxSpetialities = new JComboBox<>(UserAccount.getSpetiality());

    public static ButtonGroup getGroup() {
        return group;
    }

    public static JRadioButton getRadioButtonKobieta() {
        return RadioButtonKobieta;
    }

    public static JRadioButton getRadioButtonMezczyzna() {
        return RadioButtonMezczyzna;
    }

    public static JTextField getTextFieldDoctorID() {
        return TextFieldDoctorID;
    }

    public PanelRejestracja() throws IOException {
        JButton ButtonAsDoctor = new JButton("As Doctor");
        JButton ButtonAsPatient = new JButton("As Patient");
        ButtonAsDoctor.setEnabled(true);
        ButtonAsPatient.setEnabled(false);
        ComboBoxSpetialities.setVisible(false);
        LabelSpetiality.setVisible(false);
        LabelDoctorID.setVisible(false);
        TextFieldDoctorID.setVisible(false);
        ButtonAsDoctor.addActionListener(e -> {
            LabelRejestracja.setText("<html>Register<br/> as Doctor</html");
            ButtonAsDoctor.setEnabled(false);
            ButtonAsPatient.setEnabled(true);
            ComboBoxSpetialities.setVisible(true);
            LabelSpetiality.setVisible(true);
            ComboBoxBloodGroup.setVisible(false);
            LabelGrupaKrwi.setVisible(false);
            LabelDoctorID.setVisible(true);
            TextFieldDoctorID.setVisible(true);
        });
        ButtonAsPatient.addActionListener(e -> {
            LabelRejestracja.setText("<html>Register<br/> as Patient</html");
            ButtonAsPatient.setEnabled(false);
            ButtonAsDoctor.setEnabled(true);
            ComboBoxSpetialities.setVisible(false);
            LabelSpetiality.setVisible(false);
            ComboBoxBloodGroup.setVisible(true);
            LabelGrupaKrwi.setVisible(true);
            LabelDoctorID.setVisible(false);
            TextFieldDoctorID.setVisible(false);
        });
        group.add(RadioButtonKobieta);
        group.add(RadioButtonMezczyzna);
        RadioButtonKobieta.setOpaque(false);
        RadioButtonMezczyzna.setOpaque(false);

        JComponent[] tab = {LabelImie, LabelNazwisko, LabelTelefon, LabelMiastoZamieszkania, LabelAdresZamieszkania, LabelNumerZamieszkania,
                LabelDataUrodzenia, LabelWzrost, TextFieldImie, TextFieldNazwisko, TextFieldTelefon, TextFieldMiastoZamieszkania, TextFieldAdresZamieszkania,
                TextFieldNumerZamieszkania, TextFieldDataUrodzenia, TextFieldWzrost, LabelNazwisko, LabelPesel, LabelGrupaKrwi, LabelWaga,
                LabelWybierzPlec, RadioButtonKobieta, TextFieldAdresEmail, TextFieldPesel, TextFieldGrupaKrwi, TextFieldWaga,
                new PanelPusty(), RadioButtonMezczyzna, TextFieldHaslo};
        RadioButtonKobieta.setHorizontalAlignment(SwingConstants.RIGHT);
        for (int i = 0; i < 16; i++) {
            if (tab[i].getClass() == JLabel.class) {
                ((JLabel) tab[i]).setHorizontalAlignment(SwingConstants.RIGHT);
            }
            if (i < 8) {
                add(tab[i], getC(1, i + 4, 0, 0, 20, 0));
            } else {
                add(tab[i], getC(2, i - 4, 0, 0, 200, 0));
            }
            add(TextFieldPesel, getC(6, 4, 0, 0, 0, 0));
            LabelPesel.setHorizontalAlignment(SwingConstants.RIGHT);
            add(LabelPesel, getC(5, 4, 0, 0, 0, 0));
            LabelAdresEmail.setHorizontalAlignment(SwingConstants.RIGHT);
            add(TextFieldAdresEmail, getC(6, 5, 0, 0, 200, 0));
            add(LabelAdresEmail, getC(5, 5, 0, 0, 0, 0));
            add(LabelHaslo, getC(5, 6, 0, 0, 0, 0));
            add(TextFieldHaslo, getC(6, 6, 0, 0, 0, 0));
            LabelWaga.setHorizontalAlignment(SwingConstants.RIGHT);
            add(TextFieldWaga, getC(6, 8, 0, 0, 0, 0));
            add(LabelWaga, getC(5, 8, 0, 0, 0, 0));
            LabelGrupaKrwi.setHorizontalAlignment(SwingConstants.RIGHT);
            add(LabelGrupaKrwi, getC(5, 7, 0, 0, 0, 0));
            LabelSpetiality.setHorizontalAlignment(SwingConstants.RIGHT);
            add(LabelSpetiality, getC(5, 7, 0, 0, 0, 0));
            add(ComboBoxBloodGroup, getC(6, 7, 0, 0, 0, 0));
            add(ComboBoxSpetialities, getC(6, 7, 0, 0, 0, 0));
            LabelHaslo.setHorizontalAlignment(SwingConstants.RIGHT);
            add(RadioButtonKobieta, getC(5, 10, 0, 0, 0, 0));
            add(RadioButtonMezczyzna, getC(6, 10, 0, 0, 0, 0));
            add(LabelDoctorID,getC(5,11,0,0,0,0));
            add(TextFieldDoctorID,getC(6,11,0,0,0,0));
            LabelDoctorID.setHorizontalAlignment(SwingConstants.RIGHT);
            LabelWybierzPlec.setHorizontalAlignment(SwingConstants.RIGHT);
            add(LabelWybierzPlec, getC(5, 9, 0, 0, 100, 0));
            add(ButtonAsPatient, getC(1, 2, 0, 0, 0, 0));
            add(ButtonAsDoctor, getC(1, 3, 0, 0, 0, 0));
            add(getButtonCofnij(), getC(1, 15, 0, 0, 0, 0));
        }
        LabelRejestracja.setFont(new Font("Now", Font.BOLD, 30));
        add(LabelRejestracja, getC(5, 1, 0, 0, 0, 0));
        add(ButtonRejestruj, getC(5, 13, 0, 0, 0, 0));
        ButtonRejestruj.addActionListener(e -> {
            if (ButtonAsPatient.isEnabled()) {
                try {
                    UserAccount.doctorRegister();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            } else if (ButtonAsDoctor.isEnabled()) {
                try {
                    UserAccount.patientRegister();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        getButtonCofnij().addActionListener(e -> {
            for (int i = 0; i < tab.length; i++) {
                if (tab[i] instanceof JTextField) {
                    ((JTextField) tab[i]).setText("");
                    tab[i].setBackground(Color.WHITE);
                }
            }
            ComboBoxBloodGroup.setBackground(Color.WHITE);
            ComboBoxBloodGroup.setSelectedIndex(0);
        });
    }
    // static {
    //     try {
    //         panelrejestracja = new PanelRejestracja();
    //     } catch (IOException e) {
    //         throw new RuntimeException(e);
    //     }
    // }

    public static JTextField getTextFieldImie() {
        return TextFieldImie;
    }

    public static JTextField getTextFieldAdresEmail() {
        return TextFieldAdresEmail;
    }

    public static JTextField getTextFieldTelefon() {
        return TextFieldTelefon;
    }

    public static JTextField getTextFieldDataUrodzenia() {
        return TextFieldDataUrodzenia;
    }

    public static JTextField getTextFieldAdresZamieszkania() {
        return TextFieldAdresZamieszkania;
    }

    public static JTextField getTextFieldWaga() {
        return TextFieldWaga;
    }

    public static JTextField getTextFieldWzrost() {
        return TextFieldWzrost;
    }

    public static JTextField getTextFieldGrupaKrwi() {
        return TextFieldGrupaKrwi;
    }

    public static JTextField getTextFieldNazwisko() {
        return TextFieldNazwisko;
    }

    public static JTextField getTextFieldPesel() {
        return TextFieldPesel;
    }

    public static JTextField getTextFieldMiastoZamieszkania() {
        return TextFieldMiastoZamieszkania;
    }

    public static JTextField getTextFieldNumerZamieszkania() {
        return TextFieldNumerZamieszkania;
    }

    public static JTextField getTextFieldHaslo() {
        return TextFieldHaslo;
    }

    public static char getGender() {
        if (RadioButtonMezczyzna.isSelected()) {
            return 'M';
        } else if (RadioButtonKobieta.isSelected()) {
            return 'K';
        } else {
            return 'b';
        }
    }

    public static JComboBox getComboBoxSpetialities() {
        return ComboBoxSpetialities;
    }
}
