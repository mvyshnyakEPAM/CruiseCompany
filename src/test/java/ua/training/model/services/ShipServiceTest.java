package ua.training.model.services;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ua.training.controller.exceptions.CruiseAlreadyBoughtException;
import ua.training.model.dao.ExcursionDao;
import ua.training.model.dao.ShipDao;
import ua.training.model.dao.cp.ConnectionPool;
import ua.training.model.dao.factory.JDBCDaoFactory;
import ua.training.model.dao.impl.ExcursionDaoImpl;
import ua.training.model.dao.impl.ShipDaoImpl;
import ua.training.model.entities.Excursion;
import ua.training.model.entities.Ship;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 * Максим
 * 23.05.2018
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(ConnectionPool.class)
public class ShipServiceTest {
    private ShipService shipService = ShipService.getInstance();

    @Test
    public void test_pay_cruise_ok() throws CruiseAlreadyBoughtException, SQLException {
        ShipService spyShipService = Mockito.spy(shipService);
        spyShipService.daoFactory = Mockito.mock(JDBCDaoFactory.class);
        PowerMockito.mockStatic(ConnectionPool.class);
        Connection connection = Mockito.mock(Connection.class);
        ShipDao shipDao = Mockito.mock(ShipDaoImpl.class);
        ExcursionDao excursionDao = Mockito.mock(ExcursionDaoImpl.class);

        Ship ship = new Ship.ShipBuilder()
                .setId(1)
                .setNameEn("shipName")
                .build();

        Set<Excursion> excursions = new HashSet<>();
        excursions.add(new Excursion.ExcursionBuilder().setId(1).build());

        BDDMockito.given(ConnectionPool.getConnection()).willReturn(connection);
        Mockito.when(spyShipService.daoFactory.createShipDao(connection)).thenReturn(shipDao);
        Mockito.when(spyShipService.daoFactory.createExcursionDao(connection)).thenReturn(excursionDao);
        Mockito.doReturn(true).when(spyShipService).freePlacesAvailable("shipName");
        Mockito.when(shipDao.getFreePlacesAmount("shipName")).thenReturn(3);

        spyShipService.payCruise(1, ship, excursions);

        PowerMockito.verifyStatic();
        ConnectionPool.getConnection();
        Mockito.verify(spyShipService.daoFactory).createShipDao(connection);
        Mockito.verify(spyShipService.daoFactory).createExcursionDao(connection);
        Mockito.verify(connection).setAutoCommit(false);
        Mockito.verify(spyShipService).freePlacesAvailable("shipName");
        Mockito.verify(shipDao).addShipToUser(1, ship.getId());
        Mockito.verify(shipDao).getFreePlacesAmount("shipName");
        Mockito.verify(shipDao).update(ship);
        for (Excursion excursion : excursions) {
            Mockito.verify(excursionDao).addExcursionToUser(excursion.getId(), 1, "shipName");
        }
        Mockito.verify(connection).commit();
        Mockito.verify(shipDao).close();
        Mockito.verify(excursionDao).close();
        Mockito.verifyNoMoreInteractions(shipDao, excursionDao, spyShipService.daoFactory, connection);
        PowerMockito.verifyNoMoreInteractions(ConnectionPool.class);
    }

    @Test
    public void test_pay_cruise_no_free_places() throws CruiseAlreadyBoughtException, SQLException {
        ShipService spyShipService = Mockito.spy(shipService);
        spyShipService.daoFactory = Mockito.mock(JDBCDaoFactory.class);
        PowerMockito.mockStatic(ConnectionPool.class);
        Connection connection = Mockito.mock(Connection.class);
        ShipDao shipDao = Mockito.mock(ShipDaoImpl.class);
        ExcursionDao excursionDao = Mockito.mock(ExcursionDaoImpl.class);

        Ship ship = new Ship.ShipBuilder()
                .setId(1)
                .setNameEn("shipName")
                .build();

        Set<Excursion> excursions = new HashSet<>();
        excursions.add(new Excursion.ExcursionBuilder().setId(1).build());

        BDDMockito.given(ConnectionPool.getConnection()).willReturn(connection);
        Mockito.when(spyShipService.daoFactory.createShipDao(connection)).thenReturn(shipDao);
        Mockito.when(spyShipService.daoFactory.createExcursionDao(connection)).thenReturn(excursionDao);
        Mockito.doReturn(false).when(spyShipService).freePlacesAvailable("shipName");

        spyShipService.payCruise(1, ship, excursions);

        PowerMockito.verifyStatic();
        ConnectionPool.getConnection();
        Mockito.verify(spyShipService.daoFactory).createShipDao(connection);
        Mockito.verify(spyShipService.daoFactory).createExcursionDao(connection);
        Mockito.verify(connection).setAutoCommit(false);
        Mockito.verify(spyShipService).freePlacesAvailable("shipName");
        Mockito.verify(shipDao).close();
        Mockito.verify(excursionDao).close();
        Mockito.verifyNoMoreInteractions(shipDao, excursionDao, spyShipService.daoFactory, connection);
        PowerMockito.verifyNoMoreInteractions(ConnectionPool.class);
    }

