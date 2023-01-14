package GUI.PanelPacjenta;

import UI.UserAccount;

import javax.swing.*;
import java.io.IOException;

public class PanelPacjent extends JTabbedPane {
    public PanelPacjent() throws IOException {
        add("Wyswietl profil", new PanelWyswietlProfil());
        add("Historia chorob i przypisane leki", new PanelChorobyILeki());
        add("Umow wizyte", new PanelUmowWizyte());
    }
}
