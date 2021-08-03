package base;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Test {

	public static void main(String[] args) throws IOException, ParseException {
			      
		      JSONParser jsonParser = new JSONParser();
	    	  FileReader reader = new FileReader("C:\\Data-Stack\\ds-dev-ui-automation-framework\\testData\\Simple.json");
	    	 
	    	  Object object1 = jsonParser.parse(reader);
	    	  
	    	  JSONObject jsonObject = (JSONObject) object1;
	    	  
	    	  JSONArray array=(JSONArray) jsonObject.get("definition");
	    //	  System.ut.println(array.get(0));
	    	 
	    	  System.out.println(array.get(1));
	    	  
	    	  long type = (long) jsonObject.get("attributeCount");
	    	  System.out.println("Type is :" +type);
	    	  
	    	  String key = (String) jsonObject.get("key");
	    	  System.out.println("Key is :" +key);
	    	   
	    	  JSONObject obj1 = (JSONObject) array.get(1);
	    	  System.out.println(obj1);
	    	  
	    	  String val = (String) obj1.get("type");
	    	  System.out.println("Value is :" + val);
	    	  
	    	  System.out.println(obj1.get("definition"));
	    	  
	    	  JSONArray array1=(JSONArray) obj1.get("definition");
	    	  
	    	  System.out.println(array1.get(0));
	    	  
	    	  JSONObject json = (JSONObject) array1.get(0);
	    	  String type1 = (String) json.get("type");
	    	  System.out.println(type1);
	    	  
	    	  String properties = (String) jsonObject.get("properties");
	    	  System.out.println("properties is :" +properties);
	    	  
	    	  for(int j=0; j<array.size();j++) {
	    		//  System.out.println("Array size is: " + array.size());
					JSONObject object = (JSONObject) array.get(j);
    				JSONObject obj = (JSONObject) object.get("properties");
					String value = (String) obj.get("relatedSearchField");
					System.out.println( "RelatedSearchField value is" + value );
					// JSONArray array1=(JSONArray) jsonObject.get("relatedViewFields");
					 {
					 for(int i=0; i<array1.size();i++) {
			    		  System.out.println("Array size is: " + array.size());
	    	  
					 }
	    	  }
	    	  }
	}
}
	

