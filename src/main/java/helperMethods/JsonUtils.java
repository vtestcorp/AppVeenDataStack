package helperMethods;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import base.BaseClass;

public class JsonUtils extends BaseClass{

	private static FileWriter file;
	private static FileReader reader;
	private static JSONParser jsonParser;
	private static JSONObject jsonObject;
	private static Object object;
	private static JSONArray jsonArray;

	public static String getData(String filePath, String field) {
		try {
			reader = new FileReader(filePath);
			jsonParser = new JSONParser();
			object = jsonParser.parse(reader);
			jsonObject = (JSONObject) object;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (String) jsonObject.get(field);
	}

	public static void setData(String key, String value, String inputFile) {
		try {
			jsonParser = new JSONParser();
			object = jsonParser.parse(new FileReader(inputFile));
			jsonObject = (JSONObject) object;

			jsonObject.put(key, value);

			file = new FileWriter(inputFile);
			file.write(jsonObject.toJSONString());
			file.flush();
			file.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static JSONArray getArrayValues(String filePath, String field) {
		try {
			reader = new FileReader(filePath);
			jsonParser = new JSONParser();
			object = jsonParser.parse(reader);
			jsonObject = (JSONObject) object;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (JSONArray) jsonObject.get(field);
	}
	
	public static JSONArray getJSONArrayValues(String filePath, String field) {
		try {
			reader = new FileReader(filePath);
			jsonParser = new JSONParser();
			object = jsonParser.parse(reader);
			jsonObject = (JSONObject) object;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (JSONArray) jsonObject.get(field);
	}
	
	
	public static JSONArray getJSONArray(String filePath) {
		try {
			reader = new FileReader(filePath);
			jsonParser = new JSONParser();
			object = jsonParser.parse(reader);
		} catch (Exception e) {
			e.printStackTrace();
		}
		JSONArray a = (JSONArray) object;
		return a;
	}
	
	public static JSONArray fetchJSONArray(String jsonArray) {
		try {
			jsonParser = new JSONParser();
			object = jsonParser.parse(jsonArray);
		} catch (Exception e) {
			e.printStackTrace();
		}
		JSONArray a = (JSONArray) object;
		return a;
	}
	
	public static JSONObject fetchJSONObject(String jsonObject) {
		try {
			jsonParser = new JSONParser();
			object = jsonParser.parse(jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		JSONObject a = (JSONObject) object;
		return a;
	}

	public static JSONObject getJSONObject(String filePath) {
		try {
			reader = new FileReader(filePath);
			jsonParser = new JSONParser();
			object = jsonParser.parse(reader);
			jsonObject = (JSONObject) object;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject;

	}
	
	 /**
     *This method will convert json file into map
     */
	
	public static Map getMapFromJSON(String jsonFile) {
		Map<String, String> carMap = null;

		ObjectMapper mapper = new ObjectMapper();
       
        try {
             carMap = mapper.readValue(
            		 jsonFile, new TypeReference<Map<String, String>>() {
            });
	}
        catch (Exception e) {
            e.printStackTrace();
        }
		return carMap;
		
	}
	
	
	public static String getJsonValue(String filePath,String jsonPath) {
		String exampleRequest = null;
		
	try {
		exampleRequest = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
	} catch (IOException e) {
		e.printStackTrace();
	}
		
		String authors = JsonPath.read(exampleRequest, "$."+jsonPath+"").toString();
		
		return authors;

	}
}