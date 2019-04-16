package rockets.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class RocketUnitTest {
    private Rocket rocket;

    @BeforeEach
    public void setUp() {
        rocket = new Rocket();
    }

    @AfterEach
    public void tearDown() {
    }

    @DisplayName("should throw exception when pass a empty rocket name  to parameterized constructor ")
    @Test
    public void shouldThrowExceptionWhenEmptyNameParameterIsPassedToConstructor() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->  new Rocket(" ", "Australia", new LaunchServiceProvider("SpaceX", 2002, "USA")));
        assertEquals("name cannot be empty", exception.getMessage());
    }

    @DisplayName("should throw exception when pass a null rocket name  to parameterized constructor")
    @Test
    public void shouldThrowExceptionWhenNullNameParameterIsPassedToConstructor() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> new Rocket(null, "Australia", new LaunchServiceProvider("SpaceX", 2002, "USA")));
        assertEquals("name cannot be null", exception.getMessage());
    }

    @DisplayName("should throw exception when pass a empty country  to parameterized constructor")
    @Test
    public void shouldThrowExceptionWhenEmptyCountryParameterIsPassedToConstructor() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Rocket("rocketA", " ", new LaunchServiceProvider("SpaceX", 2002, "USA")));
        assertEquals("country cannot be empty", exception.getMessage());
    }

    @DisplayName("should throw exception when pass a null country  to parameterized constructor")
    @Test
    public void shouldThrowExceptionWhenNullCountryParameterIsPassedToConstructor() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> new Rocket("RocketB", null, new LaunchServiceProvider("SpaceX", 2002, "USA")));
        assertEquals("country cannot be null", exception.getMessage());
    }

    @DisplayName("should throw exception when pass a null country  to parameterized constructor")
    @Test
    public void shouldThrowExceptionWhenNullManufacturerParameterIsPassedToConstructor() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> new Rocket("RocketB", "INdia", null));
        assertEquals("manufacturer cannot be null", exception.getMessage());
    }

    @DisplayName("should throw exception when pass a non numerical value to setMassToGTO function")
    @Test
    public void shouldThrowExceptionWhenNonNumericalValuePassedToSetMassToGto() {

        NumberFormatException exception = assertThrows(NumberFormatException.class, () -> rocket.setMassToGTO("abcd"));
        assertEquals("Mass to GTO cannot be non numerical", exception.getMessage());

    }

    @DisplayName("should throw exception when pass a non numerical value to setMassToLEO function")
    @Test
    public void shouldThrowExceptionWhenNonNumericalValuePassedToSetMassToLeo() {

        NumberFormatException exception = assertThrows(NumberFormatException.class, () -> rocket.setMassToLEO("abcd"));
        assertEquals("Mass to LEO cannot be non numerical", exception.getMessage());

    }

    @DisplayName("should pass when a numerical value in String to setMassToGTO function")
    @Test
    public void shouldSetMassToGtoWhenNumericalValueIsPassed() {

        rocket.setMassToGTO("9500");
        assertEquals("9500",rocket.getMassToGTO());

    }

    @DisplayName("should pass  when a numerical value in string to setMassToLEO function")
    @Test
    public void shouldPassWhenNumericalValuePassedToSetMassToLeo() {

        rocket.setMassToLEO("8000");
        assertEquals("8000",rocket.getMassToLEO());
    }

    @DisplayName("should throw exception when pass a empty MassToOther to setMassToOther function")
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  "})
    public void shouldThrowExceptionWhenSetMassToOtherToEmpty(String massToOthers) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> rocket.setMassToOther(massToOthers));
        assertEquals("MassToOther cannot be null or empty", exception.getMessage());
    }

    @DisplayName("should throw exception when pass a null MassToOther to setMassToOther function")
    @Test
    public void shouldThrowExceptionWhenSetMassToOtherToNull() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> rocket.setMassToOther(null));
        assertEquals("MassToOther cannot be null or empty", exception.getMessage());
    }

    @DisplayName("should create rocket successfully when given right parameters to constructor")
    @Test
    public void shouldConstructRocketObject() {
        String name = "BFR";
        String country = "USA";
        LaunchServiceProvider manufacturer = new LaunchServiceProvider("SpaceX", 2002, "USA");
        Rocket bfr = new Rocket(name, country, manufacturer);
        assertNotNull(bfr);
    }

    @DisplayName("should throw exception when given null manufacturer to constructor")
    @Test
    public void shouldThrowExceptionWhenNoManufacturerGiven() {
        String name = "BFR";
        String country = "USA";
        assertThrows(NullPointerException.class, () -> new Rocket(name, country, null));
    }

    @DisplayName("should set rocket massToLEO value")
    @ValueSource(strings = {"10000", "15000"})
    public void shouldSetMassToLEOWhenGivenCorrectValue(String massToLEO) {
        String name = "BFR";
        String country = "USA";
        LaunchServiceProvider manufacturer = new LaunchServiceProvider("SpaceX", 2002, "USA");

        Rocket bfr = new Rocket(name, country, manufacturer);

        bfr.setMassToLEO(massToLEO);
        assertEquals(massToLEO, bfr.getMassToLEO());
    }

    @DisplayName("should throw exception when set massToLEO to null")
    @Test
    public void shouldThrowExceptionWhenSetMassToLEOToNull() {
        String name = "BFR";
        String country = "USA";
        LaunchServiceProvider manufacturer = new LaunchServiceProvider("SpaceX", 2002, "USA");
        Rocket bfr = new Rocket(name, country, manufacturer);
        assertThrows(NullPointerException.class, () -> bfr.setMassToLEO(null));
    }
}
