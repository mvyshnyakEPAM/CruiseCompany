package ua.training.controller.commands.redirect;

import ua.training.constants.Pages;
import ua.training.controller.commands.AccessRequired;
import ua.training.controller.commands.Command;
import ua.training.controller.servlets.actions.Forward;
import ua.training.controller.servlets.actions.ServletAction;
import ua.training.model.entities.User;

import javax.servlet.http.HttpServletRequest;

/**
 * Максим
 * 03.05.2018
 */
@AccessRequired(roles = {User.Role.GUEST})
public class LoginPageCommand implements Command {
    @Override
    public ServletAction execute(HttpServletRequest request) {
        return new Forward(Pages.LOGIN);
    }
}
