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
            ship.setStaff(staff);
            ship.setPorts(ports);
            ship.setUsers(users);
            ship.setBonuses(bonuses);
            return ship;
        }
    }
}
