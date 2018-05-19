package ua.training.controller.commands;

import ua.training.constants.Attributes;
import ua.training.constants.Commands;
import ua.training.constants.RegExp;
import ua.training.controller.commands.admin.AddBonusCommand;
import ua.training.controller.commands.admin.RemoveBonusCommand;
import ua.training.controller.commands.admin.ShowShipBonusesCommand;
import ua.training.controller.commands.admin.ShowShipsCommand;
import ua.training.controller.commands.client.*;
import ua.training.controller.commands.redirect.LoginPageCommand;
import ua.training.controller.commands.redirect.MainPageCommand;
import ua.training.controller.commands.redirect.RegistrationPageCommand;
import ua.training.controller.servlets.actions.Redirect;
import ua.training.controller.util.ControllerUtil;
import ua.training.model.entities.User;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Максим
 * 03.05.2018
 */
public class CommandFactory {
    private Map<String, Command> commands = new HashMap<>();

    private CommandFactory() {
        commands.put(Commands.LOGIN_PAGE, new LoginPageCommand());
        commands.put(Commands.REGISTRATION_PAGE, new RegistrationPageCommand());
        commands.put(Commands.REGISTER, new RegisterCommand());
        commands.put(Commands.LOGIN, new LoginCommand());
        commands.put(Commands.LOGOUT, new LogoutCommand());
        commands.put(Commands.LANGUAGE, new LanguageCommand());
        commands.put(Commands.MAIN, new MainPageCommand());
        commands.put(Commands.CRUISES, new ShowCruisesCommand());
        commands.put(Commands.CRUISE_INFO, new ShowCruiseInfoCommand());
        commands.put(Commands.ADD_EXCURSION, new AddExcursionCommand());
        commands.put(Commands.REMOVE_EXCURSION, new RemoveExcursionCommand());
        commands.put(Commands.PAY_CRUISE, new PayCruiseCommand());
        commands.put(Commands.MY_CRUISES, new ShowMyCruisesCommand());
        commands.put(Commands.MY_EXCURSIONS, new ShowMyExcursionsCommand());
        commands.put(Commands.SHIPS, new ShowShipsCommand());
        commands.put(Commands.SHIP_BONUSES, new ShowShipBonusesCommand());
        commands.put(Commands.ADD_BONUS, new AddBonusCommand());
        commands.put(Commands.REMOVE_BONUS, new RemoveBonusCommand());
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
        return Objects.nonNull(command) && ControllerUtil.isAccessAllowed(command, role) ?
                command : r -> new Redirect(ControllerUtil.getRedirectPath(role));
    }

    private static String extractCommand(String uri) {
        return uri.replaceAll(RegExp.EXTRACT_COMMAND, "");
    }
}
