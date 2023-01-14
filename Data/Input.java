package Data;

import java.util.Scanner;

public final class Input {

    private Input() {}

    public static String getString() {
        return new Scanner(System.in).nextLine();
    }

    public static int getInt() {
        return new Scanner(System.in).nextInt();
    }

    public static char getChar() {
        return new Scanner(System.in).next().charAt(0);
    }
}