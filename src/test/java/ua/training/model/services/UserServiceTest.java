package ua.training.model.services;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import ua.training.model.entities.User;

import java.util.Optional;

/**
 * Максим
 * 09.05.2018
 */
public class UserServiceTest {
    private UserService userService;

    @Before
    public void setUp() {
        userService = Mockito.mock(UserService.class);
    }

    @Test
    public void test_signIn_return_user() {
        User defUser = new User.UserBuilder()
                .setId(1)
                .setLogin("user")
                .setPassword("123")
                .setRole(User.Role.CLIENT)
                .build();
        Mockito.when(userService.signIn("user", "123")).thenReturn(Optional.of(defUser));
        Optional<User> user = userService.signIn("user", "123");
        Mockito.verify(userService).signIn("user", "123");
        Mockito.verifyNoMoreInteractions(userService);
        Assert.assertTrue(user.isPresent());
    }

    @Test
    public void test_signIn_return_empty_user() {
        Mockito.when(userService.signIn("user", "123")).thenReturn(Optional.empty());
        Optional<User> user = userService.signIn("user", "123");
        Mockito.verify(userService).signIn("user", "123");
        Mockito.verifyNoMoreInteractions(userService);
        Assert.assertFalse(user.isPresent());
    }
}
