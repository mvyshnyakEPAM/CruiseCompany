package ua.training.model.entities;

import java.util.List;

/**
 * Максим
 * 27.04.2018
 */
public class User {
    public enum Role {
        GUEST, CLIENT, ADMIN
    }

    private int id;
    private String login;
    private String password;
    private Role role;

    private List<Ship> cruises;
    private List<Excursion> excursions;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Ship> getCruises() {
        return cruises;
    }

    public void setCruises(List<Ship> cruises) {
        this.cruises = cruises;
    }

    public List<Excursion> getExcursions() {
        return excursions;
    }

    public void setExcursions(List<Excursion> excursions) {
        this.excursions = excursions;
    }

    public static final class UserBuilder {
        private int id;
        private String login;
        private String password;
        private Role role;

        private List<Ship> tickets;
        private List<Excursion> excursions;

        public UserBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public UserBuilder setLogin(String login) {
            this.login = login;
            return this;
        }

        public UserBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder setRole(Role role) {
            this.role = role;
            return this;
        }

        public UserBuilder setTickets(List<Ship> tickets) {
            this.tickets = tickets;
            return this;
        }

        public UserBuilder setExcursions(List<Excursion> excursions) {
            this.excursions = excursions;
            return this;
        }

        public User build() {
            User user = new User();
            user.setId(id);
            user.setLogin(login);
            user.setPassword(password);
            user.setRole(role);
            user.setCruises(tickets);
            user.setExcursions(excursions);
            return user;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (login != null ? !login.equals(user.login) : user.login != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (role != user.role) return false;
        if (cruises != null ? !cruises.equals(user.cruises) : user.cruises != null) return false;
        return excursions != null ? excursions.equals(user.excursions) : user.excursions == null;
    }

    @Override
    public int hashCode() {
        int result = login != null ? login.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (cruises != null ? cruises.hashCode() : 0);
        result = 31 * result + (excursions != null ? excursions.hashCode() : 0);
        return result;
    }
}
