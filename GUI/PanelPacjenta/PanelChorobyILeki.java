package GUI.PanelPacjenta;

import Data.Connection;
import GUI.Menu.PanelMenu;
import GUI.Skladowe.PanelOgolny;
import GUI.Skladowe.Powierzchnia;
import Model.Doctor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class PanelChorobyILeki extends PanelOgolny {
    private static boolean HistoriaOtwarta = false;
    private static boolean LekiOtwarte = false;
    private static final OknoHistoriaChorob Historia = new OknoHistoriaChorob();
    private static final OknoPrzypisaneLeki Leki = new OknoPrzypisaneLeki();

    public static OknoHistoriaChorob getHistoria() {
        return Historia;
    }

    public static OknoPrzypisaneLeki getLeki() {
        return Leki;
    }

    public static void setHistoriaOtwarta(boolean historiaOtwarta) {
        HistoriaOtwarta = historiaOtwarta;
    }

    public static void setLekiOtwarte(boolean lekiOtwarte) {
        LekiOtwarte = lekiOtwarte;
    }
    private static JComboBox ComboBoxLekarze= new JComboBox<>();
    private static Doctor doctor;

    public static Doctor getDoctor() {
        return doctor;
    }

    public PanelChorobyILeki() throws IOException {

        JLabel LabelOcenLekarza=new JLabel("Ocen Lekarza");
        JButton ButtonRateDoctor=new JButton("Ocen");
        JButton ButtonHistoriaChorob = new JButton("Historia Chorob");
        JButton ButtonPrzypisaneLeki = new JButton("Przypisane Leki");
        for(int i=0;i<Connection.getDoctors().size();i++){
            ComboBoxLekarze.addItem(Connection.getDoctors().get(i).getFirstName()+" "+Connection.getDoctors().get(i).getSurname());
        }
        ButtonHistoriaChorob.addActionListener(a -> {
            if (HistoriaOtwarta) {
                Historia.toFront();
            } else {
                Historia.setVisible(true);
            }
        });
        ButtonPrzypisaneLeki.addActionListener(a -> {
            if (LekiOtwarte) {
                Leki.toFront();
            } else {
                Leki.setVisible(true);
            }
        });
        ButtonHistoriaChorob.setFont(new Font("Now", Font.BOLD, 20));
        ButtonPrzypisaneLeki.setFont(new Font("Now", Font.BOLD, 20));
        add(ButtonHistoriaChorob, getC(5, 7, 0, 0, 0, 0));
        add(ButtonPrzypisaneLeki, getC(10, 7, 0, 0, 0, 0));
        add(ButtonWyloguj, getC(14, 1, 0, 0, 0, 0));
        add(LabelOcenLekarza,getC(5,9,0,0,0,0));
        add(ComboBoxLekarze,getC(5,10,0,0,0,0));
        add(ButtonRateDoctor,getC(6,10,0,0,0,0));
        ButtonRateDoctor.addActionListener(e->{
            doctor=Connection.getDoctors().get(ComboBoxLekarze.getSelectedIndex());
            try {
                OknoOpinie.LevelBar.setClicked(-1);
                OknoOpinie.createAndShowGui();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        ButtonWyloguj.addActionListener(e -> {
            Historia.dispatchEvent(new WindowEvent(Historia, WindowEvent.WINDOW_CLOSING));
            Leki.dispatchEvent(new WindowEvent(Leki, WindowEvent.WINDOW_CLOSING));
            try {
                Powierzchnia.getRamka().set(new PanelMenu());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
}
