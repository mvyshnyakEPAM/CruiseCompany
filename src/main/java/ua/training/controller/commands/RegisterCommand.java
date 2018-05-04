package ua.training.controller.commands;

import ua.training.controller.servlets.actions.Redirect;
import ua.training.controller.servlets.actions.ServletAction;
import ua.training.model.entities.User;
import ua.training.model.entities.enums.Role;
import ua.training.model.services.UserService;

import javax.servlet.http.HttpServletRequest;

import static ua.training.controller.constants.Attributes.ATTRIBUTE_MESSAGE;
import static ua.training.controller.constants.Messages.MESSAGE_REGISTRATION_FAIL;
import static ua.training.controller.constants.Parameters.PARAMETER_LOGIN;
import static ua.training.controller.constants.Parameters.PARAMETER_PASSWORD;
import static ua.training.controller.constants.URLs.URL_LOGIN;
import static ua.training.controller.constants.URLs.URL_REGISTRATION;

/**
 * Максим
 * 29.04.2018
 */
public class RegisterCommand implements Command {
    private UserService userService = UserService.getInstance();

    @Override
    public ServletAction execute(HttpServletRequest request) {
        try {
            String login = request.getParameter(PARAMETER_LOGIN);
            String password = request.getParameter(PARAMETER_PASSWORD);
            User user = new User.UserBuilder()
                    .setLogin(login)
                    .setPassword(password)
                    .setRole(Role.CLIENT)
                    .build();
            userService.signUp(user);
            return new Redirect(URL_LOGIN);
        } catch (Exception e) {
            request.setAttribute(ATTRIBUTE_MESSAGE, MESSAGE_REGISTRATION_FAIL);
        }
        return new Redirect(URL_REGISTRATION);
    }
}
