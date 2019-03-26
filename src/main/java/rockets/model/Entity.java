package rockets.model;
import static org.apache.commons.lang3.Validate.notBlank;
import static org.apache.commons.lang3.Validate.notNull;

public abstract class Entity {
    private Long id;

    private String wikilink;

    public String getWikilink() {
        return wikilink;
    }

    public void setWikilink(String wikilink)
    {
        notBlank(wikilink,"Wikilink cannot be null or empty");
        notNull(wikilink,"Wikilink cannot be null or empty");
        this.wikilink = wikilink;
    }


    public Long getId() {
        return id;
    }

    public void setId(long id) {
        checkValueShouldNotBeNegative(id);
        this.id = id;
    }

    public void checkValueShouldNotBeNegative(long number)   {
        if (number < 0) {
            throw new IllegalArgumentException("ID cannot be negative");
        }

    }
}
