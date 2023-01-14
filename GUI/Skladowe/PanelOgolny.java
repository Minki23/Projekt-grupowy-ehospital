package GUI.Skladowe;

import GUI.Menu.PanelMenu;
import GUI.PanelPacjenta.PanelChorobyILeki;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

public abstract class PanelOgolny extends JPanel {
    //stale
    private final GridBagConstraints C = new GridBagConstraints();
    private static final JButton ButtonCofnij = new JButton("<-");
    public static JButton getButtonCofnij() {
        return ButtonCofnij;
    }
    protected static JButton ButtonWyloguj=new JButton("Wyloguj");
    private final Image scaled;
    //konstruktor
    public PanelOgolny() throws IOException {
        ButtonWyloguj.addActionListener(e ->{
            PanelChorobyILeki.getHistoria().dispatchEvent(new WindowEvent(PanelChorobyILeki.getHistoria(), WindowEvent.WINDOW_CLOSING));
            PanelChorobyILeki.getLeki().dispatchEvent(new WindowEvent(PanelChorobyILeki.getLeki(), WindowEvent.WINDOW_CLOSING));
            Powierzchnia.getRamka().set(PanelMenu.getMenu());
        });
        //akcje buttonow
        ButtonCofnij.addActionListener(e -> {
            Powierzchnia.getRamka().set(PanelMenu.getMenu());
        });
        Image backgroundImage = ImageIO.read(new File("Data/tlo3.jpg"));
        scaled = backgroundImage.getScaledInstance(Powierzchnia.getRamka().getWidth(), Powierzchnia.getRamka().getHeight(), Image.SCALE_SMOOTH);
        setLayout(new GridBagLayout());
        for (int i = 0; i < 15; i++) {
            getC(i, i, 0.5, 0.5, 0, 0);
            add(new PanelPusty(), C);
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension d = Powierzchnia.getRamka().getSize();
        g.drawImage(scaled, 0, 0, (int) d.getWidth(), (int) d.getHeight(), this);
    }

    protected GridBagConstraints getC(int gridx, int gridy, double weightx, double weighty, int ipadx, int ipady) {
        C.gridx = gridx;
        C.gridy = gridy;
        C.weightx = weightx;
        C.weighty = weighty;
        C.ipadx=ipadx;
        C.ipady=ipady;
        C.fill = GridBagConstraints.BOTH;
        return C;
    }
    public static class HintTextField extends JTextField {
        public HintTextField(String hint) {
            _hint = hint;
        }
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            if (getText().length() == 0) {
                int h = getHeight();
                ((Graphics2D)g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
                Insets ins = getInsets();
                FontMetrics fm = g.getFontMetrics();
                int c0 = getBackground().getRGB();
                int c1 = getForeground().getRGB();
                int m = 0xfefefefe;
                int c2 = ((c0 & m) >>> 1) + ((c1 & m) >>> 1);
                g.setColor(new Color(c2, true));
                g.drawString(_hint, ins.left, h / 2 + fm.getAscent() / 2 - 2);
            }
        }
        private final String _hint;
    }
}
