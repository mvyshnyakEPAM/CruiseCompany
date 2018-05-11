package ua.training.constants;

/**
 * Максим
 * 26.04.2018
 */
public interface Queries {
    String EXCURSION_CREATE = "INSERT INTO excursions (name, price, port_id) VALUES (?, ?, ?)";
    String EXCURSION_FIND_BY_ID = "SELECT id, name, price FROM excursions WHERE id = ?";
    String EXCURSION_FIND_ALL = "SELECT id, name, price FROM excursions";
    String EXCURSION_UPDATE = "UPDATE excursions SET name = ?, price = ? WHERE id = ?";
    String EXCURSION_DELETE = "DELETE FROM excursions WHERE id = ?";
    String EXCURSION_ADD_TO_USER = "INSERT INTO excursions_has_users (excursions_id, users_id) VALUES (?, ?)";
    String EXCURSION_FIND_ALL_BY_USER = "SELECT * FROM excursions\n" +
                                        " INNER JOIN excursions_has_users\n" +
                                        " ON excursions.id = excursions_has_users.excursions_id\n" +
                                        "WHERE excursions_has_users.users_id = ?";

    String PORT_CREATE = "INSERT INTO port (name, country) VALUES (?, ?)";
    String PORT_FIND_BY_ID = "SELECT * FROM port WHERE port_id = ?";
    String PORT_FIND_ALL = "SELECT * FROM port";
    String PORT_UPDATE = "UPDATE port SET name = ?, country = ? WHERE port_id = ?";
    String PORT_DELETE = "DELETE FROM port WHERE port_id = ?";
    String PORT_ADD_TO_SHIP = "INSERT INTO port_has_ship (port_id, ship_id, number) VALUES (?, ?, ?)";
    String PORT_FIND_ALL_BY_SHIP = "SELECT * FROM port\n" +
                                    " INNER JOIN port_has_ship\n" +
                                    " ON port.port_id = port_has_ship.port_id\n" +
                                    "WHERE port_has_ship.ship_id = ?\n" +
                                    "ORDER BY port_has_ship.order_number";

    String SHIP_CREATE = "INSERT INTO ship (ship_name, ports_visited, departure, arrival, " +
                            "cruise_duration, ship_class, passenger_capacity, staff) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    String SHIP_FIND_BY_ID = "SELECT * FROM ship WHERE ship_id = ?";
    String SHIP_FIND_ALL = "SELECT * FROM ship";
    String SHIP_UPDATE = "UPDATE ship SET ship_name = ?, ports_visited = ?, departure = ?, arrival = ?, " +
                            "cruise_duration = ?, ship_class = ?, passenger_capacity = ?, staff = ? WHERE ship_id = ?";
    String SHIP_DELETE = "DELETE FROM ship WHERE ship_id = ?";
    String SHIP_COUNT = "SELECT count(*) FROM ship";
    String SHIP_FIND_ALL_PER_PAGE = "SELECT * FROM ship LIMIT 4 OFFSET ?";
    String SHIP_ADD_BONUS_TO_SHIP = "INSERT INTO bonus (ship_id, name) VALUES (?, ?)";
    String SHIP_DELETE_BONUS_FROM_SHIP = "DELETE FROM bonus WHERE ship_id = ? AND name = ?";
    String SHIP_ADD_TO_USER = "INSERT INTO user_has_ship (user_id, ship_id) VALUES (?, ?)";
    String SHIP_FIND_ALL_BY_USER = "SELECT * FROM ship\n" +
                                    " INNER JOIN user_has_ship\n" +
                                    " ON ships.id = user_has_ship.ship_id\n" +
                                    "WHERE user_has_ship.user_id = ?";

    String USER_CREATE = "INSERT INTO user (login, password, role) VALUES (?, ?, ?)";
    String USER_FIND_BY_ID = "SELECT * FROM user WHERE id = ?";
    String USER_FIND_BY_LOGIN = "SELECT * FROM user WHERE login = ?";
    String USER_FIND_ALL = "SELECT * FROM user";
    String USER_UPDATE = "UPDATE user SET login = ?, password = ?, role = ? WHERE id = ?";
    String USER_DELETE = "DELETE FROM user WHERE user_id = ?";
}
