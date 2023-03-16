package user.password;

import org.jetbrains.annotations.NotNull;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * The PasswordHashManager class provides a utility for hashing passwords using the SHA3-256 algorithm.
 */
public class PasswordHashManager {
    /**
     * The hashing algorithm used to hash passwords
     */
    private static final String HASH_ALGORITHM = "SHA3-256";
    /**
     * The MessageDigest object used to perform the hashing
     */
    private static final MessageDigest messageDigest;

    // Static block used to initialize the MessageDigest object with the hashing algorithm
    static {
        try {
            messageDigest = MessageDigest.getInstance(HASH_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            // If the specified algorithm is not available, throw a runtime exception
            throw new RuntimeException(e);
        }
    }

    /**
     * Hashes the specified password using the SHA3-256 algorithm.
     *
     * @param password the password to be hashed
     * @return a byte array containing the hashed password
     */
    public static byte[] hashPassword(@NotNull String password) {
        return messageDigest.digest(password.getBytes());
    }
}
