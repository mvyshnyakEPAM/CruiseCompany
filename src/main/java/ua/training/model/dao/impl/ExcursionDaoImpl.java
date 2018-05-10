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
            ps.setString(1, entity.getName());
            ps.setBigDecimal(2, entity.getPrice());
            ps.setInt(3, entity.getPort().getId());
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
    public List<Excursion> getAllExcursionsByUser(int userId) {
        try(PreparedStatement ps = connection.prepareStatement(Queries.EXCURSION_FIND_ALL_BY_USER)) {
            ps.setInt(1, userId);
            ResultSet resultSet = ps.executeQuery();
            List<Excursion> excursions = new ArrayList<>();
            while (resultSet.next()) {
                Excursion excursion = extractEntityFromResultSet(resultSet);
                excursions.add(excursion);
            }
            return excursions;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Excursion> findById(int id) {
        try(PreparedStatement ps = connection.prepareStatement(Queries.EXCURSION_FIND_BY_ID)) {
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            Excursion excursion = null;
            while (resultSet.next()) {
                excursion = extractEntityFromResultSet(resultSet);
            }
            return Optional.ofNullable(excursion);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Excursion> findAll() {
        try(Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(Queries.EXCURSION_FIND_ALL);
            List<Excursion> excursions = new ArrayList<>();
            while (resultSet.next()) {
                Excursion excursion = extractEntityFromResultSet(resultSet);
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
            ps.setString(1, entity.getName());
            ps.setBigDecimal(2, entity.getPrice());
            ps.setInt(3, entity.getId());
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

    private Excursion extractEntityFromResultSet(ResultSet resultSet) throws SQLException {
        return new Excursion.ExcursionBuilder()
                .setId(resultSet.getInt(TableColumns.ID))
                .setName(resultSet.getString(TableColumns.EXCURSION_NAME))
                .setPrice(resultSet.getBigDecimal(TableColumns.EXCURSION_PRICE))
                .build();
    }
}
