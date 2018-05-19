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
public class ActiveUser implements HttpSessionBindingListener {
    private int id;
    private String login;

    public ActiveUser(int id, String login) {
        this.id = id;
        this.login = login;
    }

    public int getId() {
        return id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ActiveUser activeUser = (ActiveUser) o;

        return login != null ? login.equals(activeUser.login) : activeUser.login == null;
    }

    @Override
    public int hashCode() {
        return login != null ? login.hashCode() : 0;
    }
}
