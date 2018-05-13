package ua.training.model.entities;

import java.util.List;

/**
 * Максим
 * 06.05.2018
 */
public class Port {
    private int id;
    private String name;
    private String nameEn;
    private String nameUa;
    private String country;
    private String countryEn;
    private String countryUa;

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

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getNameUa() {
        return nameUa;
    }

    public void setNameUa(String nameUa) {
        this.nameUa = nameUa;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryEn() {
        return countryEn;
    }

    public void setCountryEn(String countryEn) {
        this.countryEn = countryEn;
    }

    public String getCountryUa() {
        return countryUa;
    }

    public void setCountryUa(String countryUa) {
        this.countryUa = countryUa;
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
        private String nameEn;
        private String nameUa;
        private String country;
        private String countryEn;
        private String countryUa;

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

        public void setNameEn(String nameEn) {
            this.nameEn = nameEn;
        }

        public void setNameUa(String nameUa) {
            this.nameUa = nameUa;
        }

        public PortBuilder setCountry(String country) {
            this.country = country;
            return this;
        }

        public void setCountryEn(String countryEn) {
            this.countryEn = countryEn;
        }

        public void setCountryUa(String countryUa) {
            this.countryUa = countryUa;
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
            port.setId(id);
            port.setName(name);
            port.setNameEn(nameEn);
            port.setNameUa(nameUa);
            port.setCountry(country);
            port.setCountryEn(countryEn);
            port.setCountryUa(countryUa);
            port.setShips(ships);
            port.setExcursions(excursions);
            return port;
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
