package ua.training.controller.commands.redirect;

import ua.training.constants.Attributes;
import ua.training.controller.commands.AccessRequired;
import ua.training.controller.commands.Command;
import ua.training.controller.servlets.actions.Forward;
import ua.training.controller.servlets.actions.ServletAction;
import ua.training.controller.util.ControllerUtil;
import ua.training.model.entities.User;

import javax.servlet.http.HttpServletRequest;

/**
 * Максим
 * 30.04.2018
 */
@AccessRequired(roles = {User.Role.CLIENT, User.Role.ADMIN})
public class MainPageCommand implements Command {
    @Override
    public ServletAction execute(HttpServletRequest request) {
        User.Role role = (User.Role) request.getSession().getAttribute(Attributes.ROLE);
        return new Forward(ControllerUtil.getUserPage(role));
    }
}
