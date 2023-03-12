package test.user;

import org.junit.jupiter.api.Test;
import user.password.PasswordManager;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PasswordManagerTest {
    /**
     * Tests the PasswordManager class.
     */
    @Test
    public void testPasswordManager() {
        PasswordManager passwordManager = new PasswordManager();
        assertTrue(passwordManager.checkPassword("password"));
        passwordManager.setPassword("newPassword");
        assertTrue(passwordManager.checkPassword("newPassword"));
        assertFalse(passwordManager.checkPassword("password"));
        assertFalse(passwordManager.checkPassword(null));
    }

    @Test
    public void testPasswordManagerConstructor() {
        PasswordManager passwordManager = new PasswordManager("newPassword");
        assertTrue(passwordManager.checkPassword("newPassword"));
        assertFalse(passwordManager.checkPassword("password"));
    }

    @Test
    public void testSetPassword() {
        PasswordManager passwordManager = new PasswordManager();
        passwordManager.setPassword("newPassword");
        assertTrue(passwordManager.checkPassword("newPassword"));
        assertFalse(passwordManager.checkPassword("password"));
    }

    @Test
    public void testSpecialCharacters() {
        PasswordManager passwordManager = new PasswordManager();
        passwordManager.setPassword("!@#$%^&*()");
        assertTrue(passwordManager.checkPassword("!@#$%^&*()"));
        assertFalse(passwordManager.checkPassword("password"));
        assertFalse(passwordManager.checkPassword(""));
        assertFalse(passwordManager.checkPassword(null));
    }
}
