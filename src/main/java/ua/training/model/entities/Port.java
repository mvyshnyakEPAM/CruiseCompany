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

        public PortBuilder setNameEn(String nameEn) {
            this.nameEn = nameEn;
            return this;
        }

        public PortBuilder setNameUa(String nameUa) {
            this.nameUa = nameUa;
            return this;
        }

        public PortBuilder setCountry(String country) {
            this.country = country;
            return this;
        }

        public PortBuilder setCountryEn(String countryEn) {
            this.countryEn = countryEn;
            return this;
        }

        public PortBuilder setCountryUa(String countryUa) {
            this.countryUa = countryUa;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Port port = (Port) o;

        if (name != null ? !name.equals(port.name) : port.name != null) return false;
        if (nameEn != null ? !nameEn.equals(port.nameEn) : port.nameEn != null) return false;
        if (nameUa != null ? !nameUa.equals(port.nameUa) : port.nameUa != null) return false;
        if (country != null ? !country.equals(port.country) : port.country != null) return false;
        if (countryEn != null ? !countryEn.equals(port.countryEn) : port.countryEn != null) return false;
        if (countryUa != null ? !countryUa.equals(port.countryUa) : port.countryUa != null) return false;
        if (ships != null ? !ships.equals(port.ships) : port.ships != null) return false;
        return excursions != null ? excursions.equals(port.excursions) : port.excursions == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (nameEn != null ? nameEn.hashCode() : 0);
        result = 31 * result + (nameUa != null ? nameUa.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (countryEn != null ? countryEn.hashCode() : 0);
        result = 31 * result + (countryUa != null ? countryUa.hashCode() : 0);
        result = 31 * result + (ships != null ? ships.hashCode() : 0);
        result = 31 * result + (excursions != null ? excursions.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return name;
    }
}
