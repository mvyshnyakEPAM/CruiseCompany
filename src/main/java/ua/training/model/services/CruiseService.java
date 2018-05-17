package ua.training.model.services;

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

/**
 * Максим
 * 16.05.2018
 */
public class CruiseService {
    private DaoFactory daoFactory = DaoFactory.getInstance();

    private final static class CruiseServiceHolder {
        private static final CruiseService INSTANCE = new CruiseService();
    }

    public static CruiseService getInstance() {
        return CruiseServiceHolder.INSTANCE;
    }

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

    public Optional<Ship> getCruiseByName(String name, String locale) {
        Connection connection = ConnectionPool.getConnection();
        try(ShipDao shipDao = daoFactory.createShipDao(connection);
            PortDao portDao = daoFactory.createPortDao(connection);
            ExcursionDao excursionDao = daoFactory.createExcursionDao(connection)) {
            Optional<Ship> ship = shipDao.getShipByName(name, locale);
            if (ship.isPresent()) {
                Ship cruise = ship.get();
                cruise.setBonuses(shipDao.getAllBonusesByShip(cruise.getId()));
                cruise.setPorts(portDao.getAllPortsByShip(cruise.getId(), locale));
                for (Port port : cruise.getPorts()) {
                    port.setExcursions(excursionDao.getAllExcursionsByPort(port.getId(), locale));
                }
                return Optional.of(cruise);
            }
            return Optional.empty();
        }
    }

    public List<Ship> getAllCruisesByUser(int userId, String locale) {
        Connection connection = ConnectionPool.getConnection();
        try(ShipDao shipDao = daoFactory.createShipDao(connection);
            PortDao portDao = daoFactory.createPortDao(connection);
            ExcursionDao excursionDao = daoFactory.createExcursionDao(connection)) {
            List<Ship> ships = shipDao.getAllShipsByUser(userId, locale);
            for (Ship ship : ships) {
                ship.setPorts(portDao.getAllPortsByShip(ship.getId(), locale));
                for (Port port : ship.getPorts()) {
                    port.setExcursions(excursionDao.getAllExcursionsByPort(port.getId(), locale));
                }
                ship.setBonuses(shipDao.getAllBonusesByShip(ship.getId()));
            }
            return ships;
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
            if (freePlacesAvailable(ship.getNameEn())) {
                ship.setFreePlaces(ship.getFreePlaces() - 1);
                shipDao.update(ship);
                connection.commit();
                return true;
            } else {
                connection.rollback();
            }
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }

    public Optional<Ship> getShipByName(String name, String locale) {
        Connection connection = ConnectionPool.getConnection();
        try(ShipDao shipDao = daoFactory.createShipDao(connection)) {
            return shipDao.getShipByName(name, locale);
        }
    }

    public List<Ship> getAllShipsPerPage(int pageNumber, String locale) {
        Connection connection = ConnectionPool.getConnection();
        try(ShipDao shipDao = daoFactory.createShipDao(connection)) {
            return shipDao.getAllShipsPerPage(pageNumber, locale);
        }
    }

    public List<Bonus> getAllBonusesByShip(int shipId) {
        Connection connection = ConnectionPool.getConnection();
        try(ShipDao shipDao = daoFactory.createShipDao(connection)) {
            return shipDao.getAllBonusesByShip(shipId);
        }
    }

    public void addBonusToShip(int shipId, String bonus) {
        Connection connection = ConnectionPool.getConnection();
        try(ShipDao shipDao = daoFactory.createShipDao(connection)) {
            shipDao.addBonusToShip(shipId, bonus);
        }
    }

    public void removeBonusFromShip(int shipId, String bonus) {
        Connection connection = ConnectionPool.getConnection();
        try(ShipDao shipDao = daoFactory.createShipDao(connection)) {
            shipDao.deleteBonusFromShip(shipId, bonus);
        }
    }


    public int getNumberOfPages() {
        Connection connection = ConnectionPool.getConnection();
        try(ShipDao shipDao = daoFactory.createShipDao(connection)) {
            int numberOfShips = shipDao.countAllShips();
            return (int) Math.ceil(numberOfShips / 4.0);
        }
    }

    public boolean freePlacesAvailable(String name) {
        Connection connection = ConnectionPool.getConnection();
        try(ShipDao shipDao = daoFactory.createShipDao(connection)) {
            int freePlaces = shipDao.getFreePlacesAmount(name);
            return freePlaces > 0;
        }
    }
}
