package GUI.Menu;

import GUI.Skladowe.PanelOgolny;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;

public class PanelLogowanieLekarz extends PanelOgolny {
    private boolean PoprawneHaslo = false;

    public void setPoprawneHaslo(boolean poprawneHaslo) {
        PoprawneHaslo = poprawneHaslo;
    }

    public PanelLogowanieLekarz() throws IOException {
        //Buttony
        JButton ButtonZaloguj = new JButton("Zaloguj");
        //Labele
        JLabel LabelLogowaniePacjent = new JLabel("Logowanie Lekarz:");
        JLabel LabelPesel = new JLabel("ID");
        JLabel LabelHaslo = new JLabel("Haslo");
        JLabel LabelBlednyLoginLubHaslo = new JLabel("Bledne ID lub Haslo");
        LabelBlednyLoginLubHaslo.setForeground(Color.RED);
        LabelBlednyLoginLubHaslo.setVisible(false);
        //Akcje Buttonow
        ButtonZaloguj.addActionListener(e -> {
            if (!PoprawneHaslo) {
                LabelBlednyLoginLubHaslo.setVisible(true);
                repaint();
            }
        });
        //Textfieldy
        JTextField TextFieldIDLekarz = new JTextField();
        JPasswordField TextFieldHasloLekarz = new JPasswordField();
        TextFieldHasloLekarz.setEchoChar('*');
        //Checkbox
        JCheckBox CheckBoxPokazHaslo = new JCheckBox("Pokaz haslo");
        CheckBoxPokazHaslo.setOpaque(false);
        //Akcje CheckBoxow
        CheckBoxPokazHaslo.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    TextFieldHasloLekarz.setEchoChar((char) 0);
                } else {
                    TextFieldHasloLekarz.setEchoChar('*');
                }
            }
        });
        //Personalizacja obiektow
        add(CheckBoxPokazHaslo, getC(8, 7, 0, 0, 0, 0));
        add(LabelLogowaniePacjent, getC(7, 3, 0, 0, 10, 10));
        add(LabelPesel, getC(7, 4, 0, 0, 10, 10));
        add(TextFieldIDLekarz, getC(7, 5, 0, 0, 10, 10));
        add(LabelHaslo, getC(7, 6, 0, 0, 10, 10));
        add(TextFieldHasloLekarz, getC(7, 7, 0, 0, 10, 10));
        add(LabelBlednyLoginLubHaslo, getC(7, 8, 0, 0, 0, 0));
        add(ButtonZaloguj, getC(7, 9, 0, 0, 10, 10));
        add(getButtonCofnij(), getC(1, 14, 0, 0, 0, 0));
    }
}