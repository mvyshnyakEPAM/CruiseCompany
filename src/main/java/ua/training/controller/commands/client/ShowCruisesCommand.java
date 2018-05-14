package ua.training.controller.commands.client;

import ua.training.constants.*;
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
@AccessRequired(roles = {User.Role.CLIENT}, path = CommandPaths.SHOW_CRUISES)
public class ShowCruisesCommand implements Command {
    private ShipService shipService = ShipService.getInstance();
    @Override
    public ServletAction execute(HttpServletRequest request) {
        String locale = (String) request.getSession().getAttribute(Attributes.LANGUAGE);
        int numberOfPages = shipService.getNumberOfPages();
        try {
            int pageNumber = Integer.parseInt(request.getParameter(Parameters.PAGE));
            if (numberOfPages > 0 && numberOfPages >= pageNumber) {
                List<Ship> cruises = shipService.getCruisesPerPage(pageNumber, locale);
                request.setAttribute(Attributes.NUMBER_OF_PAGES, numberOfPages);
                request.setAttribute(Attributes.CRUISES, cruises);
                return new Forward(Pages.CRUISE_LIST);
            } else {
                return new Redirect(URLs.CRUISE_LIST +
                        (numberOfPages > 0 ? numberOfPages : 1));
            }
        } catch (NullPointerException | NumberFormatException e) {
            return new Redirect(URLs.CRUISE_LIST + 1);
        }
    }
}
