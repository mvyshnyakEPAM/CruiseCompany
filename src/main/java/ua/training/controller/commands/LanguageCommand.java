package ua.training.controller.commands;

import ua.training.controller.servlets.actions.Redirect;
import ua.training.controller.servlets.actions.ServletAction;

import javax.servlet.http.HttpServletRequest;

import static ua.training.controller.constants.Attributes.ATTRIBUTE_LANGUAGE;
import static ua.training.controller.constants.Attributes.ATTRIBUTE_REFERER;
import static ua.training.controller.constants.Parameters.PARAMETER_LOCALE;

/**
 * Максим
 * 30.04.2018
 */
public class LanguageCommand implements Command {
    @Override
    public ServletAction execute(HttpServletRequest request) {
        String url = request.getHeader(ATTRIBUTE_REFERER);
        request.getSession().setAttribute(ATTRIBUTE_LANGUAGE, request.getParameter(PARAMETER_LOCALE));
        return new Redirect(url);
    }
}
