package rockets.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RocketUnitTest {
    private Rocket rocket;

    @BeforeEach
    public void setUp() {
        rocket = new Rocket();
    }

    @DisplayName("should throw exception when pass a empty rocket name  to parameterized constructor ")
    @Test
    public void shouldThrowExceptionWhenEmptyNameParameterIsPassedToConstructor() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->  new Rocket(" ", "Australia", "ABC"));
        assertEquals("name cannot be empty", exception.getMessage());
    }

    @DisplayName("should throw exception when pass a null rocket name  to parameterized constructor")
    @Test
    public void shouldThrowExceptionWhenNullNameParameterIsPassedToConstructor() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> new Rocket(null, "Australia", "ABC"));
        assertEquals("name cannot be null", exception.getMessage());
    }

    @DisplayName("should throw exception when pass a empty country  to parameterized constructor")
    @Test
    public void shouldThrowExceptionWhenEmptyCountryParameterIsPassedToConstructor() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Rocket("rocketA", " ", "ABC"));
        assertEquals("country cannot be empty", exception.getMessage());
    }

    @DisplayName("should throw exception when pass a null country  to parameterized constructor")
    @Test
    public void shouldThrowExceptionWhenNullCountryParameterIsPassedToConstructor() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> new Rocket("RocketB", null, "ABC"));
        assertEquals("country cannot be null", exception.getMessage());
    }

    @DisplayName("should throw exception when pass a empty manufacturer to parameterized constructor")
    @Test
    public void shouldThrowExceptionWhenEmptyManufacturerParameterIsPassedToConstructor() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Rocket("rocketA", "India", " "));
        assertEquals("manufacturer cannot be empty", exception.getMessage());
    }

    @DisplayName("should throw exception when pass a null country  to parameterized constructor")
    @Test
    public void shouldThrowExceptionWhenNullManufacturerParameterIsPassedToConstructor() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> new Rocket("RocketB", "INdia", null));
        assertEquals("manufacturer cannot be null", exception.getMessage());
    }

    @Test
    public void shouldThrowExceptionWhenNegativeNumberPassed() {

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> rocket.setMassToLEO(-1));
        assertEquals("Mass to LEO cannot be 0 or negative", exception.getMessage());

    }
    @Test
    public void shouldThrowExceptionWhenNonNumericalValuePassed() {

        NumberFormatException exception = assertThrows(NumberFormatException.class, () -> rocket.setMassToGTO("abcd"));
        assertEquals("Mass to GTO cannot be non numerical", exception.getMessage());

    }
    @DisplayName("should throw exception when pass a empty MassToOther to setMassToOther function")
    @Test
    public void shouldThrowExceptionWhenSetMassToOtherToEmpty() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> rocket.setMassToOther(" "));
        assertEquals("MassToOther cannot be empty", exception.getMessage());
    }

    @DisplayName("should throw exception when pass a null MassToOther to setMassToOther function")
    @Test
    public void shouldThrowExceptionWhenSetMassToOtherToNull() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> rocket.setMassToOther(null));
        assertEquals("MassToOther cannot be null", exception.getMessage());
    }


}
