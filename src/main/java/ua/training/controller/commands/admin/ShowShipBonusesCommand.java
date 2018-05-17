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
import java.util.List;
import java.util.Optional;

/**
 * Максим
 * 05.05.2018
 */
@AccessRequired(roles = {User.Role.ADMIN}, path = "/admin/show-ship-bonuses")
public class ShowShipBonusesCommand implements Command {
    private CruiseService cruiseService = CruiseService.getInstance();
    @Override
    public ServletAction execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String locale = (String) session.getAttribute(Attributes.LANGUAGE);
        String shipName = request.getParameter("ship");
        Optional<Ship> ship = cruiseService.getShipByName(shipName, locale);
        if (ship.isPresent()) {
            List<Bonus> bonuses = cruiseService.getAllBonusesByShip(ship.get().getId());
            session.setAttribute("ship", ship.get());
            request.setAttribute("shipBonuses", bonuses);
            request.setAttribute("bonuses", Bonus.values());
            return new Forward("/WEB-INF/admin/ship_bonuses_list.jsp");
        }
        return new Redirect(Attributes.REFERER);
    }
}
