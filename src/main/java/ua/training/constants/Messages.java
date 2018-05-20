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
    String LOG_ADD_BONUS = "%s %s added bonus %s to %s";
    String LOG_REMOVE_BONUS = "%s %s removed bonus %s from %s";
    String LOG_ADD_EXCURSION = "%s %s added excursion %s to the cart.";
    String LOG_REMOVE_EXCURSION = "%s %s removed excursion %s from the cart.";
    String LOG_SUCCESSFUL_PURCHASE = "%s %s bought cruise %s.";
    String LOG_NO_FREE_PLACES = "%s %s tried to buy cruise %s but there were no free places.";
    String LOG_ALREADY_BOUGHT = "%s %s tried to buy cruise %s he(she) already has.";
}
