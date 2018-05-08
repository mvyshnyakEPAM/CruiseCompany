package ua.training.constants;

/**
 * Максим
 * 06.05.2018
 */
public interface TableColumns {
    String ID = "id";

    String USER_LOGIN = "login";
    String USER_PASSWORD = "password";
    String USER_ROLE = "role";

    String SHIP_CRUISE_NAME = "cruise_name";
    String SHIP_PORTS_VISITED = "ports_visited";
    String SHIP_DEPARTURE = "departure";
    String SHIP_ARRIVAL = "arrival";
    String SHIP_DURATION = "duration";
    String SHIP_CLASS = "ship_class";
    String SHIP_PASSENGER_CAPACITY = "passenger_capacity";
    String SHIP_STAFF = "staff";

    String PORT_NAME = "name";
    String PORT_COUNTRY = "country";

    String EXCURSION_NAME = "name";
    String EXCURSION_PRICE = "price";
    String EXCURSION_PORT_ID = "port_id";
}
