package ua.training.model.entities;

import java.util.List;

/**
 * Максим
 * 06.05.2018
 */
public class Port {
    private int id;
    private String name;
    private String country;

    private List<Ship> ships;
    private List<Excursion> excursions;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Ship> getShips() {
        return ships;
    }

    public void setShips(List<Ship> ships) {
        this.ships = ships;
    }

    public List<Excursion> getExcursions() {
        return excursions;
    }

    public void setExcursions(List<Excursion> excursions) {
        this.excursions = excursions;
    }

    public static final class PortBuilder {
        private int id;
        private String name;
        private String country;

        private List<Ship> ships;
        private List<Excursion> excursions;

        public PortBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public PortBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public PortBuilder setCountry(String country) {
            this.country = country;
            return this;
        }

        public PortBuilder setShips(List<Ship> ships) {
            this.ships = ships;
            return this;
        }

        public PortBuilder setExcursions(List<Excursion> excursions) {
            this.excursions = excursions;
            return this;
        }

        public Port build() {
            Port port = new Port();
            port.setName(name);
            port.setCountry(country);
            port.setShips(ships);
            port.setExcursions(excursions);
            return port;
        }
    }
}
