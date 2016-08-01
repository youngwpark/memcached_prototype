package model;

import java.io.Serializable;
import java.util.EnumSet;

import model.enums.CompanyEnum;
import model.enums.ExperienceLevelEnum;
import model.enums.JobFieldEnum;
import model.enums.JobTypeEnum;
import model.enums.LocationEnum;
import model.enums.TechnologyEnum;

public class DevOps extends Job implements Serializable
{
	
	private EnumSet<TechnologyEnum> requiredSkills = EnumSet.of(TechnologyEnum.UNIX,
																TechnologyEnum.LINUX);
	
	public DevOps(String title, 
			JobTypeEnum jobType, 
			LocationEnum location, 
			CompanyEnum company,
			ExperienceLevelEnum experienceLevel) 
	{
		super(title, jobType, location, company, experienceLevel);		
	}

	@Override
	public EnumSet<TechnologyEnum> getRequiredSkills()
	{
		return requiredSkills;
	}
	
	@Override
	public JobFieldEnum getField()
	{
		return JobFieldEnum.ENGINEERING;
	}
	
	
	public String toString()
	{
		return super.toString() + ", JobField[" + getField() + "], ReqSkills[" + requiredSkills.toString() + "]";
	}
	
	
}
