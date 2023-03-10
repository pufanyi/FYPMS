package user;

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
        return input.equals(password);
    }
}
