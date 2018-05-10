package ua.training.model.services;

import org.junit.Before;
import org.junit.Test;

/**
 * Максим
 * 09.05.2018
 */
public class UserServiceTest {
    private UserService userService;

    @Before
    public void setUp() {
        userService = new UserService();
    }

    @Test
    public void test() {
        //init

        //use
        userService.signIn("user", "123");
        //check
    }
}
