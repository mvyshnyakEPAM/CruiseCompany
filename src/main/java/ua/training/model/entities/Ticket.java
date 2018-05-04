package ua.training.model.entities;

import java.math.BigDecimal;
import java.util.List;

/**
 * Максим
 * 27.04.2018
 */
public class Ticket {
    private int id;
    private BigDecimal price;
    private int passengersAmount;

    private List<Ship> ships;
    private List<User> users;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getPassengersAmount() {
        return passengersAmount;
    }

    public void setPassengersAmount(int passengersAmount) {
        this.passengersAmount = passengersAmount;
    }

    public List<Ship> getShips() {
        return ships;
    }

    public void setShips(List<Ship> ships) {
        this.ships = ships;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public static final class TicketBuilder {
        private int id;
        private BigDecimal price;
        private int passengersAmount;

        private List<Ship> ships;
        private List<User> users;

        public TicketBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public TicketBuilder setPrice(BigDecimal price) {
            this.price = price;
            return this;
        }

        public TicketBuilder setPassengersAmount(int passengersAmount) {
            this.passengersAmount = passengersAmount;
            return this;
        }

        public TicketBuilder setShips(List<Ship> ships) {
            this.ships = ships;
            return this;
        }

        public TicketBuilder setUsers(List<User> users) {
            this.users = users;
            return this;
        }

        public Ticket build() {
            Ticket ticket = new Ticket();
            ticket.setPrice(price);
            ticket.setPassengersAmount(passengersAmount);
            ticket.setShips(ships);
            ticket.setUsers(users);
            return ticket;
        }
    }
}
