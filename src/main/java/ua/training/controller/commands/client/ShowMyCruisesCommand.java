package ua.training.controller.commands.client;

import ua.training.constants.Attributes;
import ua.training.controller.commands.AccessRequired;
import ua.training.controller.commands.Command;
import ua.training.controller.listeners.ActiveUser;
import ua.training.controller.servlets.actions.Forward;
import ua.training.controller.servlets.actions.ServletAction;
import ua.training.model.entities.Ship;
import ua.training.model.entities.User;
import ua.training.model.services.CruiseService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Максим
 * 08.05.2018
 */
@AccessRequired(roles = {User.Role.CLIENT})
public class ShowMyCruisesCommand implements Command {
    private CruiseService cruiseService = CruiseService.getInstance();
    @Override
    public ServletAction execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        int userId = ((ActiveUser) request.getSession()
                .getAttribute(Attributes.USER)).getId();
        String locale = (String) session.getAttribute(Attributes.LOCALE);
        List<Ship> cruises = cruiseService.getAllCruisesByUser(userId, locale);
        request.setAttribute("myCruises", cruises);
        return new Forward("/WEB-INF/client/my_cruises_list.jsp");
    }
}
