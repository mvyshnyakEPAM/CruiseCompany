package ua.training.controller.commands.client;

import ua.training.constants.*;
import ua.training.controller.commands.AccessRequired;
import ua.training.controller.commands.Command;
import ua.training.controller.servlets.actions.Forward;
import ua.training.controller.servlets.actions.Redirect;
import ua.training.controller.servlets.actions.ServletAction;
import ua.training.model.entities.Ship;
import ua.training.model.entities.User;
import ua.training.model.services.CruiseService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

/**
 * Максим
 * 05.05.2018
 */
@AccessRequired(roles = {User.Role.CLIENT})
public class ShowCruisesCommand implements Command {
    private CruiseService cruiseService = CruiseService.getInstance();
    @Override
    public ServletAction execute(HttpServletRequest request) {
        String locale = (String) request.getSession().getAttribute(Attributes.LOCALE);
        int numberOfPages = cruiseService.getNumberOfPages();
        try {
            String page = request.getParameter(Parameters.PAGE);
            int pageNumber = Objects.isNull(page) ? 1 : Integer.parseInt(page);
            if (pageNumber > 0 && numberOfPages >= pageNumber) {
                List<Ship> cruises = cruiseService.getCruisesPerPage(pageNumber, locale);
                request.setAttribute(Attributes.NUMBER_OF_PAGES, numberOfPages);
                request.setAttribute(Attributes.CRUISES, cruises);
                return new Forward(Pages.CRUISE_LIST);
            } else {
                return new Redirect(URLs.CRUISE_LIST +
                        (pageNumber > 0 ? numberOfPages : 1));
            }
        } catch (NullPointerException | NumberFormatException e) {
            return new Redirect(URLs.CRUISE_LIST + 1);
        }
    }
}
