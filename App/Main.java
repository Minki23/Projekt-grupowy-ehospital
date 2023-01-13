package App;

import Data.Connection;
import UI.UserAccount;

public class Main {

    public static void main(String[] args) {
        Connection.loadAllData();
        System.out.println(Connection.getAdmins().toString());
        UserAccount.createAdmin(1111,"12345");

        new Hospital();
        Connection.saveAllData();
    }
}
