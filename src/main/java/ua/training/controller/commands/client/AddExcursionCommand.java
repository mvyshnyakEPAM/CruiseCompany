package ua.training.controller.commands.client;

import ua.training.constants.Attributes;
import ua.training.controller.commands.AccessRequired;
import ua.training.controller.commands.Command;
import ua.training.controller.servlets.actions.Redirect;
import ua.training.controller.servlets.actions.ServletAction;
import ua.training.controller.util.ControllerUtil;
import ua.training.model.entities.Excursion;
import ua.training.model.entities.Ship;
import ua.training.model.entities.User;
import ua.training.model.services.ExcursionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Максим
 * 15.05.2018
 */
@SuppressWarnings("unchecked")
@AccessRequired(roles = {User.Role.CLIENT})
public class AddExcursionCommand implements Command {
    private ExcursionService excursionService = ExcursionService.getInstance();
    @Override
    public ServletAction execute(HttpServletRequest request) {
        String excursionName = request.getParameter("excursion");
        HttpSession session = request.getSession();

        String locale = (String) session.getAttribute(Attributes.LANGUAGE);
        Optional<Excursion> excursion = excursionService.getExcursionByName(excursionName, locale);
        Ship ship = (Ship) session.getAttribute("cruise");
        if (excursion.isPresent()) {
            Map<String, Set<Excursion>> bucket = ControllerUtil.getCart(session);
            bucket.putIfAbsent(ship.getNameEn(), new HashSet<>());
            bucket.get(ship.getNameEn()).add(excursion.get());
            session.setAttribute("cart", bucket);
        }
        return new Redirect(request.getHeader(Attributes.REFERER));
    }
}
