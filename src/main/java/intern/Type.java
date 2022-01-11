package intern;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import helperMethods.JsonUtils;

public class Type 
{
	public static void main(String[] args)
	{
	JSONArray c = JsonUtils.getArrayValues("C:\\SDET\\Appveen\\AppVeenDataStack\\testData\\string_Text.json", "definition");
		
		
		for(int i=0; i<c.size(); i++) 
		{
			
			JSONObject obj = (JSONObject) c.get(i);
			
		
			
			System.out.println(obj.get("type"));
			
			
		}
	
	}

}
