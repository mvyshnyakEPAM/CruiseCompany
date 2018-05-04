package ua.training.controller.filters;

import ua.training.model.entities.User;
import ua.training.model.entities.enums.Role;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static ua.training.controller.constants.Attributes.ATTRIBUTE_USER;
import static ua.training.controller.constants.Pages.PAGE_LOGIN;
import static ua.training.controller.constants.RegExp.*;

/**
 * Максим
 * 30.04.2018
 */
public class AuthFilter extends BaseFilter {
    @Override
    public void doFilter(HttpServletRequest request,
                         HttpServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String requestURI = request.getRequestURI();
        User user = (User) session.getAttribute(ATTRIBUTE_USER);

        if (user != null && isAccessAllowed(requestURI, user.getRole())) {
            filterChain.doFilter(request, response);
        } else {
            session.removeAttribute(ATTRIBUTE_USER);
            response.sendRedirect(PAGE_LOGIN);
        }
    }

    private boolean isAccessAllowed(String requestURI, Role role) {
        return (requestURI.matches(REGEXP_ADMIN_URL) && (role == Role.ADMIN)) ||
                (requestURI.matches(REGEXP_CLIENT_URL) && (role == Role.CLIENT));
    }
}
