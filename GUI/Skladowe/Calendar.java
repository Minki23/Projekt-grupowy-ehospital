package GUI.Skladowe;

import GUI.PanelPacjenta.PanelPacjent;
import GUI.PanelPacjenta.PanelUmawianieWizyty;
import GUI.PanelPacjenta.PanelUmowWizyte;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Calendar {
    int month = java.util.Calendar.getInstance().get(java.util.Calendar.MONTH);
    int year = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);;
    JLabel l = new JLabel("", JLabel.CENTER);
    String day = "";
    JDialog d;
    JButton[] button = new JButton[49];
    public Calendar(JFrame parent) {
        d = new JDialog();
        d.setTitle(parent.getTitle());
        d.setModal(true);
        String[] header = { "Sun", "Mon", "Tue", "Wed", "Thur", "Fri", "Sat" };
        JPanel p1 = new JPanel(new GridLayout(7, 7));
        p1.setPreferredSize(new Dimension(430, 120));

        for (int x = 0; x < button.length; x++) {
            final int selection = x;
            button[x] = new JButton();
            button[x].setFocusPainted(false);
            button[x].setBackground(Color.white);
            if (x > 6) {
                button[x].addActionListener(ae -> {
                    day = button[selection].getActionCommand();
                    if(day.length()!=0) {
                        PanelUmowWizyte.getLabelWybranaData().setText("<html>" + " " +
                                "" +PanelUmawianieWizyty.getDoctor()+ "<br/>" +
                                "" + setPickedDate() + "</html>");
                        try {
                            Powierzchnia.getRamka().setTab(new PanelPacjent());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        d.dispose();
                    }
                });
            }
            if (x < 7) {
                button[x].setText(header[x]);
                button[x].setForeground(Color.red);
            }
            p1.add(button[x]);
        }
        JPanel p2 = new JPanel(new GridLayout(1, 3));
        JButton previous = new JButton("<< Previous");
        previous.addActionListener(ae -> {
            month--;
            displayDate();
        });
        p2.add(previous);
        p2.add(l);
        JButton next = new JButton("Next >>");
        next.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                month++;
                displayDate();
            }
        });
        p2.add(next);
        d.add(p1, BorderLayout.CENTER);
        d.add(p2, BorderLayout.SOUTH);
        d.pack();
        d.setLocationRelativeTo(parent);
        displayDate();
        d.setVisible(true);
    }

    public void displayDate() {
        for (int x = 7; x < button.length; x++)
            button[x].setText("");
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
                "MMMM yyyy");
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.set(year, month, 1);
        int dayOfWeek = cal.get(java.util.Calendar.DAY_OF_WEEK);
        int daysInMonth = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
        for (int x = 6 + dayOfWeek, day = 1; day <= daysInMonth; x++, day++) {
            button[x].setText("" + day);
            //for(int i=0;i<PanelUmawianieWizyty.getLekarz().getDoctorAppointments().size();i++) {
            //    if((year==PanelUmawianieWizyty.getLekarz().getDoctorAppointments().get(i).getDateOfAppointment().getYear())
            //    && (month==PanelUmawianieWizyty.getLekarz().getDoctorAppointments().get(i).getDateOfAppointment().getMonth())
            //    && (day==PanelUmawianieWizyty.getLekarz().getDoctorAppointments().get(i).getDateOfAppointment().getDay()))
            //    {
            //        button[x].setBackground(Color.RED);
            //        button[x].setEnabled(false);
            //    }
            //}
        }
        l.setText(sdf.format(cal.getTime()));
    }

    public String setPickedDate() {
        if (day.equals(""))
            return day;
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd-MM-yyyy");
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.set(year, month, Integer.parseInt(day));
        return sdf.format(cal.getTime());
    }
}