package ua.training.constants;

/**
 * Максим
 * 04.05.2018
 */
public interface RegExp {
    String CLIENT_URL = "(/company/(client|language)|/company/client.*/(logout|show-cruises))";
    String ADMIN_URL = "(/company/(admin|language)|/company/admin.*/(logout))";
    String GUEST_URL = "/company/(login-page|login|registration-page|register|language)";

    String LOGIN = "^[A-Za-z0-9_-]{3,16}$";
    String PASSWORD = "^[A-Za-z0-9_-]{3,18}$";
}
