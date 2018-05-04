package ua.training.controller.commands.redirect;

import ua.training.controller.commands.Command;
import ua.training.controller.servlets.actions.Forward;
import ua.training.controller.servlets.actions.ServletAction;

import javax.servlet.http.HttpServletRequest;

import static ua.training.controller.constants.Pages.PAGE_CLIENT;

/**
 * Максим
 * 30.04.2018
 */
public class ClientPageCommand implements Command {
    @Override
    public ServletAction execute(HttpServletRequest request) {
        return new Forward(PAGE_CLIENT);
    }
}
