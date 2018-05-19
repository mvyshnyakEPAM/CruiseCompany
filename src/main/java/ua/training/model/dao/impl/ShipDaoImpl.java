package ua.training.model.dao.impl;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import ua.training.constants.Queries;
import ua.training.constants.TableColumns;
import ua.training.controller.exceptions.CruiseAlreadyBoughtException;
import ua.training.model.dao.ShipDao;
import ua.training.model.entities.Ship;
import ua.training.model.entities.enums.Bonus;
import ua.training.model.entities.enums.ShipClass;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.sql.Date.valueOf;

/**
 * Максим
 * 06.05.2018
 */
public class ShipDaoImpl implements ShipDao {
    private Connection connection;

    public ShipDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int countAllShips() {
        try(Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(Queries.SHIP_COUNT);
            int ships = 0;
            while (resultSet.next()) {
                ships = resultSet.getInt(1);
            }
            return ships;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getFreePlacesAmount(String shipName) {
        try(PreparedStatement ps = connection.prepareStatement(Queries.SHIP_GET_FREE_PLACES)) {
            ps.setString(1, shipName);
            ResultSet resultSet = ps.executeQuery();
            int freePlaces = 0;
            while (resultSet.next()) {
                freePlaces = resultSet.getInt(1);
            }
            return freePlaces;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void create(Ship entity) {
        try(PreparedStatement ps = connection.prepareStatement(Queries.SHIP_CREATE)) {
            ps.setString(1, entity.getNameEn());
            ps.setString(2, entity.getNameUa());
            ps.setInt(3, entity.getPrice());
            ps.setInt(4, entity.getPortsVisited());
            ps.setDate(5, valueOf(entity.getDeparture()));
            ps.setDate(6, valueOf(entity.getArrival()));
            ps.setInt(7, entity.getCruiseDuration());
            ps.setString(8, entity.getShipClass().name());
            ps.setInt(9, entity.getPassengerCapacity());
            ps.setInt(10, entity.getPassengerCapacity());
            ps.setInt(11, entity.getStaff());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addShipToUser(int shipId, int userId)
            throws CruiseAlreadyBoughtException {
        try(PreparedStatement ps = connection.prepareStatement(
                Queries.SHIP_ADD_TO_USER)) {
            ps.setInt(1, userId);
            ps.setInt(2, shipId);
            ps.executeUpdate();
        } catch (MySQLIntegrityConstraintViolationException e) {
            throw new CruiseAlreadyBoughtException(e.getMessage());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Ship> getAllShipsByUser(int userId, String locale) {
        try(PreparedStatement ps = connection.prepareStatement(
                Queries.SHIP_FIND_ALL_BY_USER)) {
            ps.setInt(1, userId);
            ResultSet resultSet = ps.executeQuery();
            List<Ship> ships = new ArrayList<>();
            while (resultSet.next()) {
                Ship ship = extractEntityFromResultSet(resultSet, locale);
                ships.add(ship);
            }
            return ships;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Bonus> getAllBonusesByShip(int shipId) {
        try(PreparedStatement ps = connection.prepareStatement(
                Queries.SHIP_GET_ALL_BONUSES_BY_SHIP)) {
            ps.setInt(1, shipId);
            ResultSet resultSet = ps.executeQuery();
            List<Bonus> bonuses = new ArrayList<>();
            while (resultSet.next()) {
                bonuses.add(Bonus.valueOf(resultSet.getString("name")));
            }
            return bonuses;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addBonusToShip(int shipId, String bonus) {
        try(PreparedStatement ps = connection.prepareStatement(
                Queries.SHIP_ADD_BONUS_TO_SHIP)) {
            ps.setInt(1, shipId);
            ps.setString(2, bonus);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteBonusFromShip(int shipId, String bonus) {
        try(PreparedStatement ps = connection.prepareStatement(
                Queries.SHIP_DELETE_BONUS_FROM_SHIP)) {
            ps.setInt(1, shipId);
            ps.setString(2, bonus);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Ship> findById(int id, String locale) {
        try(PreparedStatement ps = connection.prepareStatement(Queries.SHIP_FIND_BY_ID)) {
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            Ship ship = null;
            while (resultSet.next()) {
                ship = extractEntityFromResultSet(resultSet, locale);
            }
            return Optional.ofNullable(ship);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Ship> getShipByName(String shipName, String locale) {
        try(PreparedStatement ps = connection.prepareStatement(Queries.SHIP_FIND_BY_NAME)) {
            ps.setString(1, shipName);
            ResultSet resultSet = ps.executeQuery();
            Ship ship = null;
            while (resultSet.next()) {
                ship = extractEntityFromResultSet(resultSet, locale);
            }
            return Optional.ofNullable(ship);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Ship> getAllShipsPerPage(int pageNumber, String locale) {
        try(PreparedStatement ps = connection.prepareStatement(Queries.SHIP_FIND_ALL_PER_PAGE)) {
            ps.setInt(1, pageNumber * 4 - 4);
            ResultSet resultSet = ps.executeQuery();
            List<Ship> ships = new ArrayList<>();
            while (resultSet.next()) {
                Ship ship = extractEntityFromResultSet(resultSet, locale);
                ships.add(ship);
            }
            return ships;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Ship> findAll(String locale) {
        try(Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(Queries.SHIP_FIND_ALL);
            List<Ship> ships = new ArrayList<>();
            while (resultSet.next()) {
                Ship ship = extractEntityFromResultSet(resultSet, locale);
                ships.add(ship);
            }
            return ships;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Ship entity) {
        try(PreparedStatement ps = connection.prepareStatement(Queries.SHIP_UPDATE)) {
            ps.setString(1, entity.getNameEn());
            ps.setString(2, entity.getNameUa());
            ps.setInt(3, entity.getPrice());
            ps.setInt(4, entity.getPortsVisited());
            ps.setDate(5, valueOf(entity.getDeparture()));
            ps.setDate(6, valueOf(entity.getArrival()));
            ps.setInt(7, entity.getCruiseDuration());
            ps.setString(8, entity.getShipClass().name());
            ps.setInt(9, entity.getPassengerCapacity());
            ps.setInt(10, entity.getFreePlaces());
            ps.setInt(11, entity.getStaff());
            ps.setInt(12, entity.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        try(PreparedStatement ps = connection.prepareStatement(Queries.SHIP_DELETE)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Ship extractEntityFromResultSet(ResultSet resultSet, String locale) throws SQLException {
        return new Ship.ShipBuilder()
                .setId(resultSet.getInt(TableColumns.SHIP_ID))
                .setName(resultSet.getString(TableColumns.SHIP_NAME + "_" + locale))
                .setNameEn(resultSet.getString(TableColumns.SHIP_NAME_EN))
                .setNameUa(resultSet.getString(TableColumns.SHIP_NAME_UA))
                .setPrice(resultSet.getInt(TableColumns.SHIP_PRICE))
                .setPortsVisited(resultSet.getInt(TableColumns.SHIP_PORTS_VISITED))
                .setDeparture(resultSet.getDate(TableColumns.SHIP_DEPARTURE).toLocalDate())
                .setArrival(resultSet.getDate(TableColumns.SHIP_ARRIVAL).toLocalDate())
                .setCruiseDuration(resultSet.getInt(TableColumns.SHIP_DURATION))
                .setShipClass(ShipClass.valueOf(resultSet.getString(TableColumns.SHIP_CLASS)))
                .setPassengerCapacity(resultSet.getInt(TableColumns.SHIP_PASSENGER_CAPACITY))
                .setFreePlaces(resultSet.getInt(TableColumns.SHIP_FREE_PLACES))
                .setStaff(resultSet.getInt(TableColumns.SHIP_STAFF))
                .build();
    }
}
