package GUI.Skladowe;
import javax.swing.*;
import java.awt.*;

public class Powierzchnia extends JFrame{
    private static Powierzchnia ramka=new Powierzchnia();

    public static void setRamka(Powierzchnia ramka) {
        Powierzchnia.ramka = ramka;
    }

    public static Powierzchnia getRamka() {
        return ramka;
    }

    public Powierzchnia() throws HeadlessException {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setSize(800,600);
        setVisible(true);
    }
    public void set(JPanel panel){
        setContentPane(panel);
        revalidate();
        repaint();
    }
    public void setTab(JTabbedPane panel){
        setContentPane(panel);
        revalidate();
        repaint();
    }
}
