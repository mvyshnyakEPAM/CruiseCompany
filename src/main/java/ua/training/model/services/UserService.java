package ua.training.model.services;

import ua.training.model.entities.User;

import java.util.Optional;

/**
 * Максим
 * 30.04.2018
 */
public class UserService {
    private final static class Holder {
        private static final UserService INSTANCE = new UserService();
    }

    public static UserService getInstance() {
        return Holder.INSTANCE;
    }

    public void signUp(User user) {

    }

    public Optional<User> signIn(String login, String password) {
        return Optional.empty();
    }
}
