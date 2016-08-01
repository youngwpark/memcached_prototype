package model;

import java.io.Serializable;
import java.util.EnumSet;

import model.enums.CompanyEnum;
import model.enums.ExperienceLevelEnum;
import model.enums.JobFieldEnum;
import model.enums.JobTypeEnum;
import model.enums.LocationEnum;
import model.enums.TechnologyEnum;

public abstract class Job implements JobIF, Serializable
{
	public String title;
	public JobTypeEnum jobType;
	public LocationEnum location;
	public CompanyEnum company;
	public ExperienceLevelEnum experienceLevel;
	
	public Job(final String title,
			final JobTypeEnum jobType,
			final LocationEnum location,
			final CompanyEnum company,
			final ExperienceLevelEnum experienceLevel)
	{
		this.title = title;
		this.jobType = jobType;
		this.location = location;
		this.company = company;
		this.experienceLevel = experienceLevel;
	}
	
	public abstract EnumSet<TechnologyEnum> getRequiredSkills();
	public abstract JobFieldEnum getField();
	
	public String getTitle() 
	{
		return title;
	}

	public JobTypeEnum getJobType() 
	{
		return jobType;
	}

	public LocationEnum getLocation() 
	{
		return location;
	}

	public CompanyEnum getCompany() 
	{
		return company;
	}

	public ExperienceLevelEnum getExperienceLevel() 
	{
		return experienceLevel;
	}

	public String toString()
	{
		StringBuilder str = new StringBuilder();
		str.append("Title[" + title + "]");
		str.append(", ");
		str.append("JobType[" + jobType + "]");
		str.append(", ");
		str.append("Location[" + location + "]");
		str.append(", ");
		str.append("Company[" + company + "]");
		str.append(", ");
		str.append("ExperienceLevel[" + experienceLevel + "]");
		str.append(", ");
		return str.toString();
	}
}
