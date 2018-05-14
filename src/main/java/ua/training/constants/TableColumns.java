package ua.training.constants;

/**
 * Максим
 * 06.05.2018
 */
public interface TableColumns {
    String USER_ID = "user_id";
    String USER_LOGIN = "login";
    String USER_PASSWORD = "password";
    String USER_ROLE = "role";

    String SHIP_ID = "ship_id";
    String SHIP_NAME = "ship_name";
    String SHIP_PRICE = "price";
    String SHIP_PORTS_VISITED = "ports_visited";
    String SHIP_DEPARTURE = "departure";
    String SHIP_ARRIVAL = "arrival";
    String SHIP_DURATION = "cruise_duration";
    String SHIP_CLASS = "ship_class";
    String SHIP_PASSENGER_CAPACITY = "passenger_capacity";
    String SHIP_STAFF = "staff";

    String PORT_ID = "port_id";
    String PORT_NAME = "name";
    String PORT_COUNTRY = "country";

    String EXCURSION_ID = "excursion_id";
    String EXCURSION_NAME = "name";
    String EXCURSION_PRICE = "price";
    String EXCURSION_PORT_ID = "port_id";
}
