package ua.training.controller.commands;

import ua.training.constants.Attributes;
import ua.training.constants.Commands;
import ua.training.controller.commands.admin.AddBonusCommand;
import ua.training.controller.commands.admin.RemoveBonusCommand;
import ua.training.controller.commands.admin.ShowShipBonusesCommand;
import ua.training.controller.commands.admin.ShowShipsCommand;
import ua.training.controller.commands.client.*;
import ua.training.controller.commands.redirect.AdminPageCommand;
import ua.training.controller.commands.redirect.ClientPageCommand;
import ua.training.controller.commands.redirect.LoginPageCommand;
import ua.training.controller.commands.redirect.RegistrationPageCommand;
import ua.training.controller.servlets.actions.Redirect;
import ua.training.controller.util.ControllerUtil;
import ua.training.model.entities.User;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Максим
 * 03.05.2018
 */
public class CommandFactory {
    private Map<String, Command> commands = new HashMap<>();

    private CommandFactory() {
        commands.put(Commands.LOGIN_PAGE, new LoginPageCommand());
        commands.put(Commands.REGISTRATION_PAGE, new RegistrationPageCommand());
        commands.put(Commands.CLIENT_PAGE, new ClientPageCommand());
        commands.put(Commands.ADMIN_PAGE, new AdminPageCommand());
        commands.put(Commands.REGISTER, new RegisterCommand());
        commands.put(Commands.LOGIN, new LoginCommand());
        commands.put(Commands.LOGOUT, new LogoutCommand());
        commands.put(Commands.LANGUAGE, new LanguageCommand());
        commands.put(Commands.SHOW_CRUISES, new ShowCruisesCommand());
        commands.put(Commands.SHOW_CRUISE_INFO, new ShowCruiseInfoCommand());
        commands.put("add-excursion", new AddExcursionCommand());
        commands.put("remove-excursion", new RemoveExcursionCommand());
        commands.put("pay-cruise", new PayCruiseCommand());
        commands.put("show-my-cruises", new ShowMyCruisesCommand());
        commands.put("show-my-excursions", new ShowMyExcursionsCommand());
        commands.put("show-ships", new ShowShipsCommand());
        commands.put("show-ship-bonuses", new ShowShipBonusesCommand());
        commands.put("add-bonus", new AddBonusCommand());
        commands.put("remove-bonus", new RemoveBonusCommand());
    }

    private static class CommandFactoryHolder {
        private static final CommandFactory INSTANCE = new CommandFactory();
    }

    public static CommandFactory getInstance() {
        return CommandFactoryHolder.INSTANCE;
    }

    public Command getCommand(HttpServletRequest request) {
        String uri = request.getRequestURI();
        User.Role role = (User.Role) request.getSession().getAttribute(Attributes.ROLE);
        Command command = commands.get(extractCommand(uri));
        return command != null && ControllerUtil.isAccessAllowed(command, role, request.getPathInfo()) ?
                command : r -> new Redirect(ControllerUtil.getUserPage(role));
    }

    private static String extractCommand(String uri) {
        int lastSlashInd = uri.lastIndexOf("/");
        return uri.substring(lastSlashInd + 1);
    }
}
