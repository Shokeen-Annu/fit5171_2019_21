package rockets.model;

import java.util.Objects;
import java.util.regex.Pattern;

import static org.apache.commons.lang3.Validate.notBlank;
import static org.apache.commons.lang3.Validate.notNull;

public class User extends Entity {
    private String firstName;

    private String lastName;

    private String email;

    private String password;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        notBlank(firstName, "first name cannot be null or empty");
        notNull(firstName, "first name cannot be null or empty");
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        notBlank(lastName, "last name cannot be null or empty");
        notNull(lastName, "last name cannot be null or empty");
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        notBlank(email, "email cannot be null or empty");
            if (isEmailValidFormat(email))
                this.email = email;
            else
                throw new IllegalArgumentException("Email is not valid");
        }



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        notBlank(password, "password cannot be null or empty");

        if(isPasswordValidFormat(password))
            this.password = password;
            else
                throw new IllegalArgumentException("Password is not in valid format");
        }


    // match the given password against user's password and return the result
    public boolean isPasswordMatch(String password) {
        return this.password.equals(password.trim());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
    public static boolean isEmailValidFormat(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern p = Pattern.compile(regex);
        return p.matcher(email).matches();
    }

    public boolean isPasswordValidFormat(String pwd){
        boolean result = true;
        String num= "(.*[0-9].*)";
        String upperChars = "(.*[A-Z].*)";
        String lowerChars = "(.*[a-z].*)";
        String specialChars = "(.*[,~,!,@,#,$,%,^,&,*,(,),-,_,=,+,[,{,],},|,;,:,<,>,/,?].*$)";
        if (pwd.length() > 15 || pwd.length() < 8)
        {
            result = false;
        }
        if (!pwd.matches(upperChars))
        {
            result = false;
        }
        if (!pwd.matches(lowerChars)) {
            result = false;
        }
        if (!pwd.matches(specialChars)) {
            result = false;
        }
        if (!pwd.matches(num)) {
            result = false;
        }
        if(!pwd.matches(upperChars)){
            result = false;
        }
        return result;
    }

}
