package ua.training.controller.commands;

import ua.training.controller.servlets.actions.Redirect;
import ua.training.controller.servlets.actions.ServletAction;
import ua.training.model.entities.User;
import ua.training.model.entities.enums.Role;
import ua.training.model.services.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

import static ua.training.controller.constants.Attributes.ATTRIBUTE_MESSAGE;
import static ua.training.controller.constants.Attributes.ATTRIBUTE_USER;
import static ua.training.controller.constants.Messages.MESSAGE_LOGIN_FAIL;
import static ua.training.controller.constants.Parameters.PARAMETER_LOGIN;
import static ua.training.controller.constants.Parameters.PARAMETER_PASSWORD;
import static ua.training.controller.constants.URLs.URL_ADMIN;
import static ua.training.controller.constants.URLs.URL_CLIENT;
import static ua.training.controller.constants.URLs.URL_LOGIN;
import static ua.training.controller.util.ControllerUtil.*;

/**
 * Максим
 * 29.04.2018
 */
public class LoginCommand implements Command {
    private UserService userService = UserService.getInstance();

    @Override
    public ServletAction execute(HttpServletRequest request) {
        String login = request.getParameter(PARAMETER_LOGIN);
        String password = request.getParameter(PARAMETER_PASSWORD);

        Optional<User> user = userService.signIn(login, password);

        boolean isLogged = user.isPresent() &&
                addLoggedUserToContext(request.getSession(), user.get().getLogin());

        if (isLogged) {
            request.getSession().setAttribute(ATTRIBUTE_USER, user);
            return new Redirect(getUserPage(user.get().getRole()));
        } else {
            request.setAttribute(ATTRIBUTE_MESSAGE, MESSAGE_LOGIN_FAIL);
            return new Redirect(URL_LOGIN);
        }

        /*if (login.equals("user") && password.equals("123")) {
            return new Redirect(URL_CLIENT);
        } else if (login.equals("admin") && password.equals("321")) {
            return new Redirect(URL_ADMIN);
        }
        return new Redirect(PAGE_INDEX);*/
    }

    private String getUserPage(Role role) {
        if (role == Role.CLIENT) {
            return URL_CLIENT;
        } else {
            return URL_ADMIN;
        }
    }
}
