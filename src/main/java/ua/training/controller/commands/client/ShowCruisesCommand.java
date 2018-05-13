package ua.training.controller.commands.client;

import ua.training.constants.Attributes;
import ua.training.constants.RegExp;
import ua.training.controller.commands.AccessRequired;
import ua.training.controller.commands.Command;
import ua.training.controller.servlets.actions.Forward;
import ua.training.controller.servlets.actions.Redirect;
import ua.training.controller.servlets.actions.ServletAction;
import ua.training.model.entities.Ship;
import ua.training.model.entities.User;
import ua.training.model.services.ShipService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Максим
 * 05.05.2018
 */
@AccessRequired(roles = {User.Role.CLIENT}, regExp = RegExp.COMMAND_SHOW_CRUISES)
public class ShowCruisesCommand implements Command {
    private ShipService shipService = ShipService.getInstance();
    @Override
    public ServletAction execute(HttpServletRequest request) {
        String locale = (String) request.getSession().getAttribute(Attributes.LANGUAGE);
        int numberOfPages = shipService.getNumberOfPages();
        int pageNumber = Integer.parseInt(request.getParameter("page"));
        if (numberOfPages > 0 && numberOfPages >= pageNumber) {
            List<Ship> cruises = shipService.getCruisesPerPage(pageNumber, locale);
            request.setAttribute("numberOfPages", numberOfPages);
            request.setAttribute("cruises", cruises);
            return new Forward("/WEB-INF/client/cruises_list.jsp");
        } else {
            return new Redirect("/company/client/show-cruises?page=" +
                    (numberOfPages > 0 ? numberOfPages : 1));
        }
    }
}
