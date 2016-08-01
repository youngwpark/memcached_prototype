package model;

import java.io.Serializable;
import java.util.EnumSet;

import model.enums.CompanyEnum;
import model.enums.ExperienceLevelEnum;
import model.enums.JobFieldEnum;
import model.enums.JobTypeEnum;
import model.enums.LocationEnum;
import model.enums.TechnologyEnum;

public class SystemsEngineer extends Job implements Serializable
{
	private EnumSet<TechnologyEnum> requiredSkills = EnumSet.of(TechnologyEnum.MICROSOFT_EXCEL,
																TechnologyEnum.MICROSOFT_POWERPOINT);
	
	public SystemsEngineer(final String title, 
						   final JobTypeEnum jobType, 
						   final LocationEnum location, 
						   final CompanyEnum company,
						   final ExperienceLevelEnum experienceLevel) 
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
}
