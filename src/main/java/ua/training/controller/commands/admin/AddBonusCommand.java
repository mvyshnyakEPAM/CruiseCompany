package ua.training.controller.commands.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.constants.Attributes;
import ua.training.constants.Messages;
import ua.training.constants.Parameters;
import ua.training.controller.commands.AccessRequired;
import ua.training.controller.commands.Command;
import ua.training.controller.listeners.ActiveUser;
import ua.training.controller.servlets.actions.Redirect;
import ua.training.controller.servlets.actions.ServletAction;
import ua.training.model.entities.Ship;
import ua.training.model.entities.User;
import ua.training.model.services.ShipService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Максим
 * 05.05.2018
 */
@AccessRequired(roles = {User.Role.ADMIN})
public class AddBonusCommand implements Command {
    private final static Logger logger = LogManager.getLogger(AddBonusCommand.class);
    private ShipService shipService = ShipService.getInstance();
    @Override
    public ServletAction execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        ActiveUser activeUser = (ActiveUser) session.getAttribute(Attributes.ACTIVE_USER);
        User.Role userRole = (User.Role) session.getAttribute(Attributes.ROLE);
        Ship ship = (Ship) session.getAttribute(Attributes.SHIP);
        String bonus = request.getParameter(Parameters.BONUS);
        shipService.addBonusToShip(ship.getId(), bonus);
        logger.info(String.format(Messages.LOG_ADD_BONUS,
                userRole, activeUser.getLogin(), bonus, ship.getNameEn()));
        return new Redirect(request.getHeader(Attributes.REFERER));
    }
}
