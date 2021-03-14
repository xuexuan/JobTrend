package xuexuankr.starter.com.jobtrend;

import lombok.Data;
import lombok.Getter;

@Getter
public class PositionSummary {
	String uri;
	String salary;
	String location;
	String position;
	String company;
	String updated;
	
	public PositionSummary(String uri_, String Salary_, String Location_, String Position_, String Company_, String Updated_)
	{
		uri = uri_;
		salary = Salary_;
		location = Location_;
		position = Position_;
		company = Company_;
		updated = Updated_;
	}
}
