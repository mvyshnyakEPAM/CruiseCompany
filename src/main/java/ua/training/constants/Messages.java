package ua.training.constants;

/**
 * Максим
 * 03.05.2018
 */
public interface Messages {
    String LOGIN_FAIL = "login_fail";
    String REGISTRATION_FAIL = "registration_fail";

    String LOGIN_MISMATCH = "invalid_login_input_message";
    String PASSWORD_MISMATCH = "invalid_password_input_message";

    String CRUISE_ALREADY_BOUGHT = "info_cruise_bought";
    String NO_FREE_PLACES = "info_no_places";
    String SUCCESSFUL_PURCHASE = "info_successful_purchase";
    String SUCCESSFUL_REGISTRATION = "info_successful_registration";

    String LOG_SUCCESSFUL_LOGIN = "%s %s logged successfully.";
    String LOG_SUCCESSFUL_REGISTRATION = "%s %s registered successfully.";
    String LOG_FAIL_LOGIN = "Sign in fail with login: %s.";
    String LOG_FAIL_REGISTRATION = "Registration fail. Login %s already exists.";
    String LOG_LOGOUT = "%s %s logged out.";
}
