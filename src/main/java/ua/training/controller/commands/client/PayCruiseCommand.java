package ua.training.controller.commands.client;

import ua.training.constants.Attributes;
import ua.training.constants.URLs;
import ua.training.controller.commands.AccessRequired;
import ua.training.controller.commands.Command;
import ua.training.controller.listeners.LoginDto;
import ua.training.controller.servlets.actions.Redirect;
import ua.training.controller.servlets.actions.ServletAction;
import ua.training.controller.util.ControllerUtil;
import ua.training.model.entities.Excursion;
import ua.training.model.entities.Ship;
import ua.training.model.entities.User;
import ua.training.model.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Максим
 * 08.05.2018
 */

@AccessRequired(roles = {User.Role.CLIENT}, path = "/client/pay-cruise")
public class PayCruiseCommand implements Command {
    private UserService userService = UserService.getInstance();
    @Override
    public ServletAction execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        int userId = ((LoginDto) session.getAttribute(Attributes.USER)).getId();
        Ship ship = (Ship) session.getAttribute("cruise");
        List<Excursion> excursions = ControllerUtil.getCart(session).get(ship.getNameEn());
        userService.payCruise(userId, ship, excursions);
        return new Redirect(URLs.CLIENT);
    }
}
