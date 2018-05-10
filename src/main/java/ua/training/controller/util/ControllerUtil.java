package ua.training.controller.util;

import ua.training.constants.*;
import ua.training.model.entities.User;

import java.util.Map;
import java.util.Optional;

/**
 * Максим
 * 02.05.2018
 */
@SuppressWarnings("unchecked")
public class ControllerUtil {
    public static boolean isDataValid(Map<String, String> messages, String login, String password) {
        boolean valid = true;
        if (!(Optional.ofNullable(login).isPresent() && login.matches(RegExp.LOGIN))) {
            messages.put(Attributes.LOGIN_MISMATCH, Messages.LOGIN_MISMATCH);
            valid = false;
        }
        if (!(Optional.ofNullable(password).isPresent() && password.matches(RegExp.PASSWORD))) {
            messages.put(Attributes.PASSWORD_MISMATCH, Messages.PASSWORD_MISMATCH);
            valid = false;
        }
        return valid;
    }

    public static String getUserPage(User.Role role) {
        if (role == User.Role.CLIENT) {
            return URLs.CLIENT;
        } else if (role == User.Role.ADMIN) {
            return URLs.ADMIN;
        } else {
            return Pages.INDEX;
        }
    }
}
