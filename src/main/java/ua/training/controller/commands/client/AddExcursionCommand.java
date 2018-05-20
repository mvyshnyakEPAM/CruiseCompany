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
import ua.training.model.services.ExcursionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * Максим
 * 15.05.2018
 */
@SuppressWarnings("unchecked")
@AccessRequired(roles = {User.Role.CLIENT})
public class AddExcursionCommand implements Command {
    private final static Logger logger = LogManager.getLogger(AddExcursionCommand.class);
    private ExcursionService excursionService = ExcursionService.getInstance();
    @Override
    public ServletAction execute(HttpServletRequest request) {
        String excursionName = request.getParameter(Parameters.EXCURSION);
        HttpSession session = request.getSession();

        String locale = (String) session.getAttribute(Attributes.LOCALE);
        Optional<Excursion> excursion = excursionService.getExcursionByName(excursionName, locale);
        Ship cruise = (Ship) session.getAttribute(Attributes.CRUISE);
        ActiveUser activeUser = (ActiveUser) session.getAttribute(Attributes.ACTIVE_USER);
        User.Role userRole = (User.Role) session.getAttribute(Attributes.ROLE);

        if (excursion.isPresent()) {
            Map<String, Set<Excursion>> cart = ControllerUtil.getCart(session);
            cart.putIfAbsent(cruise.getNameEn(), new HashSet<>());
            cart.get(cruise.getNameEn()).add(excursion.get());
            session.setAttribute(Attributes.CART, cart);
            logger.info(String.format(Messages.LOG_ADD_EXCURSION,
                    userRole, activeUser.getLogin(), excursion.get().getNameEn()));
        }
        return new Redirect(request.getHeader(Attributes.REFERER));
    }
}
