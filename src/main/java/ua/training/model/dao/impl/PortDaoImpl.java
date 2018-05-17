package ua.training.model.dao.impl;

import ua.training.constants.Queries;
import ua.training.constants.TableColumns;
import ua.training.model.dao.PortDao;
import ua.training.model.entities.Port;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Максим
 * 06.05.2018
 */
public class PortDaoImpl implements PortDao {
    private Connection connection;

    public PortDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Port entity) {
        try(PreparedStatement ps = connection.prepareStatement(Queries.PORT_CREATE)) {
            ps.setString(1, entity.getNameEn());
            ps.setString(2, entity.getNameUa());
            ps.setString(3, entity.getCountryEn());
            ps.setString(4, entity.getCountryUa());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addPortToShip(int portId, int shipId, int number) {
        try(PreparedStatement ps = connection.prepareStatement(Queries.PORT_ADD_TO_SHIP)) {
            ps.setInt(1, portId);
            ps.setInt(2, shipId);
            ps.setInt(3, number);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Port> getAllPortsByShip(int shipId, String locale) {
        try(PreparedStatement ps = connection.prepareStatement(Queries.PORT_FIND_ALL_BY_SHIP)) {
            ps.setInt(1, shipId);
            ResultSet resultSet = ps.executeQuery();
            List<Port> ports = new ArrayList<>();
            while (resultSet.next()) {
                Port port = extractEntityFromResultSet(resultSet, locale);
                ports.add(port);
            }
            return ports;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Port> findById(int id, String locale) {
        try(PreparedStatement ps = connection.prepareStatement(Queries.PORT_FIND_BY_ID)) {
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            Port port = null;
            while (resultSet.next()) {
                port = extractEntityFromResultSet(resultSet, locale);
            }
            return Optional.ofNullable(port);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Port> findAll(String locale) {
        try(Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(Queries.PORT_FIND_ALL);
            List<Port> ports = new ArrayList<>();
            while (resultSet.next()) {
                Port port = extractEntityFromResultSet(resultSet, locale);
                ports.add(port);
            }
            return ports;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Port entity) {
        try(PreparedStatement ps = connection.prepareStatement(Queries.PORT_UPDATE)) {
            ps.setString(1, entity.getNameEn());
            ps.setString(2, entity.getNameUa());
            ps.setString(3, entity.getCountryEn());
            ps.setString(4, entity.getCountryUa());
            ps.setInt(5, entity.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        try(PreparedStatement ps = connection.prepareStatement(Queries.PORT_DELETE)) {
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

    public static Port extractEntityFromResultSet(ResultSet resultSet, String locale) throws SQLException {
        return new Port.PortBuilder()
                .setId(resultSet.getInt(TableColumns.PORT_ID))
                .setName(resultSet.getString(TableColumns.PORT_NAME + "_" + locale))
                .setNameEn(resultSet.getString(TableColumns.PORT_NAME_EN))
                .setNameUa(resultSet.getString(TableColumns.PORT_NAME_UA))
                .setCountry(resultSet.getString(TableColumns.PORT_COUNTRY + "_" + locale))
                .build();
    }
}
