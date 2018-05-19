package ua.training.controller.commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.constants.Attributes;
import ua.training.constants.Messages;
import ua.training.constants.Pages;
import ua.training.controller.listeners.ActiveUser;
import ua.training.controller.servlets.actions.Redirect;
import ua.training.controller.servlets.actions.ServletAction;
import ua.training.model.entities.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Максим
 * 29.04.2018
 */
@AccessRequired(roles = {User.Role.CLIENT, User.Role.ADMIN})
public class LogoutCommand implements Command {
    private final static Logger logger = LogManager.getLogger(LogoutCommand.class);
    @Override
    public ServletAction execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String login = ((ActiveUser) session.getAttribute(Attributes.USER)).getLogin();
        User.Role role = (User.Role) session.getAttribute(Attributes.ROLE);
        request.getSession().removeAttribute(Attributes.USER);
        request.getSession().setAttribute(Attributes.ROLE, User.Role.GUEST);
        logger.info(String.format(Messages.LOG_LOGOUT, role, login));
        return new Redirect(Pages.INDEX);
    }
}
