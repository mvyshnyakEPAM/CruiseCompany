package ua.training.controller.commands.client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.constants.Attributes;
import ua.training.constants.Info;
import ua.training.constants.Messages;
import ua.training.constants.Pages;
import ua.training.controller.commands.AccessRequired;
import ua.training.controller.commands.Command;
import ua.training.controller.exceptions.CruiseAlreadyBoughtException;
import ua.training.controller.listeners.ActiveUser;
import ua.training.controller.servlets.actions.Forward;
import ua.training.controller.servlets.actions.ServletAction;
import ua.training.controller.util.ControllerUtil;
import ua.training.model.entities.Excursion;
import ua.training.model.entities.Ship;
import ua.training.model.entities.User;
import ua.training.model.services.ShipService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Set;

/**
 * Максим
 * 08.05.2018
 */

@AccessRequired(roles = {User.Role.CLIENT})
public class PayCruiseCommand implements Command {
    private final static Logger logger = LogManager.getLogger(PayCruiseCommand.class);
    private ShipService shipService = ShipService.getInstance();
    @Override
    public ServletAction execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        ActiveUser activeUser = ((ActiveUser) session.getAttribute(Attributes.ACTIVE_USER));
        User.Role userRole = (User.Role) session.getAttribute(Attributes.ROLE);
        Ship cruise = (Ship) session.getAttribute(Attributes.CRUISE);
        Set<Excursion> excursions = ControllerUtil.getCart(session)
                .getOrDefault(cruise.getNameEn(), new HashSet<>());
        try {
            boolean isBought = shipService.payCruise(activeUser.getId(), cruise, excursions);
            if (isBought) {
                request.setAttribute(Attributes.BUY_RESULT, Messages.SUCCESSFUL_PURCHASE);
                request.setAttribute(Attributes.ALERT, Info.SUCCESS);
                logger.info(Messages.SUCCESSFUL_PURCHASE,
                        userRole, activeUser.getLogin(), cruise.getNameEn());
            } else {
                request.setAttribute(Attributes.BUY_RESULT, Messages.NO_FREE_PLACES);
                request.setAttribute(Attributes.ALERT, Info.DANGER);
                logger.info(Messages.LOG_NO_FREE_PLACES,
                        userRole, activeUser.getLogin(), cruise.getNameEn());
            }
        } catch (CruiseAlreadyBoughtException e) {
            request.setAttribute(Attributes.BUY_RESULT, Messages.CRUISE_ALREADY_BOUGHT);
            request.setAttribute(Attributes.ALERT, Info.WARNING);
            logger.info(Messages.LOG_ALREADY_BOUGHT,
                    userRole, activeUser.getLogin(), cruise.getNameEn());
        }
        return new Forward(Pages.BUY_RESULT);
    }
}
