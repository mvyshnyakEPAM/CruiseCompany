package ua.training.controller.commands;

import ua.training.controller.servlets.actions.Redirect;
import ua.training.controller.servlets.actions.ServletAction;

import javax.servlet.http.HttpServletRequest;

import static ua.training.controller.constants.Attributes.ATTRIBUTE_USER;
import static ua.training.controller.constants.Pages.PAGE_INDEX;

/**
 * Максим
 * 29.04.2018
 */
public class LogoutCommand implements Command {
    @Override
    public ServletAction execute(HttpServletRequest request) {
        request.getSession().removeAttribute(ATTRIBUTE_USER);
        return new Redirect(PAGE_INDEX);
    }
}
