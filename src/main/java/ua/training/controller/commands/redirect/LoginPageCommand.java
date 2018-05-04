package ua.training.controller.commands.redirect;

import ua.training.controller.commands.Command;
import ua.training.controller.servlets.actions.Forward;
import ua.training.controller.servlets.actions.ServletAction;

import javax.servlet.http.HttpServletRequest;

import static ua.training.controller.constants.Pages.PAGE_LOGIN;

/**
 * Максим
 * 03.05.2018
 */
public class LoginPageCommand implements Command {
    @Override
    public ServletAction execute(HttpServletRequest request) {
        return new Forward(PAGE_LOGIN);
    }
}
