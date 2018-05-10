package ua.training.controller.servlets;

import ua.training.constants.Attributes;
import ua.training.controller.commands.Command;
import ua.training.controller.commands.CommandFactory;
import ua.training.controller.servlets.actions.ServletAction;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Максим
 * 27.04.2018
 */
public class Servlet extends HttpServlet {
    private CommandFactory commandFactory;

    @Override
    public void init() throws ServletException {
        getServletContext().setAttribute(Attributes.LOGGED_USERS,
                new ConcurrentHashMap<String, HttpSession>());
        commandFactory = CommandFactory.getInstance();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Command command = commandFactory.getCommand(request);
        ServletAction action = command.execute(request);
        action.execute(request, response);
    }
}
