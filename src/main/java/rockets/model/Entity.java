package rockets.model;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.Property;

public abstract class Entity {
    @Id
    @GeneratedValue
    protected Long id;

    @Property(name = "wikilink")
    private String wikilink;

    public Entity() {
    }

    public String getWikilink() {
        return wikilink;
    }

    public void setWikilink(String wikilink) {
        this.wikilink = wikilink;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void checkValueShouldNotBeNegative(long number)   {
        if (number < 0) {
            throw new IllegalArgumentException("ID cannot be negative");
        }

    }

    public void validateYear(int year)
    {
        if(year <= 0)
            throw new IllegalArgumentException("year cannot be negative or zero");
        else
        {
            String yearString = Integer.toString(year);
            if(yearString.length()!= 4)
                throw  new IllegalArgumentException("Year should be in YYYY format");

        }
    }
}
