package GUI.PanelAdmina;

import Data.Connection;
import GUI.Skladowe.PanelOgolny;
import Model.Admin;
import Model.Patient;
import UI.UserAccount;

import javax.swing.*;
import java.io.IOException;

public class PanelAdmin extends PanelOgolny {
    private static JComboBox ComboBoxPatients = new JComboBox();
    private static JComboBox ComboBoxDoctors = new JComboBox();

    public PanelAdmin() throws IOException {
        if (ComboBoxPatients.getItemCount() == 0 && ComboBoxDoctors.getItemCount() == 0) {
            for (int i = 0; i < Connection.getDoctors().size(); i++) {
                ComboBoxDoctors.addItem(Connection.getDoctors().get(i).getFirstName() + " " + Connection.getDoctors().get(i).getSurname() + "\n ID:" +
                        Connection.getDoctors().get(i).getDoctorId());
            }
            for (int i = 0; i < Connection.getPatients().size(); i++) {
                ComboBoxPatients.addItem(Connection.getPatients().get(i).getFirstName() + " " + Connection.getPatients().get(i).getSurname() + "\n Pesel:"
                        + Connection.getPatients().get(i).getPersonalIdNumber());
            }
        }
        JButton ButtonUsunPacjenta = new JButton("Usun wybranego pacjenta");
        JButton ButtonUsunDoktora = new JButton("Usun wybranego doktora");
        JLabel LabelPanelAdmina = new JLabel("Panel admina");
        JLabel LabelDoctorName = new JLabel();
        JLabel LabelDoctorID = new JLabel();
        JLabel LabelDoctorPassword = new JLabel();
        JLabel LabelDoctorSpetialization = new JLabel();
        JLabel LabelPatientName = new JLabel();
        JLabel LabelPatientID = new JLabel();
        JLabel LabelPatientPassword = new JLabel();
        JLabel LabelZalogowanoJako = new JLabel("Zalogowano jako: " + UserAccount.getLoggedAdmin().getAdminID());
        add(ButtonWyloguj, getC(7, 1, 0, 0, 50, 0));
        //add(LabelUsunPacjentow, getC(2, 3, 0, 0, 50, 0));
        //add(LabelUsunLekarzy, getC(7, 3, 0, 0, 0, 0));
        add(LabelPanelAdmina, getC(6, 1, 0, 0, 0, 0));
        add(LabelZalogowanoJako, getC(6, 2, 0, 0, 50, 0));
        add(ComboBoxPatients, getC(2, 3, 0, 0, 50, 0));
        add(ComboBoxDoctors, getC(6, 3, 0, 0, 50, 0));
        add(ButtonUsunPacjenta, getC(2, 4, 0, 0, 0, 0));
        add(LabelPatientName, getC(2, 5, 0, 0, 0, 0));
        add(LabelPatientID, getC(2, 6, 0, 0, 0, 0));
        add(LabelPatientPassword, getC(2, 7, 0, 0, 0, 0));
        add(ButtonUsunDoktora, getC(6, 4, 0, 0, 0, 0));
        add(LabelDoctorName, getC(6, 5, 0, 0, 0, 0));
        add(LabelDoctorID, getC(6, 6, 0, 0, 0, 0));
        add(LabelDoctorPassword, getC(6, 7, 0, 0, 0, 0));
        add(LabelDoctorSpetialization,getC(6,8,0,0,0,0));
        ButtonUsunPacjenta.addActionListener(e -> {
            try {
                UserAccount.getLoggedAdmin().removePatient(Connection.getPatients().get(ComboBoxPatients.getSelectedIndex()));
                ComboBoxPatients.removeItemAt(ComboBoxPatients.getSelectedIndex());
                System.out.println("Usunieto");
                Connection.savePatients();
                revalidate();
                repaint();
            }catch (IndexOutOfBoundsException ignored){}
        });
        ButtonUsunDoktora.addActionListener(e -> {
            try {
                UserAccount.getLoggedAdmin().removeDoctor(Connection.getDoctors().get(ComboBoxDoctors.getSelectedIndex()));
                ComboBoxDoctors.removeItemAt(ComboBoxDoctors.getSelectedIndex());
                Connection.saveDoctors();
                revalidate();
                repaint();
            }catch (IndexOutOfBoundsException ignored){}
        });
        ComboBoxPatients.addActionListener(e -> {
            LabelPatientName.setText("Name:"+Connection.getPatients().get(ComboBoxPatients.getSelectedIndex()).getFirstName() + " " + Connection.getPatients().get(ComboBoxPatients.getSelectedIndex()).getSurname());
            LabelPatientID.setText("Personal ID:" + Connection.getPatients().get(ComboBoxPatients.getSelectedIndex()).getPersonalIdNumber());
            LabelPatientPassword.setText("Password:"+Connection.getPatients().get(ComboBoxPatients.getSelectedIndex()).getPassword());
            repaint();
        });
        ComboBoxDoctors.addActionListener(e -> {
            LabelDoctorName.setText("Name:"+Connection.getDoctors().get(ComboBoxDoctors.getSelectedIndex()).getFirstName() + " " + Connection.getDoctors().get(ComboBoxDoctors.getSelectedIndex()).getSurname());
            LabelDoctorID.setText("ID:" + Connection.getDoctors().get(ComboBoxDoctors.getSelectedIndex()).getDoctorId());
            LabelDoctorPassword.setText("Password:"+Connection.getDoctors().get(ComboBoxDoctors.getSelectedIndex()).getPassword());
            LabelDoctorSpetialization.setText("Spetialization:"+Connection.getDoctors().get(ComboBoxDoctors.getSelectedIndex()).getSpecialization().getName());
            repaint();
        });
        JLabel LabelOczekujacy = new JLabel("Lekarze oczekujący na dodanie");
        add(LabelOczekujacy,getC(2,8,0,0,0,0));
        JComboBox Oczekujacy=new JComboBox<>();
        JButton Zatwierdz = new JButton("Zatwierdz");
        for(int i=0;i<Connection.getDoctorsRegisterRequests().size();i++){
            Oczekujacy.addItem(Connection.getDoctorsRegisterRequests().get(i).getFirstName()+" " +
                    ""+Connection.getDoctorsRegisterRequests().get(i).getSurname()+
                    " spetialization: "+Connection.getDoctorsRegisterRequests().get(i).getSpecialization().getName());
        }
        add(Oczekujacy,getC(2,9,0,0,0,0));
        add(Zatwierdz,getC(3,9,0,0,40,0));
        Zatwierdz.addActionListener(e->{
            try {
                UserAccount.getLoggedAdmin().acceptDoctor(Connection.getDoctorsRegisterRequests().get(Oczekujacy.getSelectedIndex()));
                Oczekujacy.removeItemAt(Oczekujacy.getSelectedIndex());
                ComboBoxDoctors.addItem(Connection.getDoctors().get(Connection.getDoctors().size() - 1).getFirstName() + " " +
                        "" + Connection.getDoctors().get(Connection.getDoctors().size() - 1).getSurname() +
                        " spetialization: " + Connection.getDoctors().get(Connection.getDoctors().size() - 1).getSpecialization().getName());
                Connection.saveDoctorsRequests();
                Connection.saveDoctors();
                repaint();
                revalidate();
            }catch (IndexOutOfBoundsException ignored){}
        });
    }
}
