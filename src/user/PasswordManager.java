package user;

import java.util.Objects;

public class PasswordManager {
    private String password;

    public PasswordManager() {
        password = "password";
    }

    public PasswordManager(String password) {
        this.password = password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean checkPassword(String input) {
        return Objects.equals(input, password);
    }
}
