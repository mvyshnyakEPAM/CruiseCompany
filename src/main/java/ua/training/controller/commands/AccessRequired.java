package ua.training.controller.commands;

import ua.training.model.entities.User;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Максим
 * 13.05.2018
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface AccessRequired {
    User.Role[] roles();
    String regExp();
}
