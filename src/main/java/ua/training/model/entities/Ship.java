package ua.training.model.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * Максим
 * 27.04.2018
 */
public class Ship {
    public enum Class {
        STANDARD, PREMIUM, LUX
    }

    public enum  Bonus {
    }
    private int id;
    private String cruiseName;
    private BigDecimal price;
    private int portsVisited;
    private LocalDate departure;
    private LocalDate arrival;
    private int cruiseDuration;
    private Class shipClass;
    private int passengerCapacity;
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

    public String getCruiseName() {
        return cruiseName;
    }

    public void setCruiseName(String cruiseName) {
        this.cruiseName = cruiseName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
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

    public Class getShipClass() {
        return shipClass;
    }

    public void setShipClass(Class shipClass) {
        this.shipClass = shipClass;
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public void setPassengerCapacity(int passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
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
        private String cruiseName;
        private BigDecimal price;
        private int portsVisited;
        private LocalDate departure;
        private LocalDate arrival;
        private int cruiseDuration;
        private Class shipClass;
        private int passengerCapacity;
        private int staff;

        private List<Port> ports;
        private List<User> users;
        private List<Bonus> bonuses;

        public ShipBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public ShipBuilder setCruiseName(String cruiseName) {
            this.cruiseName = cruiseName;
            return this;
        }

        public ShipBuilder setPrice(BigDecimal price) {
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

        public ShipBuilder setShipClass(Class shipClass) {
            this.shipClass = shipClass;
            return this;
        }

        public ShipBuilder setPassengerCapacity(int passengerCapacity) {
            this.passengerCapacity = passengerCapacity;
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
            ship.setCruiseName(cruiseName);
            ship.setPrice(price);
            ship.setPortsVisited(portsVisited);
            ship.setDeparture(departure);
            ship.setArrival(arrival);
            ship.setCruiseDuration(cruiseDuration);
            ship.setShipClass(shipClass);
            ship.setPassengerCapacity(passengerCapacity);
            ship.setStaff(staff);
            ship.setPorts(ports);
            ship.setUsers(users);
            ship.setBonuses(bonuses);
            return ship;
        }
    }
}
