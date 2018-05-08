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

    String PORT_CREATE = "INSERT INTO ports (name, country) VALUES (?, ?)";
    String PORT_FIND_BY_ID = "SELECT * FROM ports WHERE id = ?";
    String PORT_FIND_ALL = "SELECT * FROM ports";
    String PORT_UPDATE = "UPDATE ports SET name = ?, country = ? WHERE id = ?";
    String PORT_DELETE = "DELETE FROM ports WHERE id = ?";

    String SHIP_CREATE = "INSERT INTO ships (cruise_name, ports_visited, departure, arrival, " +
                            "duration, ship_class, passenger_capacity, staff) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    String SHIP_FIND_BY_ID = "SELECT * FROM ships WHERE id = ?";
    String SHIP_FIND_ALL = "SELECT * FROM ships";
    String SHIP_UPDATE = "UPDATE ships SET cruise_name = ?, ports_visited = ?, departure = ?, arrival = ?, " +
                            "duration = ?, ship_class = ?, passenger_capacity = ?, staff = ? WHERE id = ?";
    String SHIP_DELETE = "DELETE FROM ships WHERE id = ?";

    String USER_CREATE = "INSERT INTO users (login, password, role) VALUES (?, ?, ?)";
    String USER_FIND_BY_ID = "SELECT * FROM users WHERE id = ?";
    String USER_FIND_BY_LOGIN = "SELECT * FROM users WHERE login = ?";
    String USER_FIND_ALL = "SELECT * FROM users";
    String USER_UPDATE = "UPDATE users SET login = ?, password = ?, role = ? WHERE id = ?";
    String USER_DELETE = "DELETE FROM users WHERE id = ?";
}
