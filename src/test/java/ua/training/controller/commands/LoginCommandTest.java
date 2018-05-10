package ua.training.controller.commands;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import ua.training.constants.Attributes;
import ua.training.constants.Messages;
import ua.training.constants.Parameters;
import ua.training.controller.listeners.LoginDto;
import ua.training.model.entities.User;
import ua.training.model.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Максим
 * 09.05.2018
 */
public class LoginCommandTest {
    private LoginCommand loginCommand;

    @Before
    public void setUp() {
        loginCommand = new LoginCommand();
    }

    @Test
    public void test_invalid_login_param() {
        //init
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpSession session = Mockito.mock(HttpSession.class);
        loginCommand.userService = Mockito.mock(UserService.class);
        Map<String, String> map = new HashMap<>();
        map.put(Attributes.LOGIN_MISMATCH, Messages.LOGIN_MISMATCH);

        Mockito.when(request.getParameter(Parameters.LOGIN)).thenReturn(null);
        Mockito.when(request.getParameter(Parameters.PASSWORD)).thenReturn("123");
        //use
        loginCommand.execute(request);
        //check
        Mockito.verify(request).getParameter(Parameters.LOGIN);
        Mockito.verify(request).getParameter(Parameters.PASSWORD);
        Mockito.verify(request).setAttribute(Attributes.MESSAGES, map);
        Mockito.verifyNoMoreInteractions(request, session, loginCommand.userService);
    }

    @Test
    public void test_invalid_password_param() {
        //init
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpSession session = Mockito.mock(HttpSession.class);
        loginCommand.userService = Mockito.mock(UserService.class);
        Map<String, String> map = new HashMap<>();
        map.put(Attributes.PASSWORD_MISMATCH, Messages.PASSWORD_MISMATCH);

        Mockito.when(request.getParameter(Parameters.LOGIN)).thenReturn("user");
        Mockito.when(request.getParameter(Parameters.PASSWORD)).thenReturn(null);
        //use
        loginCommand.execute(request);
        //check
        Mockito.verify(request).getParameter(Parameters.LOGIN);
        Mockito.verify(request).getParameter(Parameters.PASSWORD);
        Mockito.verify(request).setAttribute(Attributes.MESSAGES, map);
        Mockito.verifyNoMoreInteractions(request, session, loginCommand.userService);
    }

    @Test
    public void test_ok() {
        //init
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpSession session = Mockito.mock(HttpSession.class);
        loginCommand.userService = Mockito.mock(UserService.class);
        User user = new User.UserBuilder()
                .setLogin("user")
                .setPassword("123")
                .setRole(User.Role.CLIENT)
                .build();

        Mockito.when(request.getParameter(Parameters.LOGIN)).thenReturn("user");
        Mockito.when(request.getParameter(Parameters.PASSWORD)).thenReturn("123");
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(loginCommand.userService.signIn("user", "123")).thenReturn(Optional.ofNullable(user));
        //use
        loginCommand.execute(request);
        //check
        Mockito.verify(request).getParameter(Parameters.LOGIN);
        Mockito.verify(request).getParameter(Parameters.PASSWORD);
        Mockito.verify(request).getSession();
        Mockito.verify(loginCommand.userService).signIn("user", "123");
        Mockito.verify(session).setAttribute(Attributes.USER, new LoginDto("user"));
        Mockito.verify(session).setAttribute(Attributes.ROLE, user.getRole());
        Mockito.verifyNoMoreInteractions(request, session, loginCommand.userService);
    }

    @Test
    public void test_no_in_db() {
        //init
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpSession session = Mockito.mock(HttpSession.class);
        loginCommand.userService = Mockito.mock(UserService.class);

        Mockito.when(request.getParameter(Parameters.LOGIN)).thenReturn("user");
        Mockito.when(request.getParameter(Parameters.PASSWORD)).thenReturn("123");
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(loginCommand.userService.signIn("user", "123")).thenReturn(Optional.empty());
        //use
        loginCommand.execute(request);
        //check
        Mockito.verify(request).getParameter(Parameters.LOGIN);
        Mockito.verify(request).getParameter(Parameters.PASSWORD);
        Mockito.verify(request).getSession();
        Mockito.verify(loginCommand.userService).signIn("user", "123");
        Mockito.verify(request).setAttribute(Attributes.MESSAGE, Messages.LOGIN_FAIL);
        Mockito.verifyNoMoreInteractions(request, session, loginCommand.userService);
    }
}
