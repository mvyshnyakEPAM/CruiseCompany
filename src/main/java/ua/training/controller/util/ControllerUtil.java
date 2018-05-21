package ua.training.controller.util;

import ua.training.constants.*;
import ua.training.controller.commands.AccessRequired;
import ua.training.controller.commands.Command;
import ua.training.model.entities.Excursion;
import ua.training.model.entities.User;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Максим
 * 02.05.2018
 */
@SuppressWarnings("unchecked")
public class ControllerUtil {
    /**
     * Checks entered user data.
     *
     * @param messages the messages
     * @param login    the login
     * @param password the password
     * @return the boolean
     */
    public static boolean isDataValid(Map<String, String> messages, String login, String password) {
        boolean valid = true;
        if (Objects.isNull(login) || !login.matches(RegExp.LOGIN)) {
            messages.put(Attributes.LOGIN_MISMATCH_MESSAGE, Messages.LOGIN_MISMATCH);
            valid = false;
        }
        if (Objects.isNull(password) || !password.matches(RegExp.PASSWORD)) {
            messages.put(Attributes.PASSWORD_MISMATCH_MESSAGE, Messages.PASSWORD_MISMATCH);
            valid = false;
        }
        return valid;
    }

    /**
     * Gets redirect path considering user's role.
     *
     * @param role the role
     * @return the redirect path
     */
    public static String getRedirectPath(User.Role role) {
        return role == User.Role.GUEST ? URLs.INDEX : URLs.MAIN;
    }

    /**
     * Gets user page considering his role.
     *
     * @param role the role
     * @return the user page
     */
    public static String getUserPage (User.Role role) {
        return role == User.Role.CLIENT ? Pages.CLIENT : Pages.ADMIN;
    }

    /**
     * Checks whether current user has an access to the command.
     *
     * @param command the command
     * @param role    the role
     * @return the boolean
     */
    public static boolean isAccessAllowed(Command command, User.Role role) {
        Class<?> commandClass = command.getClass();
        AccessRequired accessRequired = commandClass.getAnnotation(AccessRequired.class);
        return Objects.nonNull(accessRequired) &&
                Arrays.asList(accessRequired.roles()).contains(role);
    }

    /**
     * Gets current user's cart from session.
     *
     * @param session the session
     * @return the cart
     */
    public static Map<String, Set<Excursion>> getCart(HttpSession session) {
        return (HashMap<String, Set<Excursion>>) session.getAttribute(Attributes.CART);
    }
}
