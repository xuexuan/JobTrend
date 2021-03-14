package xuexuankr.starter.com.jobtrend;

import java.util.List;

import lombok.Data;

@Data
public class PositionDetails {

	String company;
	String title;
	List<String> description;
	
	public PositionDetails(String company_, String title_, List<String> description_)
	{
		this.setCompany(company_);
		this.setTitle(title_);
		this.setDescription(description_);
	}
}
