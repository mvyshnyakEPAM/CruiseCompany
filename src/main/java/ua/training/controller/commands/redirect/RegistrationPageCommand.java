package ua.training.controller.commands.redirect;

import ua.training.controller.commands.Command;
import ua.training.constants.Pages;
import ua.training.controller.servlets.actions.Forward;
import ua.training.controller.servlets.actions.ServletAction;

import javax.servlet.http.HttpServletRequest;

/**
 * Максим
 * 03.05.2018
 */
public class RegistrationPageCommand implements Command {
    @Override
    public ServletAction execute(HttpServletRequest request) {
        return new Forward(Pages.REGISTRATION);
    }
}
