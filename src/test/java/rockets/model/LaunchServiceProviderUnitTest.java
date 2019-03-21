package rockets.model;

import org.apache.commons.lang3.ObjectUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LaunchServiceProviderUnitTest {

    private LaunchServiceProvider target;

    @BeforeEach
    public void setUp()
    {
        target = new LaunchServiceProvider("Antrix",1992,"India");
    }
    @DisplayName("Should throw exception when empty string or string of only spaces is passed to setHeadquarters method")
    @ParameterizedTest
    @ValueSource(strings = {"","  "})
    public void shouldThrowExceptionWhenSetHeadquartersToEmptyString(String arg)
    {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,()->target.setHeadquarters(arg));
        assertEquals("headquarters cannot be null or empty",exception.getMessage());
    }

    @DisplayName("Should throw exception when null is passed to setHeadquarters method")
    @Test
    public void shouldThrowExceptionWhenSetHeadquartersToNull()
    {
        NullPointerException exception = assertThrows(NullPointerException.class,
                ()->target.setHeadquarters(null));
        assertEquals("headquarters cannot be null or empty",exception.getMessage());
    }

    @DisplayName("Should throw exception when null is passed to setRockets method")
    @Test
    public void shouldThrowExceptionWhenSetRocketsToNull()
    {
        NullPointerException exception = assertThrows(NullPointerException.class,
                ()->target.setRockets(null));
        assertEquals("rockets cannot be null",exception.getMessage());
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


    @DisplayName("Should throw exception when negative value is passed to setYearFounded method")
    @Test
    public void shouldThrowExceptionWhenSetYearFoundedToNegative()
    {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                ()->target.setYearFounded(-10));
        assertEquals("founded year cannot be negative or zero",exception.getMessage());
    }

    @DisplayName("Should throw exception when zero is passed to setYearFounded method")
    @Test
    public void shouldThrowExceptionWhenSetYearFoundedToZero()
    {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                ()->target.setYearFounded(0));
        assertEquals("founded year cannot be negative or zero",exception.getMessage());
    }


    @DisplayName("Should throw exception when empty string or string of only spaces is passed to setCountry method")
    @ParameterizedTest
    @ValueSource(strings = {"","  "})
    public void shouldThrowExceptionWhenSetCountryToEmptyString(String arg)
    {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,()->target.setCountry(arg));
        assertEquals("country cannot be null or empty",exception.getMessage());
    }

    @DisplayName("Should throw exception when null is passed to setCountry method")
    @Test
    public void shouldThrowExceptionWhenSetCountryToNull()
    {
        NullPointerException exception = assertThrows(NullPointerException.class,
                ()->target.setCountry(null));
        assertEquals("country cannot be null or empty",exception.getMessage());
    }

    @DisplayName("Should throw exception if constructor is passed with empty name")
    @Test
    public void shouldThrowExceptionWhenConstructorPassedWithEmptyName()
    {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,()->new LaunchServiceProvider(" ",1990,"India"));
        assertEquals("constructor parameters cannot be empty or null strings",exception.getMessage());
    }

    @DisplayName("Should throw exception if constructor is passed with empty country")
    @Test
    public void shouldThrowExceptionWhenConstructorPassedWithEmptyCountry()
    {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,()->new LaunchServiceProvider("W2M",1990," "));
        assertEquals("constructor parameters cannot be empty or null strings",exception.getMessage());
    }

    @DisplayName("Should throw exception if constructor is passed with null as country")
    @Test
    public void shouldThrowExceptionWhenConstructorPassedWithNullToCountry()
    {
        NullPointerException exception = assertThrows(NullPointerException.class,()->new LaunchServiceProvider("W2M",1990,null));
        assertEquals("constructor parameters cannot be empty or null strings",exception.getMessage());
    }

    @DisplayName("Should throw exception if constructor is passed with null as name")
    @Test
    public void shouldThrowExceptionWhenConstructorPassedWithNullToName()
    {
        NullPointerException exception = assertThrows(NullPointerException.class,()->new LaunchServiceProvider(null,1990,"India"));
        assertEquals("constructor parameters cannot be empty or null strings",exception.getMessage());
    }

    @DisplayName("Should throw exception if constructor is passed with zero as founded year")
    @Test
    public void shouldThrowExceptionWhenConstructorPassedWithZeroToYearFounded()
    {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,()->new LaunchServiceProvider("W2M",0,"India"));
        assertEquals("constructor parameter : yearFounded cannot be zero or negative",exception.getMessage());
    }

    @DisplayName("Should throw exception if constructor is passed with negative value as founded year")
    @Test
    public void shouldThrowExceptionWhenConstructorPassedWithNegativeToYearFounded()
    {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,()->new LaunchServiceProvider("W2M",-1990,"India"));
        assertEquals("constructor parameter : yearFounded cannot be zero or negative",exception.getMessage());
    }
}
