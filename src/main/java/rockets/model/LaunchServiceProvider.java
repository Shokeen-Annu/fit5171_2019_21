package rockets.model;

import com.google.common.collect.Sets;
import com.sun.xml.internal.ws.commons.xmlutil.Converter;

import java.util.Objects;
import java.util.Set;
import static org.apache.commons.lang3.Validate.notBlank;
import static org.apache.commons.lang3.Validate.notNull;

public class LaunchServiceProvider extends Entity {
    private String name;

    private int yearFounded;

    private String country;

    private String headquarters;

    private Set<Rocket> rockets;

    public LaunchServiceProvider(String name, int yearFounded, String country) {

        notBlank(name,"constructor parameters cannot be empty or null strings");
        notBlank(country,"constructor parameters cannot be empty or null strings");
        if(yearFounded <= 0)
            throw new IllegalArgumentException("constructor parameter : yearFounded cannot be zero or negative");

        this.name = name;
        this.yearFounded = yearFounded;
        this.country = country;

        rockets = Sets.newLinkedHashSet();
    }

    public String getName() {
        return name;
    }

    public int getYearFounded() {
        return yearFounded;
    }

    public String getCountry() {
        return country;
    }

    public String getHeadquarters() {
        return headquarters;
    }

    public Set<Rocket> getRockets() {
        return rockets;
    }

    public void setName(String name){
        notBlank(name,"name cannot be null or empty");
        this.name = name;}

    public void setYearFounded(int year){
        if(year <= 0)
            throw new IllegalArgumentException("founded year cannot be negative or zero");
        else
        {
            String yearString = Integer.toString(year);
            if(yearString.length()!= 4)
                throw  new IllegalArgumentException("Year Founded should be in YYYY format");

        }
        this.yearFounded = year;
    }

    public void setCountry(String country){
        notBlank(country,"country cannot be null or empty");
        this.country = country;}

    public void setHeadquarters(String headquarters) {
        notBlank(headquarters,"headquarters cannot be null or empty");
        this.headquarters = headquarters;
    }

    public void setRockets(Set<Rocket> rockets) {
        notNull(rockets,"rockets cannot be null");
        this.rockets = rockets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LaunchServiceProvider that = (LaunchServiceProvider) o;
        return yearFounded == that.yearFounded &&
                Objects.equals(name, that.name) &&
                Objects.equals(country, that.country);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, yearFounded, country);
    }
}
