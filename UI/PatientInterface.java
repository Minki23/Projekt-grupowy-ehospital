package UI;

import Model.Patient;

public final class PatientInterface {

    private PatientInterface() {}

    public static void mainInterface() {

        UserAccount.patientLogin();
        if (UserAccount.getLoggedPatient() != null) {
            Patient logged = UserAccount.getLoggedPatient();
            System.out.println("You are logged as: " + logged);
        }
    }
}
