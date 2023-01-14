package GUI.PanelPacjenta;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class OknoPrzypisaneLeki extends JFrame {
    public OknoPrzypisaneLeki() {
        setTitle("Przypisane leki");
        setLayout(new BorderLayout());
        JPanel panel = createPanel();
        add(BorderLayout.CENTER, new JScrollPane(panel));
        setSize(375, 250);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
        addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
                PanelChorobyILeki.setLekiOtwarte(false);
            }
        });
    }

    public static JPanel createPanel() {
        JPanel panel = new JPanel();
        String[] drugs = {
                "ibuprofen",
                "paracetamol",
                "amoxicillin",
                "erytromycyna",
                "dekstrometorfan",
                "lewofloksacyna",
                "metformina",
                "atorwastatyna",
                "sertralina",
                "fenobarbital"
        };
        panel.setLayout(new GridLayout(drugs.length, 2, 10, 10));
        for (int i = 0; i < 10; i++) {
            JLabel label = new JLabel(drugs[i]);
            label.setFont(new Font("Arial", Font.PLAIN, 20));
            panel.add(label);
        }
        return panel;
    }
}

