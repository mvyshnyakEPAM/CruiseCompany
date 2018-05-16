package ua.training.model.services;

import org.apache.commons.codec.digest.DigestUtils;
import ua.training.controller.exceptions.LoginAlreadyExistsException;
import ua.training.model.dao.ExcursionDao;
import ua.training.model.dao.ShipDao;
import ua.training.model.dao.UserDao;
import ua.training.model.dao.cp.ConnectionPool;
import ua.training.model.dao.factory.DaoFactory;
import ua.training.model.entities.Excursion;
import ua.training.model.entities.Ship;
import ua.training.model.entities.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
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

    public void signUp(User user) throws LoginAlreadyExistsException {
        Connection connection = ConnectionPool.getConnection();
        try(UserDao userDao = daoFactory.createUserDao(connection)) {
            userDao.create(user);
        }
    }

    public Optional<User> signIn(String login, String password) {
        Connection connection = ConnectionPool.getConnection();
        try(UserDao userDao = daoFactory.createUserDao(connection)) {
            password = DigestUtils.md2Hex(password);
            Optional<User> user = userDao.getUserByLogin(login);
            if (user.isPresent() && password.equals(user.get().getPassword())) {
                return user;
            } else {
                return Optional.empty();
            }
        }
    }

    public boolean payCruise(int userId, Ship ship, List<Excursion> excursions) {
        Connection connection = ConnectionPool.getConnection();
        try(ShipDao shipDao = daoFactory.createShipDao(connection);
            ExcursionDao excursionDao = daoFactory.createExcursionDao(connection)) {
            connection.setAutoCommit(false);
            for (Excursion excursion : excursions) {
                excursionDao.addExcursionToUser(excursion.getId(), userId);
            }
            shipDao.addShipToUser(ship.getId(), userId);
            connection.commit();
            return true;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }
}
