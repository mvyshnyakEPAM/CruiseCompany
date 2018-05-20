package ua.training.controller.commands.client;

import ua.training.constants.Attributes;
import ua.training.constants.Pages;
import ua.training.controller.commands.AccessRequired;
import ua.training.controller.commands.Command;
import ua.training.controller.listeners.ActiveUser;
import ua.training.controller.servlets.actions.Forward;
import ua.training.controller.servlets.actions.ServletAction;
import ua.training.model.entities.Ship;
import ua.training.model.entities.User;
import ua.training.model.services.ShipService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Максим
 * 08.05.2018
 */
@AccessRequired(roles = {User.Role.CLIENT})
public class ShowMyCruisesCommand implements Command {
    private ShipService shipService = ShipService.getInstance();
    @Override
    public ServletAction execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        int userId = ((ActiveUser) request.getSession()
                .getAttribute(Attributes.ACTIVE_USER)).getId();
        String locale = (String) session.getAttribute(Attributes.LOCALE);
        List<Ship> cruises = shipService.getAllCruisesByUser(userId, locale);
        request.setAttribute(Attributes.MY_CRUISES, cruises);
        return new Forward(Pages.MY_CRUISES);
    }
}
