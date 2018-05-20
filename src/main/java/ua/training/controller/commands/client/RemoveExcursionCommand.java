package ua.training.controller.commands.client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.constants.Attributes;
import ua.training.constants.Messages;
import ua.training.constants.Parameters;
import ua.training.controller.commands.AccessRequired;
import ua.training.controller.commands.Command;
import ua.training.controller.listeners.ActiveUser;
import ua.training.controller.servlets.actions.Redirect;
import ua.training.controller.servlets.actions.ServletAction;
import ua.training.controller.util.ControllerUtil;
import ua.training.model.entities.Excursion;
import ua.training.model.entities.Ship;
import ua.training.model.entities.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Set;

/**
 * Максим
 * 16.05.2018
 */
@SuppressWarnings("unchecked")
@AccessRequired(roles = {User.Role.CLIENT})
public class RemoveExcursionCommand implements Command {
    private final static Logger logger = LogManager.getLogger(RemoveExcursionCommand.class);
    @Override
    public ServletAction execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String excursionName = request.getParameter(Parameters.EXCURSION);
        Ship cruise = (Ship) session.getAttribute(Attributes.CRUISE);
        ActiveUser activeUser = (ActiveUser) session.getAttribute(Attributes.ACTIVE_USER);
        User.Role userRole = (User.Role) session.getAttribute(Attributes.ROLE);

        Map<String, Set<Excursion>> cart = ControllerUtil.getCart(session);
        cart.get(cruise.getNameEn()).removeIf(e -> e.getNameEn().equals(excursionName));
        session.setAttribute(Attributes.CART, cart);
        logger.info(String.format(Messages.LOG_REMOVE_EXCURSION,
                userRole, activeUser.getLogin(), excursionName));
        return new Redirect(request.getHeader(Attributes.REFERER));
    }
}
