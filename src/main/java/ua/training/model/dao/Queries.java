package ua.training.model.dao;

/**
 * Максим
 * 26.04.2018
 */
public interface Queries {
    String GET_ALL_CRUISES = "SELECT * FROM ships " +
                            "LEFT JOIN port_has_cruises USING (ships_id)" +
                            "LEFT JOIN ports USING (port_id)";

    String GET_CRUISE = "SELECT * FROM ships " +
                        "INNER JOIN ship_bonuses USING (ship_id)" +
                        "INNER JOIN port_has_cruises USING (ships_id)" +
                        "INNER JOIN ports USING (port_id)";

    String GET_BONUSES_BY_SHIP = "SELECT * FROM bonuses WHERE ship_id = ?";
    String GET_EXCURSIONS_BY_PORT = "SELECT * FROM excursions WHERE port_id = ?";
}
