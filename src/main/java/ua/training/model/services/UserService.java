package ua.training.model.services;

import org.apache.commons.codec.digest.DigestUtils;
import ua.training.controller.exceptions.LoginAlreadyExistsException;
import ua.training.model.dao.UserDao;
import ua.training.model.dao.cp.ConnectionPool;
import ua.training.model.dao.factory.DaoFactory;
import ua.training.model.entities.User;

import java.sql.Connection;
import java.util.Optional;

/**
 * Максим
 * 30.04.2018
 */
public class UserService {
    private DaoFactory daoFactory = DaoFactory.getInstance();

    private UserService() {
    }

    private final static class Holder {
        private static final UserService INSTANCE = new UserService();
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static UserService getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * Signs up user.
     *
     * @param user the user
     * @throws LoginAlreadyExistsException the login already exists exception
     */
    public void signUp(User user) throws LoginAlreadyExistsException {
        Connection connection = ConnectionPool.getConnection();
        try(UserDao userDao = daoFactory.createUserDao(connection)) {
            userDao.create(user);
        }
    }

    /**
     * Signs in user.
     *
     * @param login    the login
     * @param password the password
     * @return the optional
     */
    public Optional<User> signIn(String login, String password) {
        Connection connection = ConnectionPool.getConnection();
        try(UserDao userDao = daoFactory.createUserDao(connection)) {
            password = DigestUtils.md5Hex(password);
            Optional<User> user = userDao.getUserByLogin(login);
            if (user.isPresent() && password.equals(user.get().getPassword())) {
                return user;
            } else {
                return Optional.empty();
            }
        }
    }
}
