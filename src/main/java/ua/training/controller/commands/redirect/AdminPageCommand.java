package ua.training.controller.commands.redirect;

import ua.training.constants.RegExp;
import ua.training.controller.commands.AccessRequired;
import ua.training.controller.commands.Command;
import ua.training.constants.Pages;
import ua.training.controller.servlets.actions.Forward;
import ua.training.controller.servlets.actions.ServletAction;
import ua.training.model.entities.User;

import javax.servlet.http.HttpServletRequest;

/**
 * Максим
 * 30.04.2018
 */
@AccessRequired(roles = {User.Role.ADMIN}, regExp = RegExp.COMMAND_ADMIN_PAGE)
public class AdminPageCommand implements Command {
    @Override
    public ServletAction execute(HttpServletRequest request) {
        return new Forward(Pages.ADMIN);
    }
}
