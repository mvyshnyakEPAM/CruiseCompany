package ua.training.constants;

/**
 * Максим
 * 04.05.2018
 */
public interface RegExp {
//    String CLIENT_URL = "(/company/(client|language)|/company/client.*/(logout|show-cruises))";
//    String ADMIN_URL = "(/company/(admin|language)|/company/admin.*/(logout))";
//    String GUEST_URL = "/company/(login-page|login|registration-page|register|language)";

    String CLIENT_URL = "/company/client.*";
    String ADMIN_URL = "/company/admin.*";
    String GUEST_URL = "/company/(?!.*(client|admin)).*";

    String COMMAND_LOGIN_PAGE = "/company/login-page";
    String COMMAND_REGISTRATION_PAGE = "/company/registration-page";
    String COMMAND_CLIENT_PAGE = "/company/client";
    String COMMAND_ADMIN_PAGE = "/company/admin";
    String COMMAND_REGISTER = "/company/register";
    String COMMAND_LOGIN = "/company/login";
    String COMMAND_LOGOUT = "/company/logout";
    String COMMAND_LANGUAGE = "/company/language";
    String COMMAND_SHOW_CRUISES = "/company/client/show-cruises";

    String LOGIN = "^[A-Za-z0-9_-]{3,16}$";
    String PASSWORD = "^[A-Za-z0-9_-]{3,18}$";
}
