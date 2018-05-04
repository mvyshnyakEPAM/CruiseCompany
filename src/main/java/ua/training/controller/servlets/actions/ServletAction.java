package ua.training.controller.servlets.actions;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Максим
 * 03.05.2018
 */
public abstract class ServletAction {
    String path;

    ServletAction(String path) {
        this.path = path;
    }

    public abstract void execute(HttpServletRequest request,
                                 HttpServletResponse response) throws ServletException, IOException;
}
