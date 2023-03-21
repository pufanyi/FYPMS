package test.model.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import model.user.password.PasswordManager;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PasswordManagerTest {
    /**
     * Tests the PasswordManager class.
     */
    @Test
    @DisplayName("Test PasswordManager")
    public void testPasswordManager() {
        PasswordManager passwordManager = new PasswordManager();
        assertTrue(passwordManager.checkPassword("password"));
        assertFalse(passwordManager.checkPassword("@@@@"));
        passwordManager.setPassword("newPassword");
        assertTrue(passwordManager.checkPassword("newPassword"));
        assertFalse(passwordManager.checkPassword("password"));
    }

    @Test
    @DisplayName("Test the Constructor of PasswordManager")
    public void testPasswordManagerConstructor() {
        PasswordManager passwordManager = new PasswordManager("newPassword");
        assertTrue(passwordManager.checkPassword("newPassword"));
        assertFalse(passwordManager.checkPassword("password"));
    }

    @Test
    @DisplayName("Test the setPassword method of PasswordManager")
    public void testSetPassword() {
        PasswordManager passwordManager = new PasswordManager();
        passwordManager.setPassword("newPassword");
        assertTrue(passwordManager.checkPassword("newPassword"));
        assertFalse(passwordManager.checkPassword("password"));
    }

    @Test
    @DisplayName("Check the special characters while setting password")
    public void testSpecialCharacters() {
        PasswordManager passwordManager = new PasswordManager();
        passwordManager.setPassword("!@#$%^&*()");
        assertTrue(passwordManager.checkPassword("!@#$%^&*()"));
        assertFalse(passwordManager.checkPassword("password"));
        assertFalse(passwordManager.checkPassword("#$%^&*()"));
        assertFalse(passwordManager.checkPassword(""));
    }
}
