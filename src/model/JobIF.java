package model;

import java.util.EnumSet;

import model.enums.JobFieldEnum;
import model.enums.TechnologyEnum;

public interface JobIF {

	public EnumSet<TechnologyEnum> getRequiredSkills();
	public JobFieldEnum getField();
}
