package ua.training.model.entities.enums;

/**
 * Максим
 * 12.05.2018
 */
public enum  ShipClass {
    STANDARD("ship_class_standard"),
    PREMIUM("ship_class_premium"),
    LUX("ship_class_lux");

    private String key;

    ShipClass(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
