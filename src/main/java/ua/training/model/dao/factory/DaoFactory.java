package ua.training.model.dao.factory;

import ua.training.model.dao.ExcursionDao;
import ua.training.model.dao.PortDao;
import ua.training.model.dao.ShipDao;
import ua.training.model.dao.UserDao;

import java.sql.Connection;

/**
 * Максим
 * 06.05.2018
 */
public abstract class DaoFactory {
    private static volatile DaoFactory daoFactory;

    public abstract UserDao createUserDao(Connection connection);
    public abstract ShipDao createShipDao(Connection connection);
    public abstract PortDao createPortDao(Connection connection);
    public abstract ExcursionDao createExcursionDao(Connection connection);

    public static DaoFactory getInstance() {
        if (daoFactory == null) {
            synchronized (DaoFactory.class) {
                if (daoFactory == null) {
                    daoFactory = new JDBCDaoFactory();
                }
            }
        }
        return daoFactory;
    }
}
