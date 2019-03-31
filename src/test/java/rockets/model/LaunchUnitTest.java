package rockets.model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
public class LaunchUnitTest {

    private Launch target;

    @BeforeEach
    public void setUp()
    {
        target = new Launch();
    }

    @DisplayName("Should throw exception when null is passed to setLaunchDate method")
    @Test
    public void shouldThrowExceptionWhenSetLaunchDateToNull()
    {
        NullPointerException exception = assertThrows(NullPointerException.class,
                ()->target.setLaunchDate(null));
        assertEquals("launchDate cannot be null",exception.getMessage());
    }

    @DisplayName("Should throw exception when null is passed to setLaunchVehicle method")
    @Test
    public void shouldThrowExceptionWhenSetLaunchVehicleToNull()
    {
        NullPointerException exception = assertThrows(NullPointerException.class,
                ()->target.setLaunchVehicle(null));
        assertEquals("launchVehicle cannot be null",exception.getMessage());
    }

    @DisplayName("Should throw exception when null is passed to setLaunchServiceProvider method")
    @Test
    public void shouldThrowExceptionWhenSetLaunchServiceProviderToNull()
    {
        NullPointerException exception = assertThrows(NullPointerException.class,
                ()->target.setLaunchServiceProvider(null));
        assertEquals("launchServiceProvider cannot be null",exception.getMessage());
    }

    @DisplayName("Should throw exception when null is passed to setPayload method")
    @Test
    public void shouldThrowExceptionWhenSetPayloadToNull()
    {
        NullPointerException exception = assertThrows(NullPointerException.class,
                ()->target.setPayload(null));
        assertEquals("payload cannot be null",exception.getMessage());
    }

    @DisplayName("Should throw exception when empty string or string of only spaces is passed to setLaunchSite method")
    @ParameterizedTest
    @ValueSource(strings = {"","  "})
    public void shouldThrowExceptionWhenSetLaunchSiteToEmptyString(String arg)
    {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,()->target.setLaunchSite(arg));
        assertEquals("launchSite cannot be null or empty",exception.getMessage());
    }

    @DisplayName("Should throw exception when null is passed to setLaunchSite method")
    @Test
    public void shouldThrowExceptionWhenSetLaunchSiteToNull()
    {
        NullPointerException exception = assertThrows(NullPointerException.class,
                ()->target.setLaunchSite(null));
        assertEquals("launchSite cannot be null or empty",exception.getMessage());
    }

    @DisplayName("Should throw exception when empty string or string of only spaces is passed to setOrbit method")
    @ParameterizedTest
    @ValueSource(strings = {"","  "})
    public void shouldThrowExceptionWhenSetOrbitToEmptyString(String arg)
    {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,()->target.setOrbit(arg));
        assertEquals("orbit cannot be null or empty",exception.getMessage());
    }

    @DisplayName("Should throw exception when null is passed to setOrbit method")
    @Test
    public void shouldThrowExceptionWhenSetOrbitToNull()
    {
        NullPointerException exception = assertThrows(NullPointerException.class,
                ()->target.setOrbit(null));
        assertEquals("orbit cannot be null or empty",exception.getMessage());
    }

    @DisplayName("Should throw exception when empty string or string of only spaces is passed to setFunction method")
    @ParameterizedTest
    @ValueSource(strings = {"","  "})
    public void shouldThrowExceptionWhenSetFunctionToEmptyString(String arg)
    {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,()->target.setFunction(arg));
        assertEquals("function cannot be null or empty",exception.getMessage());
    }

    @DisplayName("Should throw exception when null is passed to setFunction method")
    @Test
    public void shouldThrowExceptionWhenSetFunctionToNull()
    {
        NullPointerException exception = assertThrows(NullPointerException.class,
                ()->target.setFunction(null));
        assertEquals("function cannot be null or empty",exception.getMessage());
    }

    @DisplayName("Should throw exception when null is passed to setPrice method")
    @Test
    public void shouldThrowExceptionWhenSetPriceToNull()
    {
        NullPointerException exception = assertThrows(NullPointerException.class,
                ()->target.setPrice(null));
        assertEquals("price cannot be null",exception.getMessage());
    }

    @DisplayName("Should throw exception when zero is passed to setPrice method")
    @Test
    public void shouldThrowExceptionWhenSetPriceToZero()
    {
        BigDecimal price = new BigDecimal(0);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                ()->target.setPrice(price));
        assertEquals("price cannot be zero or negative",exception.getMessage());
    }

    @DisplayName("Should throw exception when negative value is passed to setPrice method")
    @Test
    public void shouldThrowExceptionWhenSetPriceToNegative()
    {
        BigDecimal price = new BigDecimal(-1);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                ()->target.setPrice(price));
        assertEquals("price cannot be zero or negative",exception.getMessage());
    }

    @DisplayName("Should set price when positive value is passed to setPrice method")
    @Test
    public void shouldSetPriceToPositiveValue()
    {
        BigDecimal price = new BigDecimal(10);
        target.setPrice(price);
        assertEquals(target.getPrice(),price);
    }

    @DisplayName("Should throw exception when null is passed to setLaunchOutcome method")
    @Test
    public void shouldThrowExceptionWhenSetLaunchOutcomeToNull()
    {
        NullPointerException exception = assertThrows(NullPointerException.class,
                ()->target.setLaunchOutcome(null));
        assertEquals("launchOutcome cannot be null",exception.getMessage());
    }

    @DisplayName("Should return false when null is passed to equals method")
    @Test
    public void shouldReturnFalseWhenNullPassedToEquals()
    {
        assertFalse(target.equals(null));
    }

    @DisplayName("Should return false when string is passed to equals method")
    @Test
    public void shouldReturnFalseWhenStringPassedToEquals()
    {
        assertFalse(target.equals("text"));
    }

    @DisplayName("Equals method should return true when two launch objects are equal")
    @Test
    public void equalsMethodShouldReturnTrueWhenTwoLaunchObjectsAreEqual()
    {
        Rocket rocket = new Rocket("W2M","India","Antrix");
        LaunchServiceProvider serviceProvider = new LaunchServiceProvider("Antrix",1992,"India");
        String orbit = "low earth orbit";
        target.setLaunchDate(LocalDate.of(1995,9,27));
        target.setLaunchVehicle(rocket);
        target.setLaunchServiceProvider(serviceProvider);
        target.setOrbit(orbit);

        Launch launch = new Launch();
        launch.setLaunchDate(LocalDate.of(1995,9,27));
        launch.setLaunchVehicle(rocket);
        launch.setLaunchServiceProvider(serviceProvider);
        launch.setOrbit(orbit);
        assertTrue(target.equals(launch));
    }

    @DisplayName("Equals method should return false when two launch objects are not equal")
    @Test
    public void equalsMethodShouldReturnFalseWhenTwoLaunchObjectsAreNotEqual()
    {
        Rocket rocket = new Rocket("W2M","India","Antrix");
        LaunchServiceProvider serviceProvider = new LaunchServiceProvider("Antrix",1992,"India");
        String orbit = "low earth orbit";
        target.setLaunchDate(LocalDate.of(1998,9,27));
        target.setLaunchVehicle(rocket);
        target.setLaunchServiceProvider(serviceProvider);
        target.setOrbit(orbit);

        Launch launch = new Launch();
        launch.setLaunchDate(LocalDate.of(1995,9,27));
        launch.setLaunchVehicle(rocket);
        launch.setLaunchServiceProvider(serviceProvider);
        launch.setOrbit("Geostationary orbit");
        assertFalse(target.equals(launch));
    }


}
