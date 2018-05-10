package ua.training.controller.commands.client;

import ua.training.controller.commands.Command;
import ua.training.controller.servlets.actions.Forward;
import ua.training.controller.servlets.actions.ServletAction;
import ua.training.model.entities.Ship;
import ua.training.model.services.ShipService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Максим
 * 05.05.2018
 */
public class ShowCruisesCommand implements Command {
    private ShipService shipService = ShipService.getInstance();
    @Override
    public ServletAction execute(HttpServletRequest request) {
        List<Ship> cruises = shipService.getAllCruises();
        request.setAttribute("cruises", cruises);
        return new Forward("/WEB-INF/client/cruises_list.jsp");
    }
}
