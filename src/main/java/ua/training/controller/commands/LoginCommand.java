package ua.training.controller.commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.constants.*;
import ua.training.controller.listeners.LoginDto;
import ua.training.controller.servlets.actions.Forward;
import ua.training.controller.servlets.actions.Redirect;
import ua.training.controller.servlets.actions.ServletAction;
import ua.training.controller.util.ControllerUtil;
import ua.training.model.entities.User;
import ua.training.model.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Максим
 * 29.04.2018
 */
@AccessRequired(roles = {User.Role.GUEST}, path = CommandPaths.LOGIN)
public class LoginCommand implements Command {
    private final static Logger logger = LogManager.getLogger(LoginCommand.class);
    UserService userService = UserService.getInstance();

    @Override
    public ServletAction execute(HttpServletRequest request) {
        String login = request.getParameter(Parameters.LOGIN);
        String password = request.getParameter(Parameters.PASSWORD);

        Map<String, String> messages = new HashMap<>();

        if (!ControllerUtil.isDataValid(messages, login, password)) {
            request.setAttribute(Attributes.MESSAGES, messages);
            return new Forward(Pages.LOGIN);
        }

        HttpSession session = request.getSession();
        Optional<User> user = userService.signIn(login, password);
        if (user.isPresent()) {
            session.setAttribute(Attributes.USER, new LoginDto(user.get().getId(), login));
            session.setAttribute(Attributes.ROLE, user.get().getRole());
            logger.info(user.get().getRole() + " " + user.get().getLogin() + " logged successfully.");
            return new Redirect(ControllerUtil.getUserPage(user.get().getRole()));
        }

        request.setAttribute(Attributes.MESSAGE, Messages.LOGIN_FAIL);
        logger.info("Sign in fail with login: " + login + ".");
        return new Forward(Pages.LOGIN);
    }
}
