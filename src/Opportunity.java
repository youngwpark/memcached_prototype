import java.io.Serializable;

public class Opportunity implements Serializable
{
    private String name;
    private double rank;
    
    public Opportunity()
    {
    }

    public Opportunity(final String name, final double rank)
    {
	this.name = name;
	this.rank = rank;
    }

    public String getName()
    {
	return name;
    }

    public double getRank()
    {
	return rank;
    }

    public String toString()
    {
	return "Name[" + name + "], Rank[" + rank + "]";
    }
}
