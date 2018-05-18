package ua.training.model.dao;

import ua.training.model.entities.Excursion;

import java.util.List;
import java.util.Optional;

/**
 * Максим
 * 06.05.2018
 */
public interface ExcursionDao extends GenericDao<Excursion> {
    void addExcursionToUser(int excursionId, int userId, String shipName);
    Optional<Excursion> getExcursionByName(String name, String locale);
    List<Excursion> getAllExcursionsByUserAndCruise(int userId, String shipName, String locale);
    List<Excursion> getAllExcursionsByPort(int portId, String locale);
}
