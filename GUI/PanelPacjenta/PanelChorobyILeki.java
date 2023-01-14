package GUI.PanelPacjenta;

import GUI.Menu.PanelMenu;
import GUI.Skladowe.PanelOgolny;
import GUI.Skladowe.Powierzchnia;

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

    public PanelChorobyILeki() throws IOException {
        JButton ButtonHistoriaChorob = new JButton("Historia Chorob");
        JButton ButtonPrzypisaneLeki = new JButton("Przypisane Leki");
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
