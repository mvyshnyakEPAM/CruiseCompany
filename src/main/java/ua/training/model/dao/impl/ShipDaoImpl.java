package ua.training.model.dao.impl;

import ua.training.constants.TableColumns;
import ua.training.model.dao.ShipDao;
import ua.training.constants.Queries;
import ua.training.model.entities.Ship;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public void create(Ship entity) {
        try(PreparedStatement ps = connection.prepareStatement(Queries.SHIP_CREATE)) {
            ps.setString(1, entity.getCruiseName());
            ps.setInt(2, entity.getPortsVisited());
            ps.setString(3, entity.getDeparture().format(DateTimeFormatter.ISO_DATE));
            ps.setString(4, entity.getArrival().format(DateTimeFormatter.ISO_DATE));
            ps.setInt(5, entity.getCruiseDuration());
            ps.setString(6, entity.getShipClass().name());
            ps.setInt(7, entity.getPassengerCapacity());
            ps.setInt(8, entity.getStaff());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Ship> findById(int id) {
        try(PreparedStatement ps = connection.prepareStatement(Queries.SHIP_FIND_BY_ID)) {
            ps.setInt(1, id);
            Ship ship = extractEntityFromResultSet(ps.executeQuery());
            return Optional.ofNullable(ship);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<List<Ship>> findAll() {
        try(Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(Queries.SHIP_FIND_ALL);
            List<Ship> ships = new ArrayList<>();
            while (resultSet.next()) {
                Ship ship = extractEntityFromResultSet(resultSet);
                ships.add(ship);
            }
            return Optional.of(ships);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Ship entity) {
        try(PreparedStatement ps = connection.prepareStatement(Queries.SHIP_UPDATE)) {
            ps.setString(1, entity.getCruiseName());
            ps.setInt(2, entity.getPortsVisited());
            ps.setString(3, entity.getDeparture().format(DateTimeFormatter.ISO_DATE));
            ps.setString(4, entity.getArrival().format(DateTimeFormatter.ISO_DATE));
            ps.setInt(5, entity.getCruiseDuration());
            ps.setString(6, entity.getShipClass().name());
            ps.setInt(7, entity.getPassengerCapacity());
            ps.setInt(8, entity.getStaff());
            ps.setInt(9, entity.getId());
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

    private Ship extractEntityFromResultSet(ResultSet resultSet) throws SQLException {
        return new Ship.ShipBuilder()
                .setId(resultSet.getInt(TableColumns.ID))
                .setCruiseName(resultSet.getString(TableColumns.SHIP_CRUISE_NAME))
                .setPortsVisited(resultSet.getInt(TableColumns.SHIP_PORTS_VISITED))
                .setDeparture(LocalDate.parse(resultSet.getString(TableColumns.SHIP_DEPARTURE),
                        DateTimeFormatter.ISO_DATE))
                .setArrival(LocalDate.parse(resultSet.getString(TableColumns.SHIP_ARRIVAL),
                        DateTimeFormatter.ISO_DATE))
                .setCruiseDuration(resultSet.getInt(TableColumns.SHIP_DURATION))
                .setShipClass(Ship.Class.valueOf(resultSet.getString(TableColumns.SHIP_CLASS)))
                .setPassengerCapacity(resultSet.getInt(TableColumns.SHIP_PASSENGER_CAPACITY))
                .setStaff(resultSet.getInt(TableColumns.SHIP_STAFF))
                .build();
    }
}
