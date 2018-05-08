package ua.training.controller.commands.admin;

import ua.training.controller.commands.Command;
import ua.training.controller.servlets.actions.ServletAction;
import ua.training.model.services.ShipService;

import javax.servlet.http.HttpServletRequest;

/**
 * Максим
 * 05.05.2018
 */
public class AddBonusCommand implements Command {
    private ShipService shipService = new ShipService();
    @Override
    public ServletAction execute(HttpServletRequest request) {
        return null;
    }
}
