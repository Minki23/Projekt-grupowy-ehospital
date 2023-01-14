package GUI.PanelPacjenta;

import GUI.Menu.PanelMenu;
import GUI.Skladowe.PanelOgolny;
import GUI.Skladowe.Powierzchnia;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class PanelUmowWizyte extends PanelOgolny {
    private static JLabel LabelWybranaData=new JLabel();
    public static JLabel getLabelWybranaData() {
        return LabelWybranaData;
    }
    public PanelUmowWizyte() throws IOException {
        JLabel LabelUmowNaWizyte = new JLabel("<html>Umow na wizyte<br> Wybierz lekarza:<html>");
        LabelUmowNaWizyte.setFont(new Font("Nul", Font.BOLD, 26));
        //przyciski lekarzy i ich akcje
        JButton ButtonLekarzRodzinny = new JButton("family doctor");
        ButtonLekarzRodzinny.addActionListener(e -> {
            try {
                Powierzchnia.getRamka().set(new PanelUmawianieWizyty("family doctor"));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        JButton ButtonLaryngolog = new JButton("Laryngolog");
        ButtonLaryngolog.addActionListener(e -> {
            PanelUmawianieWizyty.setTekst("Laryngolog");
            try {
                Powierzchnia.getRamka().set(new PanelUmawianieWizyty("Laryngolog"));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        JButton ButtonOrtopeda = new JButton("orthopedist");
        ButtonOrtopeda.addActionListener(e -> {
            try {
                Powierzchnia.getRamka().set(new PanelUmawianieWizyty("orthopedist"));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        JButton ButtonDermatolog = new JButton("Dermatolog");
        ButtonDermatolog.addActionListener(e -> {
            PanelUmawianieWizyty.setTekst("Dermatolog");
            try {
                Powierzchnia.getRamka().set(new PanelUmawianieWizyty("Dermatolog"));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        JButton ButtonKardiolog = new JButton("heart specialist");
        ButtonKardiolog.addActionListener(e -> {
            try {
                Powierzchnia.getRamka().set(new PanelUmawianieWizyty("heart specialist"));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        JButton ButtonOnkolog = new JButton("Onkolog");
        ButtonOnkolog.addActionListener(e -> {
            try {
                Powierzchnia.getRamka().set(new PanelUmawianieWizyty("Onkolog"));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        JButton ButtonGinekolog = new JButton("gynecologist");
        ButtonGinekolog.addActionListener(e -> {
            try {
                Powierzchnia.getRamka().set(new PanelUmawianieWizyty("gynecologist"));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        JButton ButtonOkulista = new JButton("Okulista");
        ButtonOkulista.addActionListener(e -> {
            try {
                Powierzchnia.getRamka().set(new PanelUmawianieWizyty("Okulista"));
            } catch (IOException ignored) {
            }
        });
        JButton ButtonWyloguj = new JButton("Wyloguj");
        ButtonWyloguj.addActionListener(e -> {
            PanelChorobyILeki.getHistoria().dispatchEvent(new WindowEvent(PanelChorobyILeki.getHistoria(), WindowEvent.WINDOW_CLOSING));
            PanelChorobyILeki.getLeki().dispatchEvent(new WindowEvent(PanelChorobyILeki.getLeki(), WindowEvent.WINDOW_CLOSING));
            try {
                Powierzchnia.getRamka().set(new PanelMenu());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        //dodawanie
        add(LabelUmowNaWizyte, getC(7, 2, 0, 0, 0, 0));
        add(ButtonLekarzRodzinny, getC(6, 4, 0, 0, 20, 10));
        add(ButtonOrtopeda, getC(6, 6, 0, 0, 40, 10));
        add(ButtonKardiolog, getC(6, 8, 0, 0, 40, 10));
        add(ButtonGinekolog, getC(6, 10, 0, 0, 40, 10));
        add(ButtonLaryngolog, getC(8, 4, 0, 0, 40, 10));
        add(ButtonDermatolog, getC(8, 6, 0, 0, 40, 10));
        add(ButtonOnkolog, getC(8, 8, 0, 0, 40, 10));
        add(ButtonOkulista, getC(8, 10, 0, 0, 40, 10));
        add(ButtonWyloguj, getC(14, 1, 0, 0, 0, 10));
        add(LabelWybranaData,getC(6,14,0,0,0,0));
    }
}