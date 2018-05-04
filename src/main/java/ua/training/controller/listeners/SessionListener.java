package ua.training.controller.listeners;

import ua.training.controller.util.ControllerUtil;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Максим
 * 02.05.2018
 */
public class SessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        /*NOP*/
    }


    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        ControllerUtil.deleteLoggedUserFromContext(httpSessionEvent.getSession());
    }
}
