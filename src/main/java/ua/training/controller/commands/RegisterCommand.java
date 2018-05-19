package ua.training.controller.commands;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.constants.*;
import ua.training.controller.exceptions.LoginAlreadyExistsException;
import ua.training.controller.servlets.actions.Forward;
import ua.training.controller.servlets.actions.ServletAction;
import ua.training.controller.util.ControllerUtil;
import ua.training.model.entities.User;
import ua.training.model.services.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Максим
 * 29.04.2018
 */
@AccessRequired(roles = {User.Role.GUEST})
public class RegisterCommand implements Command {
    private final static Logger logger = LogManager.getLogger(RegisterCommand.class);
    UserService userService = UserService.getInstance();

    @Override
    public ServletAction execute(HttpServletRequest request) {
        try {
            String login = request.getParameter(Parameters.LOGIN);
            String password = request.getParameter(Parameters.PASSWORD);
            Map<String, String> messages = new HashMap<>();
            if (!ControllerUtil.isDataValid(messages, login, password)) {
                request.setAttribute(Attributes.MESSAGES, messages);
                return new Forward(Pages.REGISTRATION);
            }
            User user = new User.UserBuilder()
                    .setLogin(login)
                    .setPassword(DigestUtils.md5Hex(password))
                    .setRole(User.Role.CLIENT)
                    .build();
            userService.signUp(user);
            logger.info(String.format(Messages.LOG_SUCCESSFUL_REGISTRATION, user.getRole(), login));
            request.setAttribute(Attributes.MESSAGE_INFO, Messages.SUCCESSFUL_REGISTRATION);
            return new Forward(Pages.SUCCESSFUL_REGISTRATION);
        } catch (LoginAlreadyExistsException e) {
            logger.error(String.format(Messages.LOG_FAIL_REGISTRATION, e.getLogin()));
            request.setAttribute(Attributes.MESSAGE_INFO, Messages.REGISTRATION_FAIL);
        }
        return new Forward(Pages.REGISTRATION);
    }
}
