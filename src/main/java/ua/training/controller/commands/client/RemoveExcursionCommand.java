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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * Максим
 * 16.05.2018
 */
@SuppressWarnings("unchecked")
@AccessRequired(roles = {User.Role.CLIENT}, path = "/client/remove-excursion")
public class RemoveExcursionCommand implements Command {
    @Override
    public ServletAction execute(HttpServletRequest request) {
        String excursionName = request.getParameter("excursion");
        HttpSession session = request.getSession();
        Ship ship = (Ship) session.getAttribute("cruise");

        Map<String, List<Excursion>> bucket = ControllerUtil.getCart(session);
        bucket.get(ship.getNameEn()).removeIf(e -> e.getNameEn().equals(excursionName));
        session.setAttribute("excursions", bucket);
        return new Redirect(request.getHeader(Attributes.REFERER));
    }
}
