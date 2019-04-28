package rockets.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.internal.matchers.Null;

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
    public void shouldThrowExceptionWhenSetPasswordToEmpty(String password) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> target.setPassword(password));
        assertEquals("password cannot be null or empty", exception.getMessage());
    }

    @DisplayName("should throw exception when pass null to setEmail function")
    @Test
    public void shouldThrowExceptionWhenSetEmailToNull() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> target.setEmail(null));
        assertEquals("email cannot be null or empty", exception.getMessage());
    }


    @DisplayName("should throw exceptions when pass a null password to setPassword function")
    @Test
    public void shouldThrowExceptionWhenSetPasswordToNull() {
        NullPointerException exception = assertThrows(NullPointerException.class,
                () -> target.setPassword(null));
        assertEquals("password cannot be null or empty", exception.getMessage());
    }


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
    //TEST FOR TESTING PASSWORD FORMAT : MIN 8 CHARS AND SHOULD HAVE ALPHABETS AND NUMBERS
    @DisplayName("should throw exception when password is not in valid format")
    @Test
    public void shouldThrowExceptionWhenPasswordIsNotInValidFormat() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> target.setPassword("Pranavefsefsf"));
        assertEquals("Password is not in valid format", exception.getMessage());
    }

    @DisplayName("should set Email in valid format")
    @Test
    public void shouldSetEmailWhenInputIsValidFormat() {
        target.setEmail("pranav123@gmail.com");
        assertEquals("pranav123@gmail.com", target.getEmail());

    }

    @DisplayName("should set password in valid format")
    @Test
    public void shouldSetPasswordWhenInputIsValidFormat() {
        target.setPassword("pranav123@A");
        assertEquals("pranav123@A", target.getPassword());

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

    @DisplayName("Equals method should return true when two user objects are equal")
    @Test
    public void equalsMethodShouldReturnTrueWhenTwoUserObjectsAreEqual()
    {
        User user1 = new User();
        user1.setFirstName("Pranav");
        user1.setLastName("Kumar");
        user1.setEmail("pranav123@gmail.com");
        user1.setPassword("pranav123@A");

        User user2 = new User();
        user2.setFirstName("Pranav");
        user2.setLastName("Kumar");
        user2.setEmail("pranav123@gmail.com");
        user2.setPassword("pranav123@A");

        assertTrue(user1.equals(user2));
    }

    @DisplayName("Equals method should return false when two user objects are not equal")
    @Test
    public void equalsMethodShouldReturnFalseWhenTwoUserObjectsAreNotEqual()
    {
        User user1 = new User();
        user1.setFirstName("Pranav");
        user1.setLastName("Kumar");
        user1.setEmail("pranav123@gmail.com");
        user1.setPassword("pranav123@A");

        User user2 = new User();
        user2.setFirstName("Pranav");
        user2.setLastName("Kumari");
        user2.setEmail("pranav123456@gmail.com");
        user2.setPassword("pranav123@A");

        assertFalse(user1.equals(user2));
    }

    @DisplayName("Input to isEmailValidFormat should not be null or empty or spaces")
    @ParameterizedTest
    @ValueSource(strings={"","  "})
    public void shouldReturnExceptionWhenInputIsEmptyToCheckEmailFormat(String arg)
    {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,()->target.isEmailValidFormat(arg));
        assertEquals(exception.getMessage(),"input should not be null or empty");
    }

    @DisplayName("Input to isPasswordValidFormat should not be null or empty or spaces")
    @ParameterizedTest
    @ValueSource(strings={"","  "})
    public void shouldReturnExceptionWhenInputIsEmptyToCheckPasswordFormat(String arg)
    {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,()->target.isPasswordValidFormat(arg));
        assertEquals(exception.getMessage(),"input should not be null or empty");
    }

    @DisplayName("Input to isEmailValidFormat should not be null or empty or spaces")
    @Test
    public void shouldReturnExceptionWhenInputIsNullToCheckEmailFormat()
    {
        NullPointerException exception = assertThrows(NullPointerException.class,()->target.isEmailValidFormat(null));
        assertEquals(exception.getMessage(),"input should not be null or empty");
    }

    @DisplayName("Input to isPasswordValidFormat should not be null or empty or spaces")
    @Test
    public void shouldReturnExceptionWhenInputIsNullToCheckPasswordFormat()
    {
        NullPointerException exception = assertThrows(NullPointerException.class,()->target.isPasswordValidFormat(null));
        assertEquals(exception.getMessage(),"input should not be null or empty");
    }
}