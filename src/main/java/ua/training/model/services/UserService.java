package ua.training.model.services;

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

    private final static class Holder {
        private static final UserService INSTANCE = new UserService();
    }

    public static UserService getInstance() {
        return Holder.INSTANCE;
    }

    public void signUp(User user) {

    }

    public Optional<User> signIn(String login, String password) {
        Connection connection = ConnectionPool.getConnection();
        try(UserDao userDao = daoFactory.createUserDao(connection)) {
            Optional<User> user = userDao.getUserByLogin(login);
            if (user.isPresent() && password.equals(user.get().getPassword())) {
                return user;
            } else {
                return Optional.empty();
            }
        }
    }
}
