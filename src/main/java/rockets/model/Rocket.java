package rockets.model;

import java.util.Objects;
import static org.apache.commons.lang3.Validate.notBlank;
import static org.apache.commons.lang3.Validate.notNull;

public class Rocket extends Entity {
    private String name;

    private String country;

    private LaunchServiceProvider manufacturer;

    private String massToLEO;

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
    public Rocket(String name, String country, LaunchServiceProvider manufacturer) {
        notNull(name,"name cannot be null");
        notBlank(name, "name cannot be empty");
        notNull(country,"country cannot be null");
        notBlank(country, "country cannot be empty");
        notNull(manufacturer,"manufacturer cannot be null");


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

    public LaunchServiceProvider getManufacturer() {
        return manufacturer;
    }

    public String getMassToLEO() {
        return massToLEO;
    }

    public String getMassToGTO() {
        return massToGTO;
    }

    public String getMassToOther() {
        return massToOther;
    }

    public void setMassToLEO(String massToLEO) {
        try {
            int mass = Integer.parseInt(massToLEO);
            checkValueShouldNotBeNegative(mass);
            this.massToLEO = massToLEO;
        }
        catch(NumberFormatException exception)
        {
            throw new NumberFormatException("Mass to LEO cannot be non numerical");
        }
    }

    public void setMassToGTO(String massToGTO) {
        try {
            int mass = Integer.parseInt(massToGTO);
            checkValueShouldNotBeNegative(mass);
            this.massToGTO = massToGTO;
        }
        catch(NumberFormatException exception)
        {
            throw new NumberFormatException("Mass to GTO cannot be non numerical");
        }

    }

    public void setMassToOther(String massToOther) {

        notBlank(massToOther, "MassToOther cannot be null or empty");
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

    }


