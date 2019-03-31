package rockets.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class UserUnitTest {
    private User target;

    @BeforeEach
    public void setUp() {
        target = new User();
    }

    // BASIC CHECKS FOR OTHER ATTRIBUTES
    @DisplayName("should throw exception when pass a empty first name address to setFirstName function")
    @Test
    public void shouldThrowExceptionWhenSetFirstNameToEmpty() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> target.setFirstName(" "));
        assertEquals("first name cannot be null or empty", exception.getMessage());
    }

    @DisplayName("should throw exception when pass null first name to setFirstName function")
    @Test
    public void shouldThrowExceptionWhenSetFirstNameToNull() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> target.setFirstName(null));
        assertEquals("first name cannot be null or empty", exception.getMessage());
    }

    @DisplayName("should throw exception when pass a empty first name address to setFirstName function")
    @Test
    public void shouldThrowExceptionWhenSetLastNameToEmpty() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> target.setLastName(" "));
        assertEquals("last name cannot be null or empty", exception.getMessage());
    }

    @DisplayName("should throw exception when pass null first name to setFirstName function")
    @Test
    public void shouldThrowExceptionWhenSetLastNameToNull() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> target.setLastName(null));
        assertEquals("last name cannot be null or empty", exception.getMessage());
    }

    @DisplayName("should throw exception when pass a empty email address to setEmail function")
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  "})
    public void shouldThrowExceptionWhenSetEmailToEmpty(String email) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> target.setEmail(email));
        assertEquals("email cannot be null or empty", exception.getMessage());
    }

    @DisplayName("should throw exception when pass a empty password to setPassword function")
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  "})
    public void shouldThrowExceptionWhenPasswordSetToEmpty(String password) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> target.setPassword(password));
        assertEquals("password cannot be null or empty", exception.getMessage());
    }

    @DisplayName("should throw exception when pass null to setEmail function")
    @Test
    public void shouldThrowExceptionWhenSetEmailToNull() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> target.setEmail(null));
        assertEquals("email cannot be null or empty", exception.getMessage());
    }

    //tEST FOR TESTING EMAIL FORMAT
    @DisplayName("should throw exceptions when pass a null password to setPassword function")
    @Test
    public void shouldThrowExceptionWhenSetPasswordToNull() {
        NullPointerException exception = assertThrows(NullPointerException.class,
                () -> target.setPassword(null));
        assertEquals("password cannot be null or empty", exception.getMessage());
    }

    //TEST FOR TESTING PASSWORD FORMAT : MIN 8 CHARS AND SHOULD HAVE ALPHABETS AND NUMBERS
    @DisplayName("should return true when two users have the same email")
    @Test
    public void shouldReturnTrueWhenUsersHaveSameEmail() {
        String email = "abc@example.com";
        target.setEmail(email);
        User anotherUser = new User();
        anotherUser.setEmail(email);
        assertTrue(target.equals(anotherUser));
    }

    @DisplayName("should return false when two users have different emails")
    @Test
    public void shouldReturnFalseWhenUsersHaveDifferentEmails() {
        target.setEmail("abc@example.com");
        User anotherUser = new User();
        anotherUser.setEmail("def@example.com");
        assertFalse(target.equals(anotherUser));
    }

    @DisplayName("should return false when two users have different emails")
    @Test
    public void shouldReturnTrueWhenPasswordsMatch() {
        target.setPassword("abc@Asdfdd8");
        assertTrue(target.isPasswordMatch("abc@Asdfdd8"));
    }

    @DisplayName("should return false when two users have different emails")
    @Test
    public void shouldReturnFalseWhenPasswordsDoNotMatch() {
        target.setPassword("abc@Asdw7");
        assertFalse(target.isPasswordMatch("abc@Asdw8"));
    }

    @DisplayName("should throw exception when email is not in format")
    @Test
    public void shouldThrowExceptionWhenEmailIsNotInValidFormat() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> target.setEmail("pranav#gmail.com"));
        assertEquals("Email is not valid", exception.getMessage());

    }

    @DisplayName("should throw exception when password is not in valid format")
    @Test
    public void shouldThrowExceptionWhenPasswordIsNotInValidFormat() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> target.setPassword("Pranavefsefsf"));
        assertEquals("Password is not in valid format", exception.getMessage());
    }

    @DisplayName("should set Email in valid format")
    @Test
    public void shouldSetEmailInValidFormat() {
        target.setEmail("pranav123@gmail.com");
        assertEquals("pranav123@gmail.com", target.getEmail());

    }

    @DisplayName("should set password in valid format")
    @Test
    public void shouldSetPasswordInValidFormat() {
        target.setPassword("pranav123@A");
        assertEquals("pranav123@A", target.getPassword());

    }
}