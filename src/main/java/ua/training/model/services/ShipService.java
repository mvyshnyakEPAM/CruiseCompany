package ua.training.model.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.controller.exceptions.CruiseAlreadyBoughtException;
import ua.training.model.dao.ExcursionDao;
import ua.training.model.dao.PortDao;
import ua.training.model.dao.ShipDao;
import ua.training.model.dao.cp.ConnectionPool;
import ua.training.model.dao.factory.DaoFactory;
import ua.training.model.entities.Excursion;
import ua.training.model.entities.Port;
import ua.training.model.entities.Ship;
import ua.training.model.entities.enums.Bonus;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Максим
 * 16.05.2018
 */
public class ShipService {
    private final static Logger logger = LogManager.getLogger(ShipService.class);
    DaoFactory daoFactory = DaoFactory.getInstance();

    private ShipService() {
    }

    private final static class ShipServiceHolder {
        private static final ShipService INSTANCE = new ShipService();
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static ShipService getInstance() {
        return ShipServiceHolder.INSTANCE;
    }

    /**
     * Gets all cruises.
     *
     * @param locale the locale
     * @return the all cruises
     */
    public List<Ship> getAllCruises(String locale) {
        Connection connection = ConnectionPool.getConnection();
        try(ShipDao shipDao = daoFactory.createShipDao(connection);
            PortDao portDao = daoFactory.createPortDao(connection)) {
            List<Ship> ships = shipDao.findAll(locale);
            for (Ship ship : ships) {
                ship.setPorts(portDao.getAllPortsByShip(ship.getId(), locale));
            }
            return ships;
        }
    }

    /**
     * Gets cruises per page.
     *
     * @param pageNumber the page number
     * @param locale     the locale
     * @return the cruises per page
     */
    public List<Ship> getCruisesPerPage(int pageNumber, String locale) {
        Connection connection = ConnectionPool.getConnection();
        try(ShipDao shipDao = daoFactory.createShipDao(connection);
            PortDao portDao = daoFactory.createPortDao(connection)) {
            List<Ship> ships = shipDao.getAllShipsPerPage(pageNumber, locale);
            for (Ship ship : ships) {
                ship.setPorts(portDao.getAllPortsByShip(ship.getId(), locale));
            }
            return ships;
        }
    }

    /**
     * Gets cruise by name.
     *
     * @param name   the name
     * @param locale the locale
     * @return the cruise by name
     */
    public Optional<Ship> getCruiseByName(String name, String locale) {
        Connection connection = ConnectionPool.getConnection();
        try(ShipDao shipDao = daoFactory.createShipDao(connection);
            PortDao portDao = daoFactory.createPortDao(connection);
            ExcursionDao excursionDao = daoFactory.createExcursionDao(connection)) {
            Optional<Ship> cruise = shipDao.getShipByName(name, locale);
            if (cruise.isPresent()) {
                cruise.get().setBonuses(shipDao.getAllBonusesByShip(cruise.get().getId()));
                cruise.get().setPorts(portDao.getAllPortsByShip(cruise.get().getId(), locale));
                for (Port port : cruise.get().getPorts()) {
                    port.setExcursions(excursionDao.getAllExcursionsByPort(port.getId(), locale));
                }
                return cruise;
            }
            return Optional.empty();
        }
    }

    /**
     * Gets all cruises by user.
     *
     * @param userId the user id
     * @param locale the locale
     * @return the all cruises by user
     */
    public List<Ship> getAllCruisesByUser(int userId, String locale) {
        Connection connection = ConnectionPool.getConnection();
        try(ShipDao shipDao = daoFactory.createShipDao(connection);
            PortDao portDao = daoFactory.createPortDao(connection)) {
            List<Ship> ships = shipDao.getAllShipsByUser(userId, locale);
            for (Ship ship : ships) {
                ship.setPorts(portDao.getAllPortsByShip(ship.getId(), locale));
                ship.setBonuses(shipDao.getAllBonusesByShip(ship.getId()));
            }
            return ships;
        }
    }

