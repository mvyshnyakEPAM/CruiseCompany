package ua.training.model.dao;

import ua.training.model.entities.Excursion;

import java.util.List;

/**
 * Максим
 * 06.05.2018
 */
public interface ExcursionDao extends GenericDao<Excursion> {
    void addExcursionToUser(int excursionId, int userId);
    List<Excursion> getAllExcursionsByUser(int userId);
}
