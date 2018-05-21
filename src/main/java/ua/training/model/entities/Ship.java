package ua.training.model.entities;

import ua.training.model.entities.enums.Bonus;
import ua.training.model.entities.enums.ShipClass;

import java.time.LocalDate;
import java.util.List;

/**
 * Максим
 * 27.04.2018
 */
public class Ship {
    private int id;
    private String name;
    private String nameEn;
    private String nameUa;
    private int price;
    private int portsVisited;
    private LocalDate departure;
    private LocalDate arrival;
    private int cruiseDuration;
    private ShipClass shipClass;
    private int passengerCapacity;
    private int freePlaces;
    private int staff;

    private List<Port> ports;
    private List<User> users;
    private List<Bonus> bonuses;

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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPortsVisited() {
        return portsVisited;
    }

    public void setPortsVisited(int portsVisited) {
        this.portsVisited = portsVisited;
    }

    public LocalDate getDeparture() {
        return departure;
    }

    public void setDeparture(LocalDate departure) {
        this.departure = departure;
    }

    public LocalDate getArrival() {
        return arrival;
    }

    public void setArrival(LocalDate arrival) {
        this.arrival = arrival;
    }

    public int getCruiseDuration() {
        return cruiseDuration;
    }

    public void setCruiseDuration(int cruiseDuration) {
        this.cruiseDuration = cruiseDuration;
    }

    public ShipClass getShipClass() {
        return shipClass;
    }

    public void setShipClass(ShipClass shipClass) {
        this.shipClass = shipClass;
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public void setPassengerCapacity(int passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
    }

    public int getFreePlaces() {
        return freePlaces;
    }

    public void setFreePlaces(int freePlaces) {
        this.freePlaces = freePlaces;
    }

    public int getStaff() {
        return staff;
    }

    public void setStaff(int staff) {
        this.staff = staff;
    }

    public List<Port> getPorts() {
        return ports;
    }

    public void setPorts(List<Port> ports) {
        this.ports = ports;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> tickets) {
        this.users = tickets;
    }

    public List<Bonus> getBonuses() {
        return bonuses;
    }

    public void setBonuses(List<Bonus> bonuses) {
        this.bonuses = bonuses;
    }

    public static final class ShipBuilder {
        private int id;
        private String name;
        private String nameEn;
        private String nameUa;
        private int price;
        private int portsVisited;
        private LocalDate departure;
        private LocalDate arrival;
        private int cruiseDuration;
        private ShipClass shipClass;
        private int passengerCapacity;
        private int freePlaces;
        private int staff;

        private List<Port> ports;
        private List<User> users;
        private List<Bonus> bonuses;

        public ShipBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public ShipBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public ShipBuilder setNameEn(String nameEn) {
            this.nameEn = nameEn;
            return this;
        }

        public ShipBuilder setNameUa(String nameUa) {
            this.nameUa = nameUa;
            return this;
        }

        public ShipBuilder setPrice(int price) {
            this.price = price;
            return this;
        }

        public ShipBuilder setPortsVisited(int portsVisited) {
            this.portsVisited = portsVisited;
            return this;
        }

        public ShipBuilder setDeparture(LocalDate departure) {
            this.departure = departure;
            return this;
        }

        public ShipBuilder setArrival(LocalDate arrival) {
            this.arrival = arrival;
            return this;
        }

        public ShipBuilder setCruiseDuration(int cruiseDuration) {
            this.cruiseDuration = cruiseDuration;
            return this;
        }

        public ShipBuilder setShipClass(ShipClass shipClass) {
            this.shipClass = shipClass;
            return this;
        }

        public ShipBuilder setPassengerCapacity(int passengerCapacity) {
            this.passengerCapacity = passengerCapacity;
            return this;
        }

        public ShipBuilder setFreePlaces(int freePlaces) {
            this.freePlaces = freePlaces;
            return this;
        }

        public ShipBuilder setStaff(int staff) {
            this.staff = staff;
            return this;
        }

        public ShipBuilder setPorts(List<Port> ports) {
            this.ports = ports;
            return this;
        }

        public ShipBuilder setUsers(List<User> users) {
            this.users = users;
            return this;
        }

        public ShipBuilder setBonuses(List<Bonus> bonuses) {
            this.bonuses = bonuses;
            return this;
        }

        public Ship build() {
            Ship ship = new Ship();
            ship.setId(id);
            ship.setName(name);
            ship.setNameEn(nameEn);
            ship.setNameUa(nameUa);
            ship.setPrice(price);
            ship.setPortsVisited(portsVisited);
            ship.setDeparture(departure);
            ship.setArrival(arrival);
            ship.setCruiseDuration(cruiseDuration);
            ship.setShipClass(shipClass);
            ship.setPassengerCapacity(passengerCapacity);
            ship.setFreePlaces(freePlaces);
            ship.setStaff(staff);
            ship.setPorts(ports);
            ship.setUsers(users);
            ship.setBonuses(bonuses);
            return ship;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ship ship = (Ship) o;

        if (price != ship.price) return false;
        if (portsVisited != ship.portsVisited) return false;
        if (cruiseDuration != ship.cruiseDuration) return false;
        if (passengerCapacity != ship.passengerCapacity) return false;
        if (freePlaces != ship.freePlaces) return false;
        if (staff != ship.staff) return false;
        if (name != null ? !name.equals(ship.name) : ship.name != null) return false;
        if (nameEn != null ? !nameEn.equals(ship.nameEn) : ship.nameEn != null) return false;
        if (nameUa != null ? !nameUa.equals(ship.nameUa) : ship.nameUa != null) return false;
        if (departure != null ? !departure.equals(ship.departure) : ship.departure != null) return false;
        if (arrival != null ? !arrival.equals(ship.arrival) : ship.arrival != null) return false;
        if (shipClass != ship.shipClass) return false;
        if (ports != null ? !ports.equals(ship.ports) : ship.ports != null) return false;
        if (users != null ? !users.equals(ship.users) : ship.users != null) return false;
        return bonuses != null ? bonuses.equals(ship.bonuses) : ship.bonuses == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (nameEn != null ? nameEn.hashCode() : 0);
        result = 31 * result + (nameUa != null ? nameUa.hashCode() : 0);
        result = 31 * result + price;
        result = 31 * result + portsVisited;
        result = 31 * result + (departure != null ? departure.hashCode() : 0);
        result = 31 * result + (arrival != null ? arrival.hashCode() : 0);
        result = 31 * result + cruiseDuration;
        result = 31 * result + (shipClass != null ? shipClass.hashCode() : 0);
        result = 31 * result + passengerCapacity;
        result = 31 * result + freePlaces;
        result = 31 * result + staff;
        result = 31 * result + (ports != null ? ports.hashCode() : 0);
        result = 31 * result + (users != null ? users.hashCode() : 0);
        result = 31 * result + (bonuses != null ? bonuses.hashCode() : 0);
        return result;
    }
}
