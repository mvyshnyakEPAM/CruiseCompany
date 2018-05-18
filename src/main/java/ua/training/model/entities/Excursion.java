package ua.training.model.entities;

import java.util.List;

/**
 * Максим
 * 27.04.2018
 */
public class Excursion {
    private int id;
    private String name;
    private String nameEn;
    private String nameUa;
    private int price;

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
        private String nameEn;
        private String nameUa;
        private int price;

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

        public ExcursionBuilder setNameEn(String nameEn) {
            this.nameEn = nameEn;
            return this;
        }

        public ExcursionBuilder setNameUa(String nameUa) {
            this.nameUa = nameUa;
            return this;
        }

        public ExcursionBuilder setPrice(int price) {
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
            excursion.setId(id);
            excursion.setName(name);
            excursion.setNameEn(nameEn);
            excursion.setNameUa(nameUa);
            excursion.setPrice(price);
            excursion.setPort(port);
            excursion.setUsers(users);
            return excursion;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Excursion excursion = (Excursion) o;

        if (price != excursion.price) return false;
        if (name != null ? !name.equals(excursion.name) : excursion.name != null) return false;
        if (nameEn != null ? !nameEn.equals(excursion.nameEn) : excursion.nameEn != null) return false;
        if (nameUa != null ? !nameUa.equals(excursion.nameUa) : excursion.nameUa != null) return false;
        if (port != null ? !port.equals(excursion.port) : excursion.port != null) return false;
        return users != null ? users.equals(excursion.users) : excursion.users == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (nameEn != null ? nameEn.hashCode() : 0);
        result = 31 * result + (nameUa != null ? nameUa.hashCode() : 0);
        result = 31 * result + price;
        result = 31 * result + (port != null ? port.hashCode() : 0);
        result = 31 * result + (users != null ? users.hashCode() : 0);
        return result;
    }
}
