package ua.training.model.dao;

import ua.training.model.entities.Ship;

import java.util.List;

/**
 * Максим
 * 06.05.2018
 */
public interface ShipDao extends GenericDao<Ship> {
    int countAllShips();
    List<Ship> getAllShipsPerPage(int pageNumber);
    void addShipToUser(int shipId, int userId);
    List<Ship> getAllShipsByUser(int userId);
    void addBonusToShip(int shipId, String bonus);
    void deleteBonusFromShip(int shipId, String bonus);
}
