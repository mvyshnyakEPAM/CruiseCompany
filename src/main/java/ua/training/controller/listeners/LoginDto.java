package ua.training.controller.listeners;

import ua.training.constants.Attributes;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import java.util.Map;
import java.util.Optional;

/**
 * Максим
 * 07.05.2018
 */

@SuppressWarnings("unchecked")
public class LoginDto implements HttpSessionBindingListener {
    private String login;

    public LoginDto(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        ServletContext context = event.getSession().getServletContext();
        Map<String, HttpSession> loggedUsers =
                (Map<String, HttpSession>) context.getAttribute(Attributes.LOGGED_USERS);
        if (loggedUsers.containsKey(login)) {
            Optional<HttpSession> previousSession = Optional.ofNullable(loggedUsers.remove(login));
            previousSession.ifPresent(HttpSession::invalidate);
        }
        loggedUsers.put(login, event.getSession());
        context.setAttribute(Attributes.LOGGED_USERS, loggedUsers);
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        ServletContext context = event.getSession().getServletContext();
        Map<String, HttpSession> loggedUsers =
                (Map<String, HttpSession>) context.getAttribute(Attributes.LOGGED_USERS);
        loggedUsers.remove(login);
        context.setAttribute(Attributes.LOGGED_USERS, loggedUsers);
    }
}
