package App;

import Data.Connection;

public class Main {

    public static void main(String[] args) {

        Connection.loadAllData();
        System.out.println(Connection.getAdmins().toString());

        new Hospital();
        Connection.saveAllData();
    }
}
