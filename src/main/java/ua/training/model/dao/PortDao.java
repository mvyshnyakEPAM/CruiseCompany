package ua.training.model.dao;

import ua.training.model.entities.Port;

import java.util.List;

/**
 * Максим
 * 06.05.2018
 */
public interface PortDao extends GenericDao<Port> {
    void addPortToShip(int portId, int shipId, int number);
    List<Port> getAllPortsByShip(int shipId);
}
