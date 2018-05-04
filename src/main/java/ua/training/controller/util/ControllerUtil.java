package ua.training.controller.util;

import ua.training.model.entities.User;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Set;

import static ua.training.controller.constants.Attributes.ATTRIBUTE_LOGGED_USERS;
import static ua.training.controller.constants.Attributes.ATTRIBUTE_USER;

/**
 * Максим
 * 02.05.2018
 */
@SuppressWarnings("unchecked")
public class ControllerUtil {
    public synchronized static boolean addLoggedUserToContext(HttpSession session, String login) {
        ServletContext context = session.getServletContext();
        Set<String> loggedUsers = (HashSet<String>) context.getAttribute(ATTRIBUTE_LOGGED_USERS);
        boolean userAdded = loggedUsers.add(login);
        context.setAttribute(ATTRIBUTE_LOGGED_USERS, loggedUsers);
        return userAdded;
    }

    public synchronized static void deleteLoggedUserFromContext(HttpSession session) {
        ServletContext context = session.getServletContext();
        Set<String> loggedUsers = (HashSet<String>) context.getAttribute(ATTRIBUTE_LOGGED_USERS);
        User user = (User) session.getAttribute(ATTRIBUTE_USER);
        loggedUsers.remove(user.getLogin());
        context.setAttribute(ATTRIBUTE_LOGGED_USERS, loggedUsers);
    }
}
