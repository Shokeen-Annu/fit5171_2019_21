package rockets.model;

import java.util.Objects;
import java.lang.Exception;

import static org.apache.commons.lang3.Validate.notBlank;
import static org.apache.commons.lang3.Validate.notNull;

public class Rocket extends Entity {
    private String name;

    private String country;

    private String manufacturer;

    private int massToLEO;

    private String massToGTO;

    private String massToOther;

    public Rocket()
    {}

    /**
     * All parameters shouldn't be null.
     *
     * @param name
     * @param country
     * @param manufacturer
     */
    public Rocket(String name, String country, String manufacturer) {
        notNull(name,"name cannot be null");
        notBlank(name, "name cannot be empty");
        notNull(country,"country cannot be null");
        notBlank(country, "country cannot be empty");
        notNull(manufacturer,"manufacturer cannot be null");
        notBlank(manufacturer, "manufacturer cannot be empty");


        this.name = name;
        this.country = country;
        this.manufacturer = manufacturer;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public int getMassToLEO() {
        return massToLEO;
    }

    public String getMassToGTO() {
        return massToGTO;
    }

    public String getMassToOther() {
        return massToOther;
    }

    public void setMassToLEO(int massToLEO) {
        checkValueShouldNotBeNegative(massToLEO);
        this.massToLEO = massToLEO;
    }

    public void setMassToGTO(String massToGTO) {
        try {
            int mass = Integer.parseInt(massToGTO);
            this.massToGTO = massToGTO;
        }
        catch(NumberFormatException exception)
        {
            throw new NumberFormatException("Mass to GTO cannot be non numerical");
        }

    }

    public void setMassToOther(String massToOther) {

        this.massToOther = massToOther;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rocket rocket = (Rocket) o;
        return Objects.equals(name, rocket.name) &&
                Objects.equals(country, rocket.country) &&
                Objects.equals(manufacturer, rocket.manufacturer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, country, manufacturer);
    }

    @Override
    public String toString() {
        return "Rocket{" +
                "name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", massToLEO='" + massToLEO + '\'' +
                ", massToGTO='" + massToGTO + '\'' +
                ", massToOther='" + massToOther + '\'' +
                '}';
    }

    public void checkValueShouldNotBeNegative(int number)   {


        if (number < 0)
        {
            throw new IllegalArgumentException("Mass to LEO cannot be 0 or negative");

        }


    }

}
