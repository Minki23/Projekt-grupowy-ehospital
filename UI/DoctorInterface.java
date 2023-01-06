package UI;

import Model.Doctor;

public final class DoctorInterface {

    private DoctorInterface() {}

    public static void mainInterface() {

        UserAccount.doctorLogin();
        if (UserAccount.getLoggedDoctor() != null) {
            Doctor logged = UserAccount.getLoggedDoctor();
            System.out.println("You are logged as: " + logged);
        }
    }
}
