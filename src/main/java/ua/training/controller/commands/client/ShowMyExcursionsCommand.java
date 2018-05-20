package ua.training.controller.commands.client;

import ua.training.constants.Attributes;
import ua.training.constants.Pages;
import ua.training.constants.Parameters;
import ua.training.controller.commands.AccessRequired;
import ua.training.controller.commands.Command;
import ua.training.controller.listeners.ActiveUser;
import ua.training.controller.servlets.actions.Forward;
import ua.training.controller.servlets.actions.ServletAction;
import ua.training.model.entities.Excursion;
import ua.training.model.entities.User;
import ua.training.model.services.ExcursionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Максим
 * 08.05.2018
 */
@AccessRequired(roles = {User.Role.CLIENT})
public class ShowMyExcursionsCommand implements Command {
    private ExcursionService excursionService = ExcursionService.getInstance();
    @Override
    public ServletAction execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        int userId = ((ActiveUser) request.getSession()
                .getAttribute(Attributes.ACTIVE_USER)).getId();
        String cruiseName = request.getParameter(Parameters.CRUISE);
        String locale = (String) session.getAttribute(Attributes.LOCALE);
        List<Excursion> excursions = excursionService
                .getAllExcursionsByUserAndCruise(userId, cruiseName, locale);
        request.setAttribute(Attributes.MY_EXCURSIONS, excursions);
        return new Forward(Pages.MY_EXCURSIONS);
    }
}
