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
    String SHIP_NAME_EN = "ship_name_en";
    String SHIP_NAME_UA = "ship_name_ua";
    String SHIP_PRICE = "price";
    String SHIP_PORTS_VISITED = "ports_visited";
    String SHIP_DEPARTURE = "departure";
    String SHIP_ARRIVAL = "arrival";
    String SHIP_DURATION = "cruise_duration";
    String SHIP_CLASS = "ship_class";
    String SHIP_PASSENGER_CAPACITY = "passenger_capacity";
    String SHIP_FREE_PLACES = "free_places";
    String SHIP_STAFF = "staff";

    String PORT_ID = "port_id";
    String PORT_NAME = "port_name";
    String PORT_NAME_EN = "port_name_en";
    String PORT_NAME_UA = "port_name_ua";
    String PORT_COUNTRY = "country";

    String EXCURSION_ID = "excursion_id";
    String EXCURSION_NAME = "excursion_name";
    String EXCURSION_NAME_EN = "excursion_name_en";
    String EXCURSION_NAME_UA = "excursion_name_ua";
    String EXCURSION_PRICE = "price";
    String EXCURSION_PORT_ID = "port_id";
}
