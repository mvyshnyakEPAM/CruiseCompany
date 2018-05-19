package ua.training.controller.commands.client;

import ua.training.constants.Attributes;
import ua.training.constants.Messages;
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
import ua.training.model.services.CruiseService;

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
    private CruiseService cruiseService = CruiseService.getInstance();
    @Override
    public ServletAction execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        int userId = ((ActiveUser) session.getAttribute(Attributes.USER)).getId();
        Ship ship = (Ship) session.getAttribute("cruise");
        Set<Excursion> excursions = ControllerUtil.getCart(session)
                .getOrDefault(ship.getNameEn(), new HashSet<>());
        try {
            boolean bought = cruiseService.payCruise(userId, ship, excursions);
            request.setAttribute("buyResult", bought ? Messages.SUCCESSFUL_PURCHASE : Messages.NO_FREE_PLACES);
            request.setAttribute("alert", bought ? "success" : "danger");
        } catch (CruiseAlreadyBoughtException e) {
            request.setAttribute("buyResult", Messages.CRUISE_ALREADY_BOUGHT);
            request.setAttribute("alert", "warning");
        }
        return new Forward("/WEB-INF/client/buy_result.jsp");
    }
}
