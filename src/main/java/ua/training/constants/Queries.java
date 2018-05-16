package ua.training.constants;

/**
 * Максим
 * 26.04.2018
 */
public interface Queries {
    String EXCURSION_CREATE = "INSERT INTO excursion (name_en, name_ua, price, port_id) VALUES (?, ?, ?, ?)";
    String EXCURSION_FIND_BY_ID = "SELECT * FROM excursion WHERE id = ?";
    String EXCURSION_FIND_BY_NAME = "SELECT * FROM excursion WHERE name_en = ?";
    String EXCURSION_FIND_ALL = "SELECT * FROM excursion";
    String EXCURSION_UPDATE = "UPDATE excursion SET name_en = ?, name_ua = ?, price = ? WHERE excursion_id = ?";
    String EXCURSION_DELETE = "DELETE FROM excursion WHERE excursion_id = ?";
    String EXCURSION_ADD_TO_USER = "INSERT INTO excursion_has_user (excursion_id, user_id) VALUES (?, ?)";
    String EXCURSION_FIND_ALL_BY_PORT = "SELECT * FROM excursion WHERE port_id = ?";
    String EXCURSION_FIND_ALL_BY_USER = "SELECT * FROM excursion\n" +
                                        " INNER JOIN excursion_has_user\n" +
                                        " USING (excursion_id)\n" +
                                        "WHERE excursion_has_user.user_id = ?";

    String PORT_CREATE = "INSERT INTO port (name_en, name_ua, country_en, country_ua) VALUES (?, ?, ?, ?)";
    String PORT_FIND_BY_ID = "SELECT * FROM port WHERE port_id = ?";
    String PORT_FIND_ALL = "SELECT * FROM port";
    String PORT_UPDATE = "UPDATE port SET name_en = ?, name_ua = ?, country_en = ?, country_ua = ? WHERE port_id = ?";
    String PORT_DELETE = "DELETE FROM port WHERE port_id = ?";
    String PORT_ADD_TO_SHIP = "INSERT INTO port_has_ship (port_id, ship_id, number) VALUES (?, ?, ?)";
    String PORT_FIND_ALL_BY_SHIP = "SELECT * FROM port\n" +
                                    " INNER JOIN port_has_ship\n" +
                                    " USING (port_id)\n" +
                                    "WHERE port_has_ship.ship_id = ?\n" +
                                    "ORDER BY port_has_ship.order_number";

    String SHIP_CREATE = "INSERT INTO ship (ship_name_en, ship_name_ua, price, ports_visited, departure, arrival, " +
                            "cruise_duration, ship_class, passenger_capacity, free_places, staff) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    String SHIP_FIND_BY_ID = "SELECT * FROM ship WHERE ship_id = ?";
    String SHIP_FIND_BY_NAME = "SELECT * FROM ship WHERE name_en = ?";
    String SHIP_GET_FREE_PLACES = "SELECT free_places FROM ship WHERE name_en = ?";
    String SHIP_FIND_ALL = "SELECT * FROM ship";
    String SHIP_UPDATE = "UPDATE ship SET name_en = ?, name_ua = ?, price = ?, ports_visited = ?, departure = ?, arrival = ?, " +
                            "cruise_duration = ?, ship_class = ?, passenger_capacity = ?, free_places = ?, staff = ? WHERE ship_id = ?";
    String SHIP_DELETE = "DELETE FROM ship WHERE ship_id = ?";
    String SHIP_COUNT = "SELECT count(*) FROM ship";
    String SHIP_FIND_ALL_PER_PAGE = "SELECT * FROM ship LIMIT 4 OFFSET ?";
    String SHIP_GET_ALL_BONUSES_BY_SHIP = "SELECT name FROM bonus WHERE ship_id = ?";
    String SHIP_ADD_BONUS_TO_SHIP = "INSERT INTO bonus (ship_id, name) VALUES (?, ?)";
    String SHIP_DELETE_BONUS_FROM_SHIP = "DELETE FROM bonus WHERE ship_id = ? AND name = ?";
    String SHIP_ADD_TO_USER = "INSERT INTO user_has_ship (user_id, ship_id) VALUES (?, ?)";
    String SHIP_FIND_ALL_BY_USER = "SELECT * FROM ship\n" +
                                    " INNER JOIN user_has_ship\n" +
                                    " USING (ship_id)\n" +
                                    "WHERE user_has_ship.user_id = ?";

    String USER_CREATE = "INSERT INTO user (login, password, role) VALUES (?, ?, ?)";
    String USER_FIND_BY_ID = "SELECT * FROM user WHERE id = ?";
    String USER_FIND_BY_LOGIN = "SELECT * FROM user WHERE login = ?";
    String USER_FIND_ALL = "SELECT * FROM user";
    String USER_UPDATE = "UPDATE user SET login = ?, password = ?, role = ? WHERE id = ?";
    String USER_DELETE = "DELETE FROM user WHERE user_id = ?";
}
