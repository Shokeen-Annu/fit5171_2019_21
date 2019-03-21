package rockets.model;

import static org.apache.commons.lang3.Validate.notBlank;
import static org.apache.commons.lang3.Validate.notNull;

public class Payload extends Entity {

    public enum TypeOfPayload
    {
        satellite,
        spaceProbe,
        crewedSpaceCraft
    }
    private TypeOfPayload type;
    private double weight;
    private String name;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        notBlank(name,"name cannot be null or empty");
        this.name = name;
    }
    public TypeOfPayload getType()
    {
        return type;
    }

    public double getWeight()
    {
        return weight;
    }

    public void setType(TypeOfPayload type)
    {
        notNull(type,"type cannot be null");
        this.type = type;
    }

    public void setWeight(double weightOfPayload)
    {
        if(weightOfPayload > 0)
           this.weight = weightOfPayload;
        else
            throw new IllegalArgumentException("weight cannot be negative or zero");
    }
}