    @Test
    public void test_pay_cruise_already_bought() throws CruiseAlreadyBoughtException, SQLException {
        ShipService spyShipService = Mockito.spy(shipService);
        spyShipService.daoFactory = Mockito.mock(JDBCDaoFactory.class);
        PowerMockito.mockStatic(ConnectionPool.class);
        Connection connection = Mockito.mock(Connection.class);
        ShipDao shipDao = Mockito.mock(ShipDaoImpl.class);
        ExcursionDao excursionDao = Mockito.mock(ExcursionDaoImpl.class);

        Ship ship = new Ship.ShipBuilder()
                .setId(1)
                .setNameEn("shipName")
                .build();

        Set<Excursion> excursions = new HashSet<>();
        excursions.add(new Excursion.ExcursionBuilder().setId(1).build());

        BDDMockito.given(ConnectionPool.getConnection()).willReturn(connection);
        Mockito.when(spyShipService.daoFactory.createShipDao(connection)).thenReturn(shipDao);
        Mockito.when(spyShipService.daoFactory.createExcursionDao(connection)).thenReturn(excursionDao);
        Mockito.doReturn(false).when(spyShipService).freePlacesAvailable("shipName");
        Mockito.doThrow(CruiseAlreadyBoughtException.class).when(shipDao).addShipToUser(1, ship.getId());

        spyShipService.payCruise(1, ship, excursions);

        PowerMockito.verifyStatic();
        ConnectionPool.getConnection();
        Mockito.verify(spyShipService.daoFactory).createShipDao(connection);
        Mockito.verify(spyShipService.daoFactory).createExcursionDao(connection);
        Mockito.verify(connection).setAutoCommit(false);
        Mockito.verify(spyShipService).freePlacesAvailable("shipName");
        Mockito.verify(shipDao).close();
        Mockito.verify(excursionDao).close();
        Mockito.verifyNoMoreInteractions(shipDao, excursionDao, spyShipService.daoFactory, connection);
        PowerMockito.verifyNoMoreInteractions(ConnectionPool.class);
    }

    @Test
    public void test_free_places_available_when_zero_places() throws SQLException {
        shipService.daoFactory = Mockito.mock(JDBCDaoFactory.class);
        PowerMockito.mockStatic(ConnectionPool.class);
        Connection connection = Mockito.mock(Connection.class);
        ShipDao shipDao = Mockito.mock(ShipDaoImpl.class);

        BDDMockito.given(ConnectionPool.getConnection()).willReturn(connection);
        Mockito.when(shipService.daoFactory.createShipDao(connection)).thenReturn(shipDao);
        Mockito.when(shipDao.getFreePlacesAmount("shipName")).thenReturn(0);

        boolean freePlacesAvailable = shipService.freePlacesAvailable("shipName");

        PowerMockito.verifyStatic();
        ConnectionPool.getConnection();
        Mockito.verify(shipService.daoFactory).createShipDao(connection);
        Mockito.verify(shipDao).getFreePlacesAmount("shipName");
        Mockito.verify(shipDao).close();
        Mockito.verifyNoMoreInteractions(shipService.daoFactory, connection, shipDao);
        PowerMockito.verifyNoMoreInteractions(ConnectionPool.class);
        Assert.assertFalse(freePlacesAvailable);
    }

    @Test
    public void test_free_places_available_when_places_greater_zero() {
        shipService.daoFactory = Mockito.mock(JDBCDaoFactory.class);
        PowerMockito.mockStatic(ConnectionPool.class);
        Connection connection = Mockito.mock(Connection.class);
        ShipDao shipDao = Mockito.mock(ShipDaoImpl.class);

        BDDMockito.given(ConnectionPool.getConnection()).willReturn(connection);
        Mockito.when(shipService.daoFactory.createShipDao(connection)).thenReturn(shipDao);
        Mockito.when(shipDao.getFreePlacesAmount("shipName")).thenReturn(1);

        boolean freePlacesAvailable = shipService.freePlacesAvailable("shipName");

        PowerMockito.verifyStatic();
        ConnectionPool.getConnection();
        Mockito.verify(shipService.daoFactory).createShipDao(connection);
        Mockito.verify(shipDao).getFreePlacesAmount("shipName");
        Mockito.verify(shipDao).close();
        Mockito.verifyNoMoreInteractions(shipService.daoFactory, connection, shipDao);
        PowerMockito.verifyNoMoreInteractions(ConnectionPool.class);
        Assert.assertTrue(freePlacesAvailable);
    }

    @Test
    public void test_free_places_available_when_places_less_zero() {
        shipService.daoFactory = Mockito.mock(JDBCDaoFactory.class);
        PowerMockito.mockStatic(ConnectionPool.class);
        Connection connection = Mockito.mock(Connection.class);
        ShipDao shipDao = Mockito.mock(ShipDaoImpl.class);

        BDDMockito.given(ConnectionPool.getConnection()).willReturn(connection);
        Mockito.when(shipService.daoFactory.createShipDao(connection)).thenReturn(shipDao);
        Mockito.when(shipDao.getFreePlacesAmount("shipName")).thenReturn(-1);

        boolean freePlacesAvailable = shipService.freePlacesAvailable("shipName");

        PowerMockito.verifyStatic();
        ConnectionPool.getConnection();
        Mockito.verify(shipService.daoFactory).createShipDao(connection);
        Mockito.verify(shipDao).getFreePlacesAmount("shipName");
        Mockito.verify(shipDao).close();
        Mockito.verifyNoMoreInteractions(shipService.daoFactory, connection, shipDao);
        PowerMockito.verifyNoMoreInteractions(ConnectionPool.class);
        Assert.assertFalse(freePlacesAvailable);
    }
}
