package ua.training.controller.commands.client;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import ua.training.model.entities.Excursion;
import ua.training.model.entities.Port;
import ua.training.model.entities.Ship;
import ua.training.model.entities.enums.ShipClass;
import ua.training.model.services.ShipService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Максим
 * 15.05.2018
 */
public class ShowCruiseInfoCommandTest {
    private ShowCruiseInfoCommand showCruiseInfoCommand;

    @Before
    public void setUp() {
        showCruiseInfoCommand = new ShowCruiseInfoCommand();
    }
    @Test
    public void test_ok() {
        showCruiseInfoCommand.shipService = Mockito.mock(ShipService.class);
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpSession session = Mockito.mock(HttpSession.class);
        List<Excursion> excursionsBarc = Arrays.asList(
                new Excursion.ExcursionBuilder().setName("San Miguel Monastery").setPrice(350).build(),
                new Excursion.ExcursionBuilder().setName("Triangle Dali").setPrice(550).build(),
                new Excursion.ExcursionBuilder().setName("Monastery Montserrat").setPrice(245).build());
        List<Excursion> excursionsSav = Arrays.asList(
                new Excursion.ExcursionBuilder().setName("Egyptian Museum").setPrice(69).build(),
                new Excursion.ExcursionBuilder().setName("Museum of Cinema").setPrice(69).build(),
                new Excursion.ExcursionBuilder().setName("Medieval Towns").setPrice(60).build());
        List<Excursion> excursionsNap = Arrays.asList(
                new Excursion.ExcursionBuilder().setName("Excursion to Vesuvius").setPrice(180).build(),
                new Excursion.ExcursionBuilder().setName("Excursion to Pompeii").setPrice(120).build());
        List<Excursion> excursionsVal = Arrays.asList(
                new Excursion.ExcursionBuilder().setName("Excursion to Malta").setPrice(250).build(),
                new Excursion.ExcursionBuilder().setName("Valletta and Medina").setPrice(260).build());
        List<Excursion> excursionsCat = Arrays.asList(
                new Excursion.ExcursionBuilder().setName("Etna and Taormina").setPrice(150).build(),
                new Excursion.ExcursionBuilder().setName("Valley of the Temples").setPrice(129).build(),
                new Excursion.ExcursionBuilder().setName("Agrigento and Piazza").setPrice(50).build());
        List<Port> ports = Arrays.asList(
                new Port.PortBuilder().setName("Barcelona").setCountry("Spain").setExcursions(excursionsBarc).build(),
                new Port.PortBuilder().setName("Savona").setCountry("Italy").setExcursions(excursionsSav).build(),
                new Port.PortBuilder().setName("Naples").setCountry("Italy").setExcursions(excursionsNap).build(),
                new Port.PortBuilder().setName("Valletta").setCountry("Malta").setExcursions(excursionsVal).build(),
                new Port.PortBuilder().setName("Catania").setCountry("Italy").setExcursions(excursionsCat).build(),
                new Port.PortBuilder().setName("Barcelona").setCountry("Spain").setExcursions(excursionsBarc).build());
        Ship ship = new Ship.ShipBuilder()
                .setId(1)
                .setName("Cruise Fascinosa")
                .setPrice(639)
                .setPortsVisited(6)
                .setDeparture(LocalDate.of(2018, 9, 29))
                .setArrival(LocalDate.of(2018, 10, 5))
                .setCruiseDuration(7)
                .setShipClass(ShipClass.PREMIUM)
                .setPassengerCapacity(2860)
                .setFreePlaces(639)
                .setStaff(1100)
                .setPorts(ports)
                .setBonuses(new ArrayList<>())
                .build();

        Mockito.when(request.getParameter("cruise")).thenReturn("Cruise Fascinosa");
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(showCruiseInfoCommand.shipService.freePlacesAvailable("Cruise Fascinosa"))
                .thenReturn(true);
        Mockito.when(showCruiseInfoCommand.shipService.getCruiseByName("Cruise Fascinosa", "en"))
                .thenReturn(Optional.ofNullable(ship));

        showCruiseInfoCommand.execute(request);

        Mockito.verify(request.getParameter("cruise"));
        Mockito.verify(request.getSession());
        Mockito.verify(showCruiseInfoCommand.shipService.freePlacesAvailable("Cruise Fascinosa"));
        Mockito.verify(showCruiseInfoCommand.shipService.getCruiseByName("Cruise Fascinosa", "en"));
        Mockito.verify(session).setAttribute("cruise", ship);
        Mockito.verifyNoMoreInteractions(showCruiseInfoCommand.shipService, request, session);
    }
}
