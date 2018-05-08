package ua.training.controller.commands;

import ua.training.constants.Attributes;
import ua.training.constants.Messages;
import ua.training.constants.Parameters;
import ua.training.constants.URLs;
import ua.training.controller.listeners.LoginDto;
import ua.training.controller.servlets.actions.Redirect;
import ua.training.controller.servlets.actions.ServletAction;
import ua.training.controller.util.ControllerUtil;
import ua.training.model.entities.User;
import ua.training.model.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * Максим
 * 29.04.2018
 */
public class LoginCommand implements Command {
    private UserService userService = UserService.getInstance();

    @Override
    public ServletAction execute(HttpServletRequest request) {
        String login = request.getParameter(Parameters.LOGIN);
        String password = request.getParameter(Parameters.PASSWORD);

        if (!ControllerUtil.isDataValid(request.getSession(), login, password)) {
            return new Redirect(URLs.LOGIN);
        }

        HttpSession session = request.getSession();
        Optional<User> user = userService.signIn(login, password);
        if (user.isPresent()) {
            session.setAttribute(Attributes.USER, new LoginDto(login));
            session.setAttribute(Attributes.ROLE, user.get().getRole());
            return new Redirect(getUserPage(user.get().getRole()));
        } else {
            request.setAttribute(Attributes.MESSAGE, Messages.LOGIN_FAIL);
            return new Redirect(URLs.LOGIN);
        }
    }

    private String getUserPage(User.Role role) {
        if (role == User.Role.CLIENT) {
            return URLs.CLIENT;
        } else {
            return URLs.ADMIN;
        }
    }
}
