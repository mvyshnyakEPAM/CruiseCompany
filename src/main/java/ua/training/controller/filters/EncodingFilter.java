package ua.training.controller.filters;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Максим
 * 01.04.2018
 */
public class EncodingFilter extends BaseFilter {
    @Override
    public void doFilter(HttpServletRequest request,
                         HttpServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {
        response.setContentType("text/html");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        filterChain.doFilter(request, response);
    }
}
