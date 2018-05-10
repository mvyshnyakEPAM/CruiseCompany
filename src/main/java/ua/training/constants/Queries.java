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

    String PORT_CREATE = "INSERT INTO ports (name, country) VALUES (?, ?)";
    String PORT_FIND_BY_ID = "SELECT * FROM ports WHERE id = ?";
    String PORT_FIND_ALL = "SELECT * FROM ports";
    String PORT_UPDATE = "UPDATE ports SET name = ?, country = ? WHERE id = ?";
    String PORT_DELETE = "DELETE FROM ports WHERE id = ?";
    String PORT_ADD_TO_SHIP = "INSERT INTO port_has_cruises (port_id, cruises_id, number) VALUES (?, ?, ?)";
    String PORT_FIND_ALL_BY_SHIP = "SELECT * FROM ports\n" +
                                    " INNER JOIN port_has_cruises\n" +
                                    " ON ports.id = port_has_cruises.port_id\n" +
                                    "WHERE port_has_cruises.cruises_id = ?\n" +
                                    "ORDER BY port_has_cruises.number";

    String SHIP_CREATE = "INSERT INTO ships (cruise_name, ports_visited, departure, arrival, " +
                            "duration, ship_class, passenger_capacity, staff) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    String SHIP_FIND_BY_ID = "SELECT * FROM ships WHERE id = ?";
    String SHIP_FIND_ALL = "SELECT * FROM ships";
    String SHIP_UPDATE = "UPDATE ships SET cruise_name = ?, ports_visited = ?, departure = ?, arrival = ?, " +
                            "duration = ?, ship_class = ?, passenger_capacity = ?, staff = ? WHERE id = ?";
    String SHIP_DELETE = "DELETE FROM ships WHERE id = ?";
    String SHIP_ADD_BONUS_TO_SHIP = "INSERT INTO ship_bonuses (ship_id, bonus) VALUES (?, ?)";
    String SHIP_DELETE_BONUS_FROM_SHIP = "DELETE FROM ship_bonuses WHERE ship_id = ? AND bonus = ?";
    String SHIP_ADD_TO_USER = "INSERT INTO users_has_ships (users_id, ships_id) VALUES (?, ?)";
    String SHIP_FIND_ALL_BY_USER = "SELECT * FROM ships\n" +
                                    " INNER JOIN users_has_ships\n" +
                                    " ON ships.id = users_has_ships.ships_id\n" +
                                    "WHERE users_has_ships.users_id = ?";

    String USER_CREATE = "INSERT INTO users (login, password, role) VALUES (?, ?, ?)";
    String USER_FIND_BY_ID = "SELECT * FROM users WHERE id = ?";
    String USER_FIND_BY_LOGIN = "SELECT * FROM users WHERE login = ?";
    String USER_FIND_ALL = "SELECT * FROM users";
    String USER_UPDATE = "UPDATE users SET login = ?, password = ?, role = ? WHERE id = ?";
    String USER_DELETE = "DELETE FROM users WHERE id = ?";
}
