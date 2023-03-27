package main.boundary.account.getter;

import main.model.user.UserType;

import java.util.Scanner;

public class DomainGetter {
    public static UserType getDomain() {
        System.out.println("Please select your domain (1-3):");
        System.out.println("\t1. Student");
        System.out.println("\t2. Supervisor");
        System.out.println("\t3. FYP Coordinator");
        UserType userType = null;
        while (userType != null) {
            Scanner scanner = new Scanner(System.in);
            int domain = scanner.nextInt();
            userType = switch (domain) {
                case 1 -> UserType.STUDENT;
                case 2 -> UserType.FACULTY;
                case 3 -> UserType.COORDINATOR;
                default -> null;
            };
            if (userType == null) {
                System.out.println("Invalid domain. Please try again.");
            }
        }
        return userType;
    }
}
