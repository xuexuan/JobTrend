package xuexuankr.starter.com.jobtrend;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class IndexPage {
	 	
		public List<PositionSummary> GetPositionSummary(String page_)
	    {
			List<PositionSummary> positionsList = new ArrayList<PositionSummary>();
	        try
	        {
				Document doc = Jsoup.connect(page_).get();
				Elements firms = doc.select("li.jobPreview.well");
				
				for (Element e: firms)
				{
					String uri = e.select("a").attr("href");
					String salary = e.select("li.salary").select("span").text();
					String location = e.select("li.location").select("span").text();
					String position = e.select("li.position").select("span").text();
					String company = e.select("li.company").select("span").text();
					String updated = e.select("li.updated").select("span").text();
					positionsList.add(new PositionSummary(uri, salary, location, position, company, updated));
				}
				
	        }
	        catch (IOException e)
	        {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        return positionsList;
	    }
		
		public List<String> IndexUri() throws IOException
		{
			List<String> indexes = new ArrayList<String>();
			String page1 = "https://www.efinancialcareers.hk/jobs-Information_Technology.s019";
			indexes.add(page1);
			
			Document doc = Jsoup.connect(page1).get();
			String nextpage = doc.select("li.current").get(0).nextElementSibling().select("a[href]").attr("href");
			for (Integer i = 2; i <200 ;i++)
			{
				String pagehead = nextpage.substring(0, nextpage.length() - 1);
				String nextpageuri = pagehead+i.toString();
				//handle fail to get the url html
				//if (Jsoup.connect(nextpageuri).get() != null)
				{
					indexes.add(nextpageuri);	
				}
				//else
				{
				//	break;
				}
				
			}
			return indexes;
		}
		
		
}
