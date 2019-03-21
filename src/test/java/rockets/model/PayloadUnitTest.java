package rockets.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PayloadUnitTest {

    private Payload target;

    @BeforeEach
    public void setUp()
    {
        target = new Payload();
    }

    @DisplayName("Should throw exception when empty string or string of only spaces is passed to setName method")
    @ParameterizedTest
    @ValueSource(strings = {"","  "})
    public void shouldThrowExceptionWhenSetNameToEmptyString(String arg)
    {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,()->target.setName(arg));
        assertEquals("name cannot be null or empty",exception.getMessage());
    }

    @DisplayName("Should throw exception when null is passed to setName method")
    @Test
    public void shouldThrowExceptionWhenSetNameToNull()
    {
        NullPointerException exception = assertThrows(NullPointerException.class,
                ()->target.setName(null));
        assertEquals("name cannot be null or empty",exception.getMessage());
    }

    @DisplayName("Should throw exception when null is passed to setType method")
    @Test
    public void shouldThrowExceptionWhenSetTypeToNull()
    {
        NullPointerException exception = assertThrows(NullPointerException.class,
                ()->target.setType(null));
        assertEquals("type cannot be null",exception.getMessage());
    }

    @DisplayName("Should throw exception when negative value is passed to setWeight method")
    @Test
    public void shouldThrowExceptionWhenSetWeightToNegative()
    {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                ()->target.setWeight(-1.1));
        assertEquals("weight cannot be negative or zero",exception.getMessage());
    }

    @DisplayName("Should throw exception when zero is passed to setWeight method")
    @Test
    public void shouldThrowExceptionWhenSetWeightToZero()
    {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                ()->target.setWeight(0));
        assertEquals("weight cannot be negative or zero",exception.getMessage());
    }
}
