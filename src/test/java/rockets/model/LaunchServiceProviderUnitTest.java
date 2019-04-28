package rockets.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

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
                ()->target.setYearFounded(-1));
        assertEquals("year cannot be negative or zero",exception.getMessage());
    }

    @DisplayName("Should throw exception when zero is passed to setYearFounded method")
    @Test
    public void shouldThrowExceptionWhenSetYearFoundedToZero()
    {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                ()->target.setYearFounded(0));
        assertEquals("year cannot be negative or zero",exception.getMessage());
    }

    @DisplayName("Should throw exception when setYearFounded is not in YYYY format")
    @Test
    public void shouldThrowExceptionWhenSetYearFoundedWrongFormat()
    {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,()-> target.setYearFounded(89));
        assertEquals("Year should be in YYYY format",exception.getMessage());
    }

    @DisplayName("Should set Year Founded in YYYY format with correct input")
    @Test
    public void shouldSetYearFoundedWithProperFormat()
    {
        target.setYearFounded(1990);
        assertEquals(1990,target.getYearFounded());
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
        assertEquals("year cannot be negative or zero",exception.getMessage());
    }

    @DisplayName("Should throw exception if constructor is passed with negative value as founded year")
    @Test
    public void shouldThrowExceptionWhenConstructorPassedWithNegativeToYearFounded()
    {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,()->new LaunchServiceProvider("W2M",-1990,"India"));
        assertEquals("year cannot be negative or zero",exception.getMessage());
    }

    @DisplayName("Should throw exception if constructor is passed with wrong format as founded year")
    @Test
    public void shouldThrowExceptionWhenConstructorPassedWithYearFoundedInWrongFormat()
    {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,()->new LaunchServiceProvider("W2M",89,"India"));
        assertEquals("Year should be in YYYY format",exception.getMessage());
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

    @DisplayName("Equals method should return true when two launch service provider objects are equal")
    @Test
    public void equalsMethodShouldReturnTrueWhenTwoLaunchServiceProviderObjectsAreEqual()
    {
        LaunchServiceProvider serviceProvider1 = new LaunchServiceProvider("Antrix",1992,"India");
        LaunchServiceProvider serviceProvider2 = new LaunchServiceProvider("Antrix",1992,"India");
        assertTrue(serviceProvider1.equals(serviceProvider2));
    }

    @DisplayName("Equals method should return false when two launch service provider objects are not equal")
    @Test
    public void equalsMethodShouldReturnFalseWhenTwoLaunchServiceProviderObjectsAreNotEqual()
    {
        LaunchServiceProvider serviceProvider1 = new LaunchServiceProvider("Antrix",1992,"India");
        LaunchServiceProvider serviceProvider2 = new LaunchServiceProvider("Nasa",1992,"India");

        assertFalse(serviceProvider1.equals(serviceProvider2));
    }

    @DisplayName("Integration testing for rockets attribute")
    @Test
    public void shouldContainRocketObjectWhenAddedToLaunchServiceProviderObject()
    {
        Set<Rocket> rockets = new HashSet<Rocket>();
        String name = "BFR";
        String country = "USA";
        LaunchServiceProvider manufacturer = new LaunchServiceProvider("SpaceX", 2002, "USA");
        Rocket rocket = new Rocket(name, country, manufacturer);
        rockets.add(rocket);
        target.setRockets(rockets);

        assertEquals(target.getRockets(),rockets);
        assertEquals(target.getRockets().size(),rockets.size());
    }


    @DisplayName("Integration testing for revenue attribute")
    @Test
    public void shouldContainLspRevenueObjectWhenAddedToLaunchServiceProviderObject()
    {
        ArrayList<LspRevenue> revenueList = new ArrayList<LspRevenue>();
        LspRevenue revenue = new LspRevenue(2017,new BigDecimal(3000.43),target);
        revenueList.add(revenue);

        target.setRevenue(revenueList);

        assertEquals(target.getRevenue(),revenueList);
        assertEquals(target.getRevenue().size(),revenueList.size());
        assertEquals(revenueList.get(0).getLsp(),target);
    }
}
