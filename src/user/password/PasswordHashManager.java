package user.password;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHashManager {
    private static final String HASH_ALGORITHM = "SHA3-256";
    private static final MessageDigest messageDigest;

    static {
        try {
            messageDigest = MessageDigest.getInstance(HASH_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] hashPassword(String password) {
        return messageDigest.digest(password.getBytes());
    }

}
