package ua.training.controller.commands.client;

import ua.training.constants.Attributes;
import ua.training.controller.commands.AccessRequired;
import ua.training.controller.commands.Command;
import ua.training.controller.listeners.LoginDto;
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
@AccessRequired(roles = {User.Role.CLIENT}, path = "/client/show-my-excursions")
public class ShowMyExcursionsCommand implements Command {
    private ExcursionService excursionService = ExcursionService.getInstance();
    @Override
    public ServletAction execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        int userId = ((LoginDto) request.getSession()
                .getAttribute(Attributes.USER)).getId();
        String shipName = request.getParameter("shipName");
        String locale = (String) session.getAttribute(Attributes.LANGUAGE);
        List<Excursion> excursions = excursionService
                .getAllExcursionsByUserAndCruise(userId, shipName, locale);
        request.setAttribute("myExcursions", excursions);
        return new Forward("/WEB-INF/client/my_excursions_list.jsp");
    }
}
