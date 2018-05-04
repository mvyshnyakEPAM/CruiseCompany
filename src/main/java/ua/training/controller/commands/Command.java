package ua.training.controller.commands;

import ua.training.controller.servlets.actions.ServletAction;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Максим
 * 27.04.2018
 */
public interface Command {
    ServletAction execute(HttpServletRequest request);
}
