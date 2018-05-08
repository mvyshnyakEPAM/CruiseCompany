package ua.training.controller.filters;

import ua.training.constants.Attributes;
import ua.training.constants.Pages;
import ua.training.constants.RegExp;
import ua.training.model.entities.User;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Максим
 * 30.04.2018
 */
public class AuthFilter extends BaseFilter {
    private Map<User.Role, String> uriByRole = new HashMap<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        uriByRole.put(User.Role.GUEST, RegExp.GUEST_URL);
        uriByRole.put(User.Role.CLIENT, RegExp.CLIENT_URL);
        uriByRole.put(User.Role.ADMIN, RegExp.ADMIN_URL);
    }

    @Override
    public void doFilter(HttpServletRequest request,
                         HttpServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpSession session = request.getSession();
        User.Role userRole = (User.Role) session.getAttribute(Attributes.ROLE);

        if (isAccessAllowed(userRole, request.getRequestURI())) {
            filterChain.doFilter(request, response);
        } else {
            session.setAttribute(Attributes.ROLE, User.Role.GUEST);
            response.sendRedirect(Pages.INDEX);
        }
    }

    private boolean isAccessAllowed(User.Role userRole, String uri) {
        return uri.matches(uriByRole.get(userRole));
    }
}
