package ua.training.controller.commands.admin;

import ua.training.constants.Attributes;
import ua.training.constants.Pages;
import ua.training.constants.Parameters;
import ua.training.controller.commands.AccessRequired;
import ua.training.controller.commands.Command;
import ua.training.controller.servlets.actions.Forward;
import ua.training.controller.servlets.actions.Redirect;
import ua.training.controller.servlets.actions.ServletAction;
import ua.training.model.entities.Ship;
import ua.training.model.entities.User;
import ua.training.model.entities.enums.Bonus;
import ua.training.model.services.ShipService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Максим
 * 05.05.2018
 */
@AccessRequired(roles = {User.Role.ADMIN})
public class ShowShipBonusesCommand implements Command {
    private ShipService shipService = ShipService.getInstance();
    @Override
    public ServletAction execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String locale = (String) session.getAttribute(Attributes.LOCALE);
        String shipName = request.getParameter(Parameters.SHIP);
        Optional<Ship> ship = shipService.getShipByName(shipName, locale);
        if (ship.isPresent()) {
            List<Bonus> shipBonuses = shipService.getAllBonusesByShip(ship.get().getId());
            session.setAttribute(Attributes.SHIP, ship.get());
            request.setAttribute(Attributes.SHIP_BONUSES, shipBonuses);
            List<Bonus> bonuses = new ArrayList<>(Arrays.asList(Bonus.values()));
            bonuses.removeAll(shipBonuses);
            request.setAttribute(Attributes.BONUSES, bonuses);
            return new Forward(Pages.SHIP_BONUSES_LIST);
        }
        return new Redirect(Attributes.REFERER);
    }
}
