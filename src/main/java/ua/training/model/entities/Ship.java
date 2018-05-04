package ua.training.model.entities;

import ua.training.model.entities.enums.ShipClass;

import java.time.LocalDate;
import java.util.List;

/**
 * Максим
 * 27.04.2018
 */
public class Ship {
    private int id;
    private String cruiseName;
    private int portsVisited;
    private LocalDate departure;
    private LocalDate arrival;
    private int cruiseDuration;
    private ShipClass shipClass;
    private int passengerCapacity;
    private int staff;

    private List<Port> ports;
    private List<Ticket> tickets;

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

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public static final class ShipBuilder {
        private int id;
        private String cruiseName;
        private int portsVisited;
        private LocalDate departure;
        private LocalDate arrival;
        private int cruiseDuration;
        private ShipClass shipClass;
        private int passengerCapacity;
        private int staff;

        private List<Port> ports;
        private List<Ticket> tickets;

        public ShipBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public ShipBuilder setCruiseName(String cruiseName) {
            this.cruiseName = cruiseName;
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

        public ShipBuilder setTickets(List<Ticket> tickets) {
            this.tickets = tickets;
            return this;
        }

        public Ship build() {
            Ship ship = new Ship();
            ship.setCruiseName(cruiseName);
            ship.setPortsVisited(portsVisited);
            ship.setDeparture(departure);
            ship.setArrival(arrival);
            ship.setCruiseDuration(cruiseDuration);
            ship.setShipClass(shipClass);
            ship.setPassengerCapacity(passengerCapacity);
            ship.setStaff(staff);
            return ship;
        }
    }
}
