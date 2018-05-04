package ua.training.controller.listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.HashSet;

import static ua.training.controller.constants.Attributes.ATTRIBUTE_LOGGED_USERS;

/**
 * Максим
 * 03.05.2018
 */
public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext context = servletContextEvent.getServletContext();
        context.setAttribute(ATTRIBUTE_LOGGED_USERS, new HashSet<String>());
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        /*NOP*/
    }
}
