package GUI.Menu;

import GUI.PanelPacjenta.PanelPacjent;
import GUI.Skladowe.PanelOgolny;
import GUI.Skladowe.Powierzchnia;
import UI.UserAccount;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;

public class PanelLogowaniePacjent extends PanelOgolny {
    //Buttony
    private static JButton ButtonZaloguj = new JButton("Zaloguj");
    private static JButton ButtonCofnij = new JButton("<-");

    //Labele
    private static JLabel LabelLogowaniePacjent = new JLabel("Logowanie Pacjent:");
    private static JLabel LabelPesel = new JLabel("Pesel");
    private static JLabel LabelHaslo = new JLabel("Haslo");
    private static JLabel LabelBlednyLoginLubHaslo = new JLabel("Bledny Login lub Haslo");
    //Textfieldy
    private static final JTextField TextFieldPeselPacjent = new JTextField();
    private static final JPasswordField TextFieldHasloPacjent = new JPasswordField();

    public static JTextField getTextFieldPeselPacjent() {
        return TextFieldPeselPacjent;
    }

    public static JPasswordField getTextFieldHasloPacjent() {
        return TextFieldHasloPacjent;
    }

    public PanelLogowaniePacjent() throws IOException {

        LabelBlednyLoginLubHaslo.setForeground(Color.RED);
        LabelBlednyLoginLubHaslo.setVisible(false);
        TextFieldHasloPacjent.setText("");
        TextFieldPeselPacjent.setText("");
        //Akcje Buttonow
        ButtonCofnij.addActionListener(e -> {
            try {
                Powierzchnia.getRamka().set(new PanelMenu());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        ButtonZaloguj.addActionListener(e -> {
            UserAccount.patientLogin();
            if (UserAccount.found) {
                try {
                    Powierzchnia.getRamka().setTab(new PanelPacjent());
                    UserAccount.found=false;
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            } else {
                LabelBlednyLoginLubHaslo.setVisible(true);
                repaint();
            }
        });
        TextFieldHasloPacjent.setEchoChar('*');
        //Checkbox
        JCheckBox CheckBoxPokazHaslo = new JCheckBox("Pokaz haslo");
        CheckBoxPokazHaslo.setOpaque(false);
        //Akcje CheckBoxow
        CheckBoxPokazHaslo.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    TextFieldHasloPacjent.setEchoChar((char) 0);
                } else {
                    TextFieldHasloPacjent.setEchoChar('*');
                }
            }
        });
        //Personalizacja obiektow
        add(CheckBoxPokazHaslo, getC(8, 7, 0, 0, 0, 0));

        add(LabelLogowaniePacjent, getC(7, 3, 0, 0, 10, 10));

        add(LabelPesel, getC(7, 4, 0, 0, 10, 10));

        add(TextFieldPeselPacjent, getC(7, 5, 0, 0, 10, 10));

        add(LabelHaslo, getC(7, 6, 0, 0, 10, 10));

        add(TextFieldHasloPacjent, getC(7, 7, 0, 0, 10, 10));

        add(LabelBlednyLoginLubHaslo, getC(7, 8, 0, 0, 0, 0));

        add(ButtonZaloguj, getC(7, 9, 0, 0, 10, 10));

        add(getButtonCofnij(), getC(1, 14, 0, 0, 0, 0));
    }
}
