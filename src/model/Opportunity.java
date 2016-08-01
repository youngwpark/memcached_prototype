package model;
import java.io.Serializable;

public class Opportunity implements OpportunityIF, Serializable
{
    private long id;
    private String key;
    private double rank;
    private Job job;
    
    public Opportunity()
    {
    }

    public Opportunity(final long id, 
    		final String key,
    		final double rank, 
    		final Job job)
    {
    	this.id = id;
    	this.key = key;
		this.rank = rank;
		this.job = job;
    }
    
    public long getId()
    {
    	return id;
    }
    
    public String getKey()
    {
    	return key;
    }

    public double getRank()
    {
    	return rank;
    }
    
    public Job getJob()
    {
    	return job;
    }

    @Override
    public String toString()
    {
    	return "ID[" + id + "], Key[" + key + "], Rank[" + rank + "], Job[" + job.toString() + "]";
    }
}
