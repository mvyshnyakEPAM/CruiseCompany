package ua.training.model.services;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ua.training.model.dao.UserDao;
import ua.training.model.dao.cp.ConnectionPool;
import ua.training.model.dao.factory.JDBCDaoFactory;
import ua.training.model.dao.impl.UserDaoImpl;
import ua.training.model.entities.User;

import java.sql.Connection;
import java.util.Optional;

/**
 * Максим
 * 09.05.2018
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(ConnectionPool.class)
public class UserServiceTest {
    private UserService userService;

    @Before
    public void setUp() {
        userService = UserService.getInstance();
    }


    @Test
    public void test_signIn_return_user_ok() {
        User testUser = new User.UserBuilder()
                .setId(1)
                .setLogin("user")
                .setPassword(DigestUtils.md5Hex("123"))
                .setRole(User.Role.CLIENT)
                .build();

        userService.daoFactory = Mockito.mock(JDBCDaoFactory.class);
        PowerMockito.mockStatic(ConnectionPool.class);
        UserDao userDao = Mockito.mock(UserDaoImpl.class);
        Connection connection = Mockito.mock(Connection.class);

        BDDMockito.given(ConnectionPool.getConnection()).willReturn(connection);
        Mockito.when(userService.daoFactory.createUserDao(connection)).thenReturn(userDao);
        Mockito.when(userDao.getUserByLogin("user")).thenReturn(Optional.of(testUser));

        Optional<User> user = userService.signIn("user", "123");

        PowerMockito.verifyStatic();
        ConnectionPool.getConnection();
        Mockito.verify(userService.daoFactory).createUserDao(connection);
        Mockito.verify(userDao).getUserByLogin("user");
        Mockito.verify(userDao).close();
        Mockito.verifyNoMoreInteractions(userService.daoFactory, userDao, connection);
        Assert.assertTrue(user.isPresent());
    }

    @Test
    public void test_signIn_return_empty_user_when_passwords_not_equal() {
        User testUser = new User.UserBuilder()
                .setId(1)
                .setLogin("user")
                .setPassword(DigestUtils.md5Hex("123"))
                .setRole(User.Role.CLIENT)
                .build();

        userService.daoFactory = Mockito.mock(JDBCDaoFactory.class);
        PowerMockito.mockStatic(ConnectionPool.class);
        UserDao userDao = Mockito.mock(UserDaoImpl.class);
        Connection connection = Mockito.mock(Connection.class);

        BDDMockito.given(ConnectionPool.getConnection()).willReturn(connection);
        Mockito.when(userService.daoFactory.createUserDao(connection)).thenReturn(userDao);
        Mockito.when(userDao.getUserByLogin("user")).thenReturn(Optional.of(testUser));

        Optional<User> user = userService.signIn("user", "124");

        PowerMockito.verifyStatic();
        ConnectionPool.getConnection();
        Mockito.verify(userService.daoFactory).createUserDao(connection);
        Mockito.verify(userDao).getUserByLogin("user");
        Mockito.verify(userDao).close();
        Mockito.verifyNoMoreInteractions(userService.daoFactory, userDao, connection);
        Assert.assertFalse(user.isPresent());
    }

    @Test
    public void test_signIn_return_empty_user_when_login_is_wrong() {
        userService.daoFactory = Mockito.mock(JDBCDaoFactory.class);
        PowerMockito.mockStatic(ConnectionPool.class);
        UserDao userDao = Mockito.mock(UserDaoImpl.class);
        Connection connection = Mockito.mock(Connection.class);

        BDDMockito.given(ConnectionPool.getConnection()).willReturn(connection);
        Mockito.when(userService.daoFactory.createUserDao(connection)).thenReturn(userDao);
        Mockito.when(userDao.getUserByLogin("user")).thenReturn(Optional.empty());

        Optional<User> user = userService.signIn("user", "124");

        PowerMockito.verifyStatic();
        ConnectionPool.getConnection();
        Mockito.verify(userService.daoFactory).createUserDao(connection);
        Mockito.verify(userDao).getUserByLogin("user");
        Mockito.verify(userDao).close();
        Mockito.verifyNoMoreInteractions(userService.daoFactory, userDao, connection);
        Assert.assertFalse(user.isPresent());
    }
}