    /**
     * Adds a bought cruise and excursions to user.
     *
     * @param userId     the user id
     * @param ship       the ship
     * @param excursions the excursions
     * @return the boolean
     * @throws CruiseAlreadyBoughtException the cruise already bought exception
     */
    public boolean payCruise(int userId, Ship ship, Set<Excursion> excursions)
            throws CruiseAlreadyBoughtException {
        Connection connection = ConnectionPool.getConnection();
        try(ShipDao shipDao = daoFactory.createShipDao(connection);
            ExcursionDao excursionDao = daoFactory.createExcursionDao(connection)) {
            connection.setAutoCommit(false);
            if (freePlacesAvailable(ship.getNameEn())) {
                shipDao.addShipToUser(ship.getId(), userId);
                ship.setFreePlaces(ship.getFreePlaces() - 1);
                shipDao.update(ship);
                for (Excursion excursion : excursions) {
                    excursionDao.addExcursionToUser(excursion.getId(), userId, ship.getNameEn());
                }
                connection.commit();
                return true;
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            try {
                connection.rollback();
            } catch (SQLException e1) {
                logger.error(e.getMessage(), e);
                throw new RuntimeException(e);
            }
        }
        return false;
    }

    /**
     * Gets ship by name.
     *
     * @param name   the name
     * @param locale the locale
     * @return the ship by name
     */
    public Optional<Ship> getShipByName(String name, String locale) {
        Connection connection = ConnectionPool.getConnection();
        try(ShipDao shipDao = daoFactory.createShipDao(connection)) {
            return shipDao.getShipByName(name, locale);
        }
    }

    /**
     * Gets all ships per one page.
     *
     * @param pageNumber the page number
     * @param locale     the locale
     * @return the all ships per page
     */
    public List<Ship> getAllShipsPerPage(int pageNumber, String locale) {
        Connection connection = ConnectionPool.getConnection();
        try(ShipDao shipDao = daoFactory.createShipDao(connection)) {
            return shipDao.getAllShipsPerPage(pageNumber, locale);
        }
    }

    /**
     * Gets all bonuses by ship.
     *
     * @param shipId the ship id
     * @return the all bonuses by ship
     */
    public List<Bonus> getAllBonusesByShip(int shipId) {
        Connection connection = ConnectionPool.getConnection();
        try(ShipDao shipDao = daoFactory.createShipDao(connection)) {
            return shipDao.getAllBonusesByShip(shipId);
        }
    }

    /**
     * Adds bonus to ship.
     *
     * @param shipId the ship id
     * @param bonus  the bonus
     */
    public void addBonusToShip(int shipId, String bonus) {
        Connection connection = ConnectionPool.getConnection();
        try(ShipDao shipDao = daoFactory.createShipDao(connection)) {
            shipDao.addBonusToShip(shipId, bonus);
        }
    }

    /**
     * Removes bonus from ship.
     *
     * @param shipId the ship id
     * @param bonus  the bonus
     */
    public void removeBonusFromShip(int shipId, String bonus) {
        Connection connection = ConnectionPool.getConnection();
        try(ShipDao shipDao = daoFactory.createShipDao(connection)) {
            shipDao.deleteBonusFromShip(shipId, bonus);
        }
    }


    /**
     * Gets number of pages considering number of ships.
     *
     * @return the number of pages
     */
    public int getNumberOfPages() {
        Connection connection = ConnectionPool.getConnection();
        try(ShipDao shipDao = daoFactory.createShipDao(connection)) {
            int numberOfShips = shipDao.countAllShips();
            return (int) Math.ceil(numberOfShips / 4.0);
        }
    }

    /**
     * Checks whether there is free places on cruise.
     *
     * @param name the name
     * @return the boolean
     */
    public boolean freePlacesAvailable(String name) {
        Connection connection = ConnectionPool.getConnection();
        try(ShipDao shipDao = daoFactory.createShipDao(connection)) {
            int freePlaces = shipDao.getFreePlacesAmount(name);
            return freePlaces > 0;
        }
    }
}
