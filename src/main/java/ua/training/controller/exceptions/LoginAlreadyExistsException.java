package ua.training.controller.exceptions;

/**
 * Максим
 * 04.05.2018
 */
public class LoginAlreadyExistsException extends Exception {
    private String login;

    public LoginAlreadyExistsException(String message, String login) {
        super(message);
        this.login = login;
    }

    public String getLogin() {
        return login;
    }
}
