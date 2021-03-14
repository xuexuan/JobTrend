package xuexuankr.starter.com.jobtrend;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

public class PositionPage {

	public PositionDetails GetPositionDetails(String page_)
	{
		String firm = "";
		String titles = "";
		List<String> textList = new ArrayList<String>();
		 try {
				Document doc = Jsoup.connect(page_).get();
				firm = doc.select("div#JobHeaderCollapse").select("div.row.mb-0.mb-2").select("strong").text();
				
				titles = doc.select("div.font-weight-bold.mt-4").first().nextSibling().toString();
				
				List<String> texts = doc.select("div.m-md-4.jobContentFrame").select("li").eachText();
				
				Elements textnodes =  doc.select("div.mt-4.font-weight-bold").last().nextElementSiblings();
				List<String> nodestext = new ArrayList<String>();
				for(Element n: textnodes)
				{
					Node node = n.nextSibling();
					nodestext.add(node.toString());
				}
				
				if (nodestext.size() > texts.size())
				{
					for(String s : nodestext)
					{
						textList.add(s);	
					}
				}
				else
				{
					for(String s : texts)
					{
						textList.add(s);	
					}
				}
				
	        } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 
		 return new PositionDetails(firm, titles, textList);
	}
}
