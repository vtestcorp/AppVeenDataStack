package intern;

import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import helperMethods.JsonUtils;

public class JSONHandling {

	public static void main(String[] args) throws IOException, ParseException 
	{
		String a = JsonUtils.getData("C:\\SDET\\Appveen\\AppVeenDataStack\\testData\\string_Text.json","status");
		
		System.out.println(a);    
		
		JSONArray c = JsonUtils.getArrayValues("C:\\SDET\\Appveen\\AppVeenDataStack\\testData\\string_Text.json", "definition");
		
		
		for(int i=0; i<c.size(); i++) 
		{
			
			JSONObject obj = (JSONObject) c.get(i);
			          
			
			
			System.out.println(obj.get("key"));
			
			
			
			
		}
	
	}

}
