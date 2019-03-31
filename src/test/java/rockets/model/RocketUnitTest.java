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
