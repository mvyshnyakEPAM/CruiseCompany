package ua.training.controller.commands.client;

import ua.training.constants.*;
import ua.training.controller.commands.AccessRequired;
import ua.training.controller.commands.Command;
import ua.training.controller.servlets.actions.Forward;
import ua.training.controller.servlets.actions.Redirect;
import ua.training.controller.servlets.actions.ServletAction;
import ua.training.controller.util.ControllerUtil;
import ua.training.model.entities.Excursion;
import ua.training.model.entities.Ship;
import ua.training.model.entities.User;
import ua.training.model.services.ShipService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

/**
 * Максим
 * 08.05.2018
 */

@SuppressWarnings("unchecked")
@AccessRequired(roles = {User.Role.CLIENT})
public class ShowCruiseInfoCommand implements Command {
    ShipService shipService = ShipService.getInstance();
    @Override
    public ServletAction execute(HttpServletRequest request) {
        String cruiseName = request.getParameter(Parameters.CRUISE);
        HttpSession session = request.getSession();

        boolean freePlacesAvailable = shipService.freePlacesAvailable(cruiseName);

        if (!freePlacesAvailable) {
            request.setAttribute(Attributes.BUY_RESULT, Messages.NO_FREE_PLACES);
            request.setAttribute(Attributes.ALERT, Info.DANGER);
            return new Forward(Pages.BUY_RESULT);
        }

        String locale = (String)session.getAttribute(Attributes.LOCALE);
        Optional<Ship> cruise = shipService.getCruiseByName(cruiseName, locale);

        if (cruise.isPresent()) {
            Map<String, Set<Excursion>> cart = ControllerUtil.getCart(session);
            Set<Excursion> excursions = cart.get(cruise.get().getNameEn());
            int cruisePrice = calculatePrice(cruise.get().getPrice(), excursions);
            session.setAttribute(Attributes.CRUISE, cruise.get());
            request.setAttribute(Attributes.PRICE, cruisePrice);
            request.setAttribute(Attributes.EXCURSIONS, excursions);
            return new Forward(Pages.CRUISE_INFO);
        }
        return new Redirect(URLs.CRUISE_LIST);
    }

    private int calculatePrice(int cruisePrice, Set<Excursion> excursions) {
        return Objects.isNull(excursions) ? cruisePrice :
                cruisePrice + excursions.stream().mapToInt(Excursion::getPrice).sum();
    }
}
