package GUI.PanelPacjenta;

import Data.Connection;
import GUI.Skladowe.Calendar;
import GUI.Skladowe.PanelOgolny;
import GUI.Skladowe.Powierzchnia;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class PanelUmawianieWizyty extends PanelOgolny {
    private static String tekst = "";
    public static void setTekst(String tekst) {
        PanelUmawianieWizyty.tekst = tekst;
    }

    private static String specjal;
    private static String doctor;

    public static String getSpecjal() {
        return specjal;
    }

    public static String getDoctor() {
        return doctor;
    }

    public PanelUmawianieWizyty(String specjalizacja) throws IOException {

        JLabel LabelWyswietlListeLekarzy = new JLabel("Wyswietl liste lekarzy");
        JLabel LabelTypLekarza = new JLabel(specjalizacja);
        LabelWyswietlListeLekarzy.setFont(new Font("Arial", Font.BOLD, 22));
        JButton ButtonWroc = new JButton("Wroc");
        ButtonWroc.addActionListener(e -> {
            try {
                Powierzchnia.getRamka().setTab(new PanelPacjent());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        int y=0;
        for (int i = 0; i < Connection.getDoctors().size(); i++) {
            if(Objects.equals(Connection.getDoctors().get(i).getSpecialization().getName(), specjalizacja)) {
               // specjal=Connection.getDoctors().get(i).getSpecialization().getName();
               // doctor=Connection.getDoctors().get(i).getFirstName()+" "+Connection.getDoctors().get(i).getFirstName();
                JButton buttonlekarz = new JButton(Connection.getDoctors().get(i).getFirstName()+" "+Connection.getDoctors().get(i).getSurname());
                add(buttonlekarz, getC(6, 6 + 2 * y, 0, 0, 0, 0));
                buttonlekarz.addActionListener(e -> {
                    new Calendar(new JFrame(specjalizacja + ": " + buttonlekarz.getText()));
                });
                y++;
            }
        }
        add(LabelWyswietlListeLekarzy, getC(6, 2, 0, 0, 0, 0));
        add(LabelTypLekarza, getC(6, 4, 0, 0, 0, 0));
        add(ButtonWroc, getC(1, 14, 0, 0, 0, 0));
    }
}
