package main.utils.ui;

import main.model.user.User;
import main.model.user.UserType;

/**
 * The {@code UserTypeGetter} class provides utility methods for obtaining different forms of user types.
 */
public class UserTypeGetter {

    /**
     * Returns the user type of the given {@code User} object in camel case format.
     *
     * @param user A {@code User} object representing the user whose type is to be obtained.
     * @return A {@code String} object representing the user type in camel case format.
     */
    public static String getUserTypeInCamelCase(User user) {
        return user.getClass().getSimpleName();
    }

    /**
     * Returns the user type of the given {@code User} object in lowercase format.
     *
     * @param user A {@code User} object representing the user whose type is to be obtained.
     * @return A {@code String} object representing the user type in lowercase format.
     */
    public static String getUserTypeInSmallLetters(User user) {
        return getUserTypeInCamelCase(user).toLowerCase();
    }

    /**
     * Returns the user type of the given {@code User} object in uppercase format.
     *
     * @param user A {@code User} object representing the user whose type is to be obtained.
     * @return A {@code String} object representing the user type in uppercase format.
     */
    public static String getUserTypeInCapitalLetters(User user) {
        return getUserTypeInCamelCase(user).toUpperCase();
    }

    /**
     * Returns the given {@code UserType} in camel case format.
     *
     * @param userType A {@code UserType} object representing the user type to be obtained.
     * @return A {@code String} object representing the user type in camel case format.
     */
    public static String getUserTypeInCamelCase(UserType userType) {
        return userType.toString().substring(0, 1).toUpperCase() +
                userType.toString().substring(1).toLowerCase();
    }

    /**
     * Returns the given {@code UserType} in lowercase format.
     *
     * @param userType A {@code UserType} object representing the user type to be obtained.
     * @return A {@code String} object representing the user type in lowercase format.
     */
    public static String getUserTypeInSmallLetters(UserType userType) {
        return getUserTypeInCamelCase(userType).toLowerCase();
    }

    /**
     * Returns the given {@code UserType} in uppercase format.
     *
     * @param userType A {@code UserType} object representing the user type to be obtained.
     * @return A {@code String} object representing the user type in uppercase format.
     */
    public static String getUserTypeInCapitalLetters(UserType userType) {
        return getUserTypeInCamelCase(userType).toUpperCase();
    }
}
