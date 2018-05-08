package ua.training.model.dao.factory;

import ua.training.model.dao.ExcursionDao;
import ua.training.model.dao.PortDao;
import ua.training.model.dao.ShipDao;
import ua.training.model.dao.UserDao;
import ua.training.model.dao.impl.ExcursionDaoImpl;
import ua.training.model.dao.impl.PortDaoImpl;
import ua.training.model.dao.impl.ShipDaoImpl;
import ua.training.model.dao.impl.UserDaoImpl;

import java.sql.Connection;

/**
 * Максим
 * 06.05.2018
 */
public class JDBCDaoFactory extends DaoFactory {
    @Override
    public UserDao createUserDao(Connection connection) {
        return new UserDaoImpl(connection);
    }

    @Override
    public ShipDao createShipDao(Connection connection) {
        return new ShipDaoImpl(connection);
    }

    @Override
    public PortDao createPortDao(Connection connection) {
        return new PortDaoImpl(connection);
    }

    @Override
    public ExcursionDao createExcursionDao(Connection connection) {
        return new ExcursionDaoImpl(connection);
    }
}
