package main.utils.iocontrol;

import java.util.Scanner;

/**

 The IntGetter class provides a static method for reading integer input from the user through the console.
 */
public class IntGetter {
    /**
     * Reads an integer value from the console input.
     *
     * @return the integer value read from the console input.
     */
    public static int readInt() {
        try {
            return new Scanner(System.in).nextInt();
        } catch (Exception e) {
            System.out.println("Please enter a valid integer.");
            return readInt();
        }
    }
}
