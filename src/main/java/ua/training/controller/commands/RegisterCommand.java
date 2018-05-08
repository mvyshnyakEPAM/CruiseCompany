package ua.training.controller.commands;

import ua.training.constants.Attributes;
import ua.training.constants.Messages;
import ua.training.constants.Parameters;
import ua.training.constants.URLs;
import ua.training.controller.servlets.actions.Redirect;
import ua.training.controller.servlets.actions.ServletAction;
import ua.training.model.entities.User;
import ua.training.model.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * Максим
 * 29.04.2018
 */
public class RegisterCommand implements Command {
    private UserService userService = UserService.getInstance();

    @Override
    public ServletAction execute(HttpServletRequest request) {
        try {
            String login = request.getParameter(Parameters.LOGIN);
            String password = request.getParameter(Parameters.PASSWORD);

            if (isDataValid(request.getSession(), login, password)) {
                return new Redirect(URLs.REGISTRATION);
            }

            User user = new User.UserBuilder()
                    .setLogin(login)
                    .setPassword(password)
                    .setRole(User.Role.CLIENT)
                    .build();

            userService.signUp(user);
            return new Redirect(URLs.LOGIN);
        } catch (Exception e) {
            request.setAttribute(Attributes.MESSAGE, Messages.REGISTRATION_FAIL);
        }
        return new Redirect(URLs.REGISTRATION);
    }

    private boolean isDataValid(HttpSession session, String login, String password) {
        boolean valid = true;
        if (!(Optional.ofNullable(login).isPresent() && login.matches(""))) {
            session.setAttribute("loginError", "invalid_login_input_message");
            valid = false;
        }
        if (!(Optional.ofNullable(password).isPresent() && password.matches(""))) {
            session.setAttribute("passwordError", "invalid_password_input_message");
            valid = false;
        }
        return valid;
    }
}
