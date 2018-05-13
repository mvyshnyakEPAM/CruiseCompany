package ua.training.model.entities.enums;

/**
 * Максим
 * 12.05.2018
 */
public enum Bonus {
    POOL("bonus_pool"),
    CINEMA("bonus_cinema"),
    GYM("bonus_gym"),
    FITNESS_CENTER("bonus_fitness_center"),
    BEAUTY_SALON("bonus_beauty_salon"),
    INTERNET_ACCESS("bonus_internet_access"),
    SAUNA("bonus_sauna"),
    REFRIGERATOR("bonus_refrigerator"),
    TRADITIONAL_FOOD("bonus_traditional_food"),
    ALL_INCLUDE_FOOD("bonus_all_include_food"),
    BALCONY_FOOD("bonus_balcony_food"),
    ROOM_SERVICE("bonus_room_service"),
    BEVERAGES("bonus_beverages"),
    LAUNDRY("bonus_laundry"),
    CHILDREN_CLUB("bonus_children_club"),
    DANCING_LESSONS("bonus_dancing_lessons"),
    LIBRARY("bonus_library"),
    BAGGAGE_DELIVERY("bonus_baggage_delivery"),
    FIVE_STAR_SERVICE("bonus_five_star_service"),
    COOKING_MASTER_CLASS("bonus_cooking_master_class");

    private String key;

    Bonus(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
