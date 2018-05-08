package ua.training.model.dao;

import ua.training.model.entities.User;

import java.util.Optional;

/**
 * Максим
 * 06.05.2018
 */
public interface UserDao extends GenericDao<User> {
    Optional<User> getUserByLogin(String login);
}
