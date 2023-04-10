package main.utils.iocontrol;

import java.util.Scanner;

public class IntGetter {
    public static int readInt() {
        try {
            return new Scanner(System.in).nextInt();
        } catch (Exception e) {
            System.out.println("Please enter a valid integer.");
            return readInt();
        }
    }
}
