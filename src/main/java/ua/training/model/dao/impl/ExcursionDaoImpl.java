package ua.training.model.dao.impl;

import ua.training.constants.Queries;
import ua.training.constants.TableColumns;
import ua.training.model.dao.ExcursionDao;
import ua.training.model.entities.Excursion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Максим
 * 06.05.2018
 */
public class ExcursionDaoImpl implements ExcursionDao {
    private Connection connection;

    public ExcursionDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Excursion entity) {
        try(PreparedStatement ps = connection.prepareStatement(Queries.EXCURSION_CREATE)) {
            ps.setString(1, entity.getNameEn());
            ps.setString(2, entity.getNameUa());
            ps.setInt(3, entity.getPrice());
            ps.setInt(4, entity.getPort().getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addExcursionToUser(int excursionId, int userID) {
        try(PreparedStatement ps = connection.prepareStatement(Queries.EXCURSION_ADD_TO_USER)) {
            ps.setInt(1, excursionId);
            ps.setInt(2, userID);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Excursion> getExcursionByName(String name, String locale) {
        try(PreparedStatement ps = connection.prepareStatement(Queries.EXCURSION_FIND_BY_NAME)) {
            ps.setString(1, name);
            ResultSet resultSet = ps.executeQuery();
            Excursion excursion = null;
            while (resultSet.next()) {
                excursion = extractEntityFromResultSet(resultSet, locale);
            }
            return Optional.ofNullable(excursion);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Excursion> getAllExcursionsByPort(int portId, String locale) {
        try(PreparedStatement ps = connection.prepareStatement(Queries.EXCURSION_FIND_ALL_BY_PORT)) {
            ps.setInt(1, portId);
            ResultSet resultSet = ps.executeQuery();
            List<Excursion> excursions = new ArrayList<>();
            while (resultSet.next()) {
                Excursion excursion = extractEntityFromResultSet(resultSet, locale);
                excursions.add(excursion);
            }
            return excursions;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Excursion> getAllExcursionsByUser(int userId, String locale) {
        try(PreparedStatement ps = connection.prepareStatement(Queries.EXCURSION_FIND_ALL_BY_USER)) {
            ps.setInt(1, userId);
            ResultSet resultSet = ps.executeQuery();
            List<Excursion> excursions = new ArrayList<>();
            while (resultSet.next()) {
                Excursion excursion = extractEntityFromResultSet(resultSet, locale);
                excursions.add(excursion);
            }
            return excursions;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Excursion> findById(int id, String locale) {
        try(PreparedStatement ps = connection.prepareStatement(Queries.EXCURSION_FIND_BY_ID)) {
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            Excursion excursion = null;
            while (resultSet.next()) {
                excursion = extractEntityFromResultSet(resultSet, locale);
            }
            return Optional.ofNullable(excursion);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Excursion> findAll(String locale) {
        try(Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(Queries.EXCURSION_FIND_ALL);
            List<Excursion> excursions = new ArrayList<>();
            while (resultSet.next()) {
                Excursion excursion = extractEntityFromResultSet(resultSet, locale);
                excursions.add(excursion);
            }
            return excursions;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Excursion entity) {
        try(PreparedStatement ps = connection.prepareStatement(Queries.EXCURSION_UPDATE)) {
            ps.setString(1, entity.getNameEn());
            ps.setString(2, entity.getNameUa());
            ps.setInt(3, entity.getPrice());
            ps.setInt(4, entity.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        try(PreparedStatement ps = connection.prepareStatement(Queries.EXCURSION_DELETE)) {
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

    private Excursion extractEntityFromResultSet(ResultSet resultSet, String locale) throws SQLException {
        return new Excursion.ExcursionBuilder()
                .setId(resultSet.getInt(TableColumns.EXCURSION_ID))
                .setName(resultSet.getString(TableColumns.EXCURSION_NAME + "_" + locale))
                .setNameEn(resultSet.getString(TableColumns.EXCURSION_NAME_EN))
                .setNameUa(resultSet.getString(TableColumns.EXCURSION_NAME_UA))
                .setPrice(resultSet.getInt(TableColumns.EXCURSION_PRICE))
                .build();
    }
}
