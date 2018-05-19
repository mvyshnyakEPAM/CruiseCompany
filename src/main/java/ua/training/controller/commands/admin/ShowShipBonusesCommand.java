package ua.training.controller.commands.admin;

import ua.training.constants.Attributes;
import ua.training.controller.commands.AccessRequired;
import ua.training.controller.commands.Command;
import ua.training.controller.servlets.actions.Forward;
import ua.training.controller.servlets.actions.Redirect;
import ua.training.controller.servlets.actions.ServletAction;
import ua.training.model.entities.Ship;
import ua.training.model.entities.User;
import ua.training.model.entities.enums.Bonus;
import ua.training.model.services.CruiseService;

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
    private CruiseService cruiseService = CruiseService.getInstance();
    @Override
    public ServletAction execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String locale = (String) session.getAttribute(Attributes.LOCALE);
        String shipName = request.getParameter("ship");
        Optional<Ship> ship = cruiseService.getShipByName(shipName, locale);
        if (ship.isPresent()) {
            List<Bonus> shipBonuses = cruiseService.getAllBonusesByShip(ship.get().getId());
            session.setAttribute("ship", ship.get());
            request.setAttribute("shipBonuses", shipBonuses);
            List<Bonus> bonuses = new ArrayList<>(Arrays.asList(Bonus.values()));
            bonuses.removeAll(shipBonuses);
            request.setAttribute("bonuses", bonuses);
            return new Forward("/WEB-INF/admin/ship_bonuses_list.jsp");
        }
        return new Redirect(Attributes.REFERER);
    }
}
