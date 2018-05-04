package ua.training.model.entities;

import ua.training.model.entities.enums.Role;

import java.util.List;

/**
 * Максим
 * 27.04.2018
 */
public class User {
    private int id;
    private String login;
    private String password;
    private Role role;

    private List<Ticket> tickets;
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

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
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

        private List<Ticket> tickets;
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

        public UserBuilder setTickets(List<Ticket> tickets) {
            this.tickets = tickets;
            return this;
        }

        public UserBuilder setExcursions(List<Excursion> excursions) {
            this.excursions = excursions;
            return this;
        }

        public User build() {
            User user = new User();
            user.setLogin(login);
            user.setPassword(password);
            user.setRole(role);
            user.setTickets(tickets);
            user.setExcursions(excursions);
            return user;
        }
    }
}
