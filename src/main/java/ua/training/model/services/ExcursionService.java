package ua.training.model.services;

import ua.training.model.dao.ExcursionDao;
import ua.training.model.dao.cp.ConnectionPool;
import ua.training.model.dao.factory.DaoFactory;
import ua.training.model.entities.Excursion;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

/**
 * Максим
 * 16.05.2018
 */
public class ExcursionService {
    private DaoFactory daoFactory = DaoFactory.getInstance();

    private ExcursionService() {
    }

    private final static class ExcursionServiceHolder {
        private static final ExcursionService INSTANCE = new ExcursionService();
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static ExcursionService getInstance() {
        return ExcursionService.ExcursionServiceHolder.INSTANCE;
    }

    /**
     * Gets excursion by name.
     *
     * @param name   the name
     * @param locale the locale
     * @return the excursion by name
     */
    public Optional<Excursion> getExcursionByName(String name, String locale) {
        Connection connection = ConnectionPool.getConnection();
        try(ExcursionDao excursionDao = daoFactory.createExcursionDao(connection)) {
            return excursionDao.getExcursionByName(name, locale);
        }
    }

    /**
     * Gets all excursions by user and cruise.
     *
     * @param userId   the user id
     * @param shipName the ship name
     * @param locale   the locale
     * @return the all excursions by user and cruise
     */
    public List<Excursion> getAllExcursionsByUserAndCruise(int userId, String shipName, String locale) {
        Connection connection = ConnectionPool.getConnection();
        try(ExcursionDao excursionDao = daoFactory.createExcursionDao(connection)) {
            return excursionDao.getAllExcursionsByUserAndCruise(userId, shipName, locale);
        }
    }
}
