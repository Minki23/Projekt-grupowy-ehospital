package GUI;

public final class AdminGraphicInterface {

    private AdminGraphicInterface() {}

    public static void displayAdminInterface() {

        System.out.println("---------------------------------------");
        System.out.println("| 0: Log out                          |");
        System.out.println("| 1: Remove patient from data base    |");
        System.out.println("| 2: Remove doctor from data base     |");
        System.out.println("| 3: View doctors register requests   |");
        System.out.println("| 4: View all patients                |");
        System.out.println("| 5: View all doctors                 |");
        System.out.println("---------------------------------------");
    }
}
