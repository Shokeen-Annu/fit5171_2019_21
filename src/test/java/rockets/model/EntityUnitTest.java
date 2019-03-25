package rockets.model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class EntityUnitTest extends Entity{

    EntityUnitTest e;

    @BeforeEach
    public void setUp() {
        e = new EntityUnitTest();
    }

    @DisplayName("should throw exception when pass null wikilink to setWikilink function")
    @Test
    public void shouldThrowExceptionWhenSetWikiLinkToNull() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> e.setWikilink(null));
        assertEquals("Wikilink cannot be null or empty", exception.getMessage());
    }

    @DisplayName("should throw exception when pass empty wikilink to setWikilink function")
    @Test
    public void shouldThrowExceptionWhenSetWikiLinkToEmpty() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> e.setWikilink(" "));
        assertEquals("Wikilink cannot be null or empty", exception.getMessage());
    }

    @DisplayName("should throw exception when pass empty wikilink to setWikilink function")
    @Test
    public void shouldThrowExceptionWhenNegativeNumberPassedtoSetID() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> e.setId(-1));
        assertEquals("ID cannot be negative", exception.getMessage());
    }

}
