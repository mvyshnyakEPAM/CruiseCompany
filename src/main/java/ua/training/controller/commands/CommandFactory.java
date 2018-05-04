package ua.training.controller.commands;

import ua.training.controller.commands.redirect.*;
import ua.training.controller.servlets.actions.Redirect;

import java.util.HashMap;
import java.util.Map;

import static ua.training.controller.constants.Commands.*;
import static ua.training.controller.constants.Pages.PAGE_INDEX;

/**
 * Максим
 * 03.05.2018
 */
public class CommandFactory {
    private Map<String, Command> commands = new HashMap<>();

    private CommandFactory() {
        commands.put(COMMAND_LOGIN_PAGE, new LoginPageCommand());
        commands.put(COMMAND_REGISTRATION_PAGE, new RegistrationPageCommand());
        commands.put(COMMAND_CLIENT_PAGE, new ClientPageCommand());
        commands.put(COMMAND_ADMIN_PAGE, new AdminPageCommand());
        commands.put(COMMAND_REGISTER, new RegisterCommand());
        commands.put(COMMAND_LOGIN, new LoginCommand());
        commands.put(COMMAND_LOGOUT, new LogoutCommand());
        commands.put(COMMAND_LANGUAGE, new LanguageCommand());
    }

    private static class CommandFactoryHolder {
        private static final CommandFactory INSTANCE = new CommandFactory();
    }

    public static CommandFactory getInstance() {
        return CommandFactoryHolder.INSTANCE;
    }

    public Command getCommand(String uri) {
        return commands.getOrDefault(extractCommand(uri), r -> new Redirect(PAGE_INDEX));
    }

    private String extractCommand(String requestURI) {
        int slashIndex = requestURI.lastIndexOf("/");
        return requestURI.substring(slashIndex, requestURI.length());
    }
}
