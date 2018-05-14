package ua.training.controller.commands;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import ua.training.constants.Attributes;
import ua.training.constants.Messages;
import ua.training.constants.Parameters;
import ua.training.controller.exceptions.LoginAlreadyExistsException;
import ua.training.model.entities.User;
import ua.training.model.services.UserService;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

/**
 * Максим
 * 09.05.2018
 */
public class RegisterCommandTest {
    private RegisterCommand registerCommand;

    @Before
    public void setUp() {
        registerCommand = new RegisterCommand();
    }

    @Test
    public void test_invalid_login_param() {
        //init
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        registerCommand.userService = Mockito.mock(UserService.class);
        Map<String, String> map = new HashMap<>();
        map.put(Attributes.LOGIN_MISMATCH, Messages.LOGIN_MISMATCH);

        Mockito.when(request.getParameter(Parameters.LOGIN)).thenReturn(null);
        Mockito.when(request.getParameter(Parameters.PASSWORD)).thenReturn("123");
        //use
        registerCommand.execute(request);
        //check
        Mockito.verify(request).getParameter(Parameters.LOGIN);
        Mockito.verify(request).getParameter(Parameters.PASSWORD);
        Mockito.verify(request).setAttribute(Attributes.MESSAGES, map);
        Mockito.verifyNoMoreInteractions(request, registerCommand.userService);
    }

    @Test
    public void test_invalid_password_param() {
        //init
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        registerCommand.userService = Mockito.mock(UserService.class);
        Map<String, String> map = new HashMap<>();
        map.put(Attributes.PASSWORD_MISMATCH, Messages.PASSWORD_MISMATCH);

        Mockito.when(request.getParameter(Parameters.LOGIN)).thenReturn("user");
        Mockito.when(request.getParameter(Parameters.PASSWORD)).thenReturn(null);
        //use
        registerCommand.execute(request);
        //check
        Mockito.verify(request).getParameter(Parameters.LOGIN);
        Mockito.verify(request).getParameter(Parameters.PASSWORD);
        Mockito.verify(request).setAttribute(Attributes.MESSAGES, map);
        Mockito.verifyNoMoreInteractions(request, registerCommand.userService);
    }

    @Test
    public void test_login_not_unique() throws LoginAlreadyExistsException {
        //init
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        registerCommand.userService = Mockito.mock(UserService.class);
        User user = new User.UserBuilder()
                .setLogin("user")
                .setPassword(DigestUtils.md5Hex("123"))
                .setRole(User.Role.CLIENT)
                .build();

        Mockito.when(request.getParameter(Parameters.LOGIN)).thenReturn("user");
        Mockito.when(request.getParameter(Parameters.PASSWORD)).thenReturn("123");
        Mockito.doThrow(new LoginAlreadyExistsException(new SQLIntegrityConstraintViolationException().getMessage(), user.getLogin()))
                .when(registerCommand.userService).signUp(user);

        //use
        registerCommand.execute(request);
        //check
        Mockito.verify(request).getParameter(Parameters.LOGIN);
        Mockito.verify(request).getParameter(Parameters.PASSWORD);
        Mockito.verify(registerCommand.userService).signUp(user);
        Mockito.verify(request).setAttribute(Attributes.MESSAGE, Messages.REGISTRATION_FAIL);
        Mockito.verifyNoMoreInteractions(request, registerCommand.userService);
    }

    @Test
    public void test_ok() throws LoginAlreadyExistsException {
        //init
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        registerCommand.userService = Mockito.mock(UserService.class);
        User user = new User.UserBuilder()
                .setLogin("user")
                .setPassword(DigestUtils.md5Hex("123"))
                .setRole(User.Role.CLIENT)
                .build();

        Mockito.when(request.getParameter(Parameters.LOGIN)).thenReturn("user");
        Mockito.when(request.getParameter(Parameters.PASSWORD)).thenReturn("123");
        //use
        registerCommand.execute(request);
        //check
        Mockito.verify(request).getParameter(Parameters.LOGIN);
        Mockito.verify(request).getParameter(Parameters.PASSWORD);
        Mockito.verify(registerCommand.userService).signUp(user);
        Mockito.verifyNoMoreInteractions(request, registerCommand.userService);
    }
}
