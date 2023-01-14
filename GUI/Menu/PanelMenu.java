package GUI.Menu;

import GUI.Skladowe.PanelOgolny;
import GUI.Skladowe.Powierzchnia;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class PanelMenu extends PanelOgolny {
    static Image scaled;
    private static PanelMenu menu;

    static {
        try {
            menu = new PanelMenu();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static PanelMenu getMenu() {
        return menu;
    }

    public PanelMenu() throws IOException {
        super();
        Image backgroundImage = ImageIO.read(new File("Data/tlo1.png"));
        scaled = backgroundImage.getScaledInstance(Powierzchnia.getRamka().getWidth(), Powierzchnia.getRamka().getHeight(), Image.SCALE_SMOOTH);
        //Labele
        JLabel LabelLogowanie = new JLabel("Strona Logowania");
        JLabel LabelWybierzLogowanie = new JLabel("<html>" + "Wybierz opcje<br> logowania:" + "</html>");
        JLabel LabelRejestracja = new JLabel("<html>" + "Jesteś u nas pierwszy raz? Zarejestruj sie za darmo" + "<html>");
        LabelLogowanie.setHorizontalAlignment(0);
        LabelWybierzLogowanie.setHorizontalAlignment(0);
        LabelRejestracja.setHorizontalAlignment(0);
        //Buttony
        JButton ButtonLogowanieLekarza = new JButton("Lekarz");
        JButton ButtonLogowaniePacjenta = new JButton("Pacjent");
        JButton ButtonRejestracja = new JButton("Rejestracja");
        JButton ButtonAdmin = new JButton("Panel Admina");
        //Akcje Buttonow
        ButtonLogowaniePacjenta.addActionListener(e -> {
            try {
                Powierzchnia.getRamka().set(new PanelLogowaniePacjent());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        ButtonLogowanieLekarza.addActionListener(e -> {
            try {
                Powierzchnia.getRamka().set(new PanelLogowanieLekarz());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        ButtonRejestracja.addActionListener(e -> {
            try {
                Powierzchnia.getRamka().set(new PanelRejestracja());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        ButtonAdmin.addActionListener(e -> {
            try {
                Powierzchnia.getRamka().set(new PanelLogowanieAdmin());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        //Personalizacja Obiektow
        LabelLogowanie.setFont(new Font("Now", Font.PLAIN, 18));
        LabelLogowanie.setOpaque(false);
        add(LabelLogowanie, getC(1, 7, 0.6, 0.6, 0, 10));

        LabelWybierzLogowanie.setFont(new Font("Now", Font.PLAIN, 18));
        LabelWybierzLogowanie.setOpaque(false);
        add(LabelWybierzLogowanie, getC(1, 9, 0, 0, 80, 20));

        add(ButtonLogowaniePacjenta, getC(1, 10, 0, 0, 0, 40));

        add(ButtonLogowanieLekarza, getC(1, 11, 0, 0, 0, 40));

        LabelRejestracja.setFont(new Font("Now", Font.ITALIC, 13));
        LabelRejestracja.setOpaque(true);
        add(LabelRejestracja, getC(12, 11, 0, 0, 110, 20));

        add(ButtonRejestracja, getC(12, 12, 0, 0, 0, 40));

        add(ButtonAdmin, getC(13, 1, 0, 0, 20, 20));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension d = Powierzchnia.getRamka().getSize();
        g.drawImage(scaled, 0, 0, (int) d.getWidth(), (int) d.getHeight(), this);
    }
}
