package ua.training.controller.listeners;

import ua.training.constants.Attributes;
import ua.training.constants.Parameters;
import ua.training.model.entities.User;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Максим
 * 02.05.2018
 */
public class SessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent event) {
        ServletContext context = event.getSession().getServletContext();
        event.getSession().setAttribute(Attributes.LANGUAGE, context.getInitParameter(Parameters.LOCALE));
        event.getSession().setAttribute(Attributes.ROLE, User.Role.GUEST);
    }


    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        event.getSession().removeAttribute(Attributes.USER);
    }
}
