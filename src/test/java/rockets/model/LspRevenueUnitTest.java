package rockets.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class LspRevenueUnitTest {

    private LspRevenue target;

    @BeforeEach
    public void setUp()
    {
        target = new LspRevenue(2017,new BigDecimal(2.33),new LaunchServiceProvider("SpaceX",2002,"USA"));
    }

    @DisplayName("Should throw exception when null is passed to setLsp method")
    @Test
    public void shouldThrowExceptionWhenSetLspToNull()
    {
        NullPointerException exception = assertThrows(NullPointerException.class,()->target.setLsp(null));
        assertEquals("Launch service provider cannot be null",exception.getMessage());
    }

    @DisplayName("Should throw exception when null is passed to AddRevenue method")
    @Test
    public void shouldThrowExceptionWhenAddRevenueToNull()
    {
        NullPointerException exception = assertThrows(NullPointerException.class,()->target.addRevenue(null));
        assertEquals("Revenue cannot be null",exception.getMessage());
    }

    @DisplayName("Should throw exception when negative value is passed to setYear method")
    @Test
    public void shouldThrowExceptionWhenSetYearToNegative()
    {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                ()->target.setYear(-1));
        assertEquals("year cannot be negative or zero",exception.getMessage());
    }

    @DisplayName("Should throw exception when zero is passed to setYear method")
    @Test
    public void shouldThrowExceptionWhenSetYearToZero()
    {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                ()->target.setYear(0));
        assertEquals("year cannot be negative or zero",exception.getMessage());
    }

    @DisplayName("Should throw exception when setYear is not in YYYY format")
    @Test
    public void shouldThrowExceptionWhenSetYearFoundedWrongFormat()
    {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,()-> target.setYear(89));
        assertEquals("Year should be in YYYY format",exception.getMessage());
    }

    @DisplayName("Should set Year in YYYY format with correct input")
    @Test
    public void shouldSetYearWithProperFormat()
    {
        target.setYear(1990);
        assertEquals(1990,target.getYear());
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

    @DisplayName("Equals method should return true when two LspRevenue objects are equal")
    @Test
    public void equalsMethodShouldReturnTrueWhenTwoLspRevenueAreEqual()
    {
        LaunchServiceProvider serviceProvider = new LaunchServiceProvider("Antrix",1992,"India");
        LspRevenue revenue1 = new LspRevenue(2017,new BigDecimal(2.33),serviceProvider);
        LspRevenue revenue2 = new LspRevenue(2017,new BigDecimal(2.33),serviceProvider);
        assertTrue(revenue1.equals(revenue2));
    }

    @DisplayName("Equals method should return false when two LspRevenue are not equal")
    @Test
    public void equalsMethodShouldReturnFalseWhenTwoLspRevenueAreNotEqual()
    {
        LaunchServiceProvider serviceProvider = new LaunchServiceProvider("Antrix",1992,"India");
        LspRevenue revenue1 = new LspRevenue(2017,new BigDecimal(2.33),serviceProvider);
        LspRevenue revenue2 = new LspRevenue(2018,new BigDecimal(2.33),serviceProvider);
        assertFalse(revenue1.equals(revenue2));
    }
}
