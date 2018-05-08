package ua.training.controller.util;

import ua.training.constants.Attributes;
import ua.training.constants.Messages;
import ua.training.constants.RegExp;

import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * Максим
 * 02.05.2018
 */
@SuppressWarnings("unchecked")
public class ControllerUtil {
    public static boolean isDataValid(HttpSession session, String login, String password) {
        boolean valid = true;
        if (!(Optional.ofNullable(login).isPresent() && login.matches(RegExp.LOGIN))) {
            session.setAttribute(Attributes.LOGIN_MISMATCH, Messages.LOGIN_MISMATCH);
            valid = false;
        }
        if (!(Optional.ofNullable(password).isPresent() && password.matches(RegExp.PASSWORD))) {
            session.setAttribute(Attributes.PASSWORD_MISMATCH, Messages.PASSWORD_MISMATCH);
            valid = false;
        }
        return valid;
    }
}
