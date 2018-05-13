package ua.training.controller.commands;

import ua.training.constants.Attributes;
import ua.training.constants.Pages;
import ua.training.constants.RegExp;
import ua.training.controller.servlets.actions.Redirect;
import ua.training.controller.servlets.actions.ServletAction;
import ua.training.model.entities.User;

import javax.servlet.http.HttpServletRequest;

/**
 * Максим
 * 29.04.2018
 */
@AccessRequired(roles = {User.Role.CLIENT, User.Role.ADMIN}, regExp = RegExp.COMMAND_LOGOUT)
public class LogoutCommand implements Command {
    @Override
    public ServletAction execute(HttpServletRequest request) {
        request.getSession().removeAttribute(Attributes.USER);
        request.getSession().setAttribute(Attributes.ROLE, User.Role.GUEST);
        return new Redirect(Pages.INDEX);
    }
}
