package rockets.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;

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

    @DisplayName("should set rocket massToLEO value")
    @ParameterizedTest
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
    @DisplayName("should throw exception when set massToGTO to null")
    @Test
    public void shouldThrowExceptionWhenSetMassToGTOToNull() {

        assertThrows(NullPointerException.class, () -> rocket.setMassToGTO(null));
    }
    @DisplayName("should throw exception when set massToLEO to empty or spaces")
    @ParameterizedTest
    @ValueSource(strings = {"","  "})
    public void shouldThrowExceptionWhenSetMassToLEOToEmpty(String arg) {
        IllegalArgumentException exception=   assertThrows(IllegalArgumentException.class, () -> rocket.setMassToLEO(arg));
        assertEquals(exception.getMessage(),"massToLEO cannot be empty or spaces or null");
    }
    @DisplayName("should throw exception when set massToGTO to empty or spaces")
    @ParameterizedTest
    @ValueSource(strings = {"","  "})
    public void shouldThrowExceptionWhenSetMassToGTOToEmpty(String arg) {
        IllegalArgumentException exception=   assertThrows(IllegalArgumentException.class, () -> rocket.setMassToGTO(arg));
        assertEquals(exception.getMessage(),"massToGTO cannot be empty or spaces or null");
    }

    @DisplayName("Should return false when null is passed to equals method")
    @Test
    public void shouldReturnFalseWhenNullPassedToEquals()
    {
        assertFalse(rocket.equals(null));
    }

    @DisplayName("Should return false when string is passed to equals method")
    @Test
    public void shouldReturnFalseWhenStringPassedToEquals()
    {
        assertFalse(rocket.equals("text"));
    }

    @DisplayName("Equals method should return true when two rocket objects are equal")
    @Test
    public void equalsMethodShouldReturnTrueWhenTwoRocketObjectsAreEqual()
    {
        Rocket rocket1 = new Rocket("W2M","India",new LaunchServiceProvider("Antrix", 1992, "India"));
        Rocket rocket2 = new Rocket("W2M","India",new LaunchServiceProvider("Antrix", 1992, "India"));

        assertTrue(rocket1.equals(rocket2));
    }

    @DisplayName("Equals method should return false when two rocket objects are not equal")
    @Test
    public void equalsMethodShouldReturnFalseWhenTwoRocketObjectsAreNotEqual()
    {
        Rocket rocket1 = new Rocket("W2M","India",new LaunchServiceProvider("Antrix", 1992, "India"));
        Rocket rocket2 = new Rocket("W2M","USA",new LaunchServiceProvider("Antrix", 1992, "India"));
        assertFalse(rocket1.equals(rocket2));
    }

    @DisplayName("Integration testing of Launch service provider with Rocket class")
    @Test
    public void rocketObjectShouldContainLaunchServiceProvider()
    {
        LaunchServiceProvider provider = new LaunchServiceProvider("SpaceX", 2002, "USA");
        Rocket rocket1 = new Rocket("rocket_X", "Australia",provider);
        assertEquals(rocket1.getManufacturer(),provider);
    }
}
