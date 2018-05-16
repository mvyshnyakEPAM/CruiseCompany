package ua.training.controller.util;

import ua.training.constants.*;
import ua.training.controller.commands.AccessRequired;
import ua.training.controller.commands.Command;
import ua.training.model.entities.Excursion;
import ua.training.model.entities.User;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Максим
 * 02.05.2018
 */
@SuppressWarnings("unchecked")
public class ControllerUtil {
    public static boolean isDataValid(Map<String, String> messages, String login, String password) {
        boolean valid = true;
        if (!(login != null && login.matches(RegExp.LOGIN))) {
            messages.put(Attributes.LOGIN_MISMATCH, Messages.LOGIN_MISMATCH);
            valid = false;
        }
        if (!(password != null && password.matches(RegExp.PASSWORD))) {
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

    public static boolean isAccessAllowed(Command command, User.Role role, String path) {
        Class<?> commandClass = command.getClass();
        AccessRequired accessRequired = commandClass.getAnnotation(AccessRequired.class);
        return accessRequired != null &&
                Arrays.asList(accessRequired.roles()).contains(role) &&
                path.matches(accessRequired.path());
    }

    public static Map<String, List<Excursion>> getCart(HttpSession session) {
        return (HashMap<String, List<Excursion>>) session.getAttribute("excursions");
    }
}
