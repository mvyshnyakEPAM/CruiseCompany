package ua.training.controller.commands;

import ua.training.constants.Attributes;
import ua.training.constants.Parameters;
import ua.training.constants.RegExp;
import ua.training.controller.servlets.actions.Redirect;
import ua.training.controller.servlets.actions.ServletAction;
import ua.training.model.entities.User;

import javax.servlet.http.HttpServletRequest;

/**
 * Максим
 * 30.04.2018
 */
@AccessRequired(roles = {User.Role.GUEST, User.Role.CLIENT, User.Role.ADMIN},
        regExp = RegExp.COMMAND_LANGUAGE)
public class LanguageCommand implements Command {
    @Override
    public ServletAction execute(HttpServletRequest request) {
        String url = request.getHeader(Attributes.REFERER);
        request.getSession().setAttribute(Attributes.LANGUAGE, request.getParameter(Parameters.LOCALE));
        return new Redirect(url);
    }
}
