package GUI.PanelPacjenta;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class OknoHistoriaChorob extends JFrame {
    public OknoHistoriaChorob() {
        setTitle("Historia chorob");
        setLayout(new BorderLayout());
        JPanel panel = createPanel();
        add(BorderLayout.CENTER, new JScrollPane(panel));
        setSize(375, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
                PanelChorobyILeki.setHistoriaOtwarta(false);
            }
        });
    }

    public static JPanel createPanel() {
        String[] diseases = {
                "grypa",
                "zapalenie płuc",
                "zapalenie ucha",
                "zapalenie spojówek",
                "otitis media",
                "choroba Crohna",
                "rzs",
                "dna",
                "opryszczka",
                "choroba lokomocyjna"
        };
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(diseases.length, 2, 10, 10));
        for (int i = 0; i < 10; i++) {
            JLabel label = new JLabel(diseases[i]);
            label.setFont(new Font("Arial", Font.PLAIN, 20));
            panel.add(label);
        }
        return panel;
    }
}
