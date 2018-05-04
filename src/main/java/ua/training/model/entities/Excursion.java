package ua.training.model.entities;

import java.math.BigDecimal;
import java.util.List;

/**
 * Максим
 * 27.04.2018
 */
public class Excursion {
    private int id;
    private String name;
    private BigDecimal price;

    private Port port;
    private List<User> users;

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Port getPort() {
        return port;
    }

    public void setPort(Port port) {
        this.port = port;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public static final class ExcursionBuilder {
        private int id;
        private String name;
        private BigDecimal price;

        private Port port;
        private List<User> users;

        public ExcursionBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public ExcursionBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public ExcursionBuilder setPrice(BigDecimal price) {
            this.price = price;
            return this;
        }

        public ExcursionBuilder setPort(Port port) {
            this.port = port;
            return this;
        }

        public ExcursionBuilder setUsers(List<User> users) {
            this.users = users;
            return this;
        }

        public Excursion build() {
            Excursion excursion = new Excursion();
            excursion.setName(name);
            excursion.setPrice(price);
            excursion.setPort(port);
            excursion.setUsers(users);
            return excursion;
        }
    }
}
