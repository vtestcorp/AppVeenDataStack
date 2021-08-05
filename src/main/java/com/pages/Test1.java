package com.pages;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.google.gson.JsonObject;
import com.jayway.jsonpath.JsonPath;

import helperMethods.JsonUtils;

public class Test1 {

	public static void main(String[] args) throws IOException {
		String json="{\r\n"
				+ "  \"_metadata\" : {\r\n"
				+ "    \"deleted\" : false,\r\n"
				+ "    \"lastUpdated\" : \"2021-08-05T10:33:39.445Z\",\r\n"
				+ "    \"version\" : {\r\n"
				+ "      \"release\" : \"dev\",\r\n"
				+ "      \"document\" : 1\r\n"
				+ "    },\r\n"
				+ "    \"createdAt\" : \"2021-08-05T10:33:39.445Z\"\r\n"
				+ "  },\r\n"
				+ "  \"_id\" : \"DS1002\",\r\n"
				+ "  \"dsLocation1001\" : {\r\n"
				+ "    \"geometry\" : {\r\n"
				+ "      \"type\" : \"Point\",\r\n"
				+ "      \"coordinates\" : [ 73.8567437, 18.5204303 ]\r\n"
				+ "    },\r\n"
				+ "    \"userInput\" : \"Pune, Maharashtra, India\",\r\n"
				+ "    \"town\" : \"Pune\",\r\n"
				+ "    \"district\" : \"Pune\",\r\n"
				+ "    \"state\" : \"Maharashtra\",\r\n"
				+ "    \"country\" : \"India\",\r\n"
				+ "    \"formattedAddress\" : \"Pune, Maharashtra, India\"\r\n"
				+ "  },\r\n"
				+ "  \"dsLocation1002\" : {\r\n"
				+ "    \"geometry\" : {\r\n"
				+ "      \"type\" : \"Point\",\r\n"
				+ "      \"coordinates\" : [ 73.7997094, 18.6297811 ]\r\n"
				+ "    },\r\n"
				+ "    \"userInput\" : \"Pimpri-Chinchwad, Maharashtra, India\",\r\n"
				+ "    \"town\" : \"Pimpri-Chinchwad\",\r\n"
				+ "    \"district\" : \"Pune\",\r\n"
				+ "    \"state\" : \"Maharashtra\",\r\n"
				+ "    \"country\" : \"India\",\r\n"
				+ "    \"formattedAddress\" : \"Pimpri-Chinchwad, Maharashtra, India\"\r\n"
				+ "  },\r\n"
				+ "  \"dsLocation1003\" : {\r\n"
				+ "    \"geometry\" : {\r\n"
				+ "      \"type\" : \"Point\",\r\n"
				+ "      \"coordinates\" : [ 72.8776559, 19.0759837 ]\r\n"
				+ "    },\r\n"
				+ "    \"userInput\" : \"Mumbai, Maharashtra, India\",\r\n"
				+ "    \"town\" : \"Mumbai\",\r\n"
				+ "    \"district\" : \"Mumbai\",\r\n"
				+ "    \"state\" : \"Maharashtra\",\r\n"
				+ "    \"country\" : \"India\",\r\n"
				+ "    \"formattedAddress\" : \"Mumbai, Maharashtra, India\"\r\n"
				+ "  },\r\n"
				+ "  \"dsLocation1006\" : {\r\n"
				+ "    \"geometry\" : {\r\n"
				+ "      \"type\" : \"Point\",\r\n"
				+ "      \"coordinates\" : [ 74.018261, 17.6804639 ]\r\n"
				+ "    },\r\n"
				+ "    \"userInput\" : \"Satara, Maharashtra, India\",\r\n"
				+ "    \"town\" : \"Satara\",\r\n"
				+ "    \"district\" : \"Satara\",\r\n"
				+ "    \"state\" : \"Maharashtra\",\r\n"
				+ "    \"country\" : \"India\",\r\n"
				+ "    \"formattedAddress\" : \"Satara, Maharashtra, India\"\r\n"
				+ "  },\r\n"
				+ "  \"dsLocation1007\" : {\r\n"
				+ "    \"geometry\" : {\r\n"
				+ "      \"type\" : \"Point\",\r\n"
				+ "      \"coordinates\" : [ 80.2707184, 13.0826802 ]\r\n"
				+ "    },\r\n"
				+ "    \"userInput\" : \"Chennai, Tamil Nadu, India\",\r\n"
				+ "    \"town\" : \"Chennai\",\r\n"
				+ "    \"district\" : \"Chennai\",\r\n"
				+ "    \"state\" : \"Tamil Nadu\",\r\n"
				+ "    \"country\" : \"India\",\r\n"
				+ "    \"formattedAddress\" : \"Chennai, Tamil Nadu, India\"\r\n"
				+ "  },\r\n"
				+ "  \"dsLocation1008\" : {\r\n"
				+ "    \"geometry\" : {\r\n"
				+ "      \"type\" : \"Point\",\r\n"
				+ "      \"coordinates\" : [ 74.24325270000001, 16.7049873 ]\r\n"
				+ "    },\r\n"
				+ "    \"userInput\" : \"Kolhapur, Maharashtra, India\",\r\n"
				+ "    \"town\" : \"Kolhapur\",\r\n"
				+ "    \"district\" : \"Kolhapur\",\r\n"
				+ "    \"state\" : \"Maharashtra\",\r\n"
				+ "    \"country\" : \"India\",\r\n"
				+ "    \"formattedAddress\" : \"Kolhapur, Maharashtra, India\"\r\n"
				+ "  },\r\n"
				+ "  \"dsLocation1009\" : {\r\n"
				+ "    \"geometry\" : {\r\n"
				+ "      \"type\" : \"Point\",\r\n"
				+ "      \"coordinates\" : [ 77.5945627, 12.9715987 ]\r\n"
				+ "    },\r\n"
				+ "    \"userInput\" : \"Bangalore, Karnataka, India\",\r\n"
				+ "    \"town\" : \"Bengaluru\",\r\n"
				+ "    \"district\" : \"Bangalore Urban\",\r\n"
				+ "    \"state\" : \"Karnataka\",\r\n"
				+ "    \"country\" : \"India\",\r\n"
				+ "    \"formattedAddress\" : \"Bengaluru, Karnataka, India\"\r\n"
				+ "  },\r\n"
				+ "  \"__v\" : 0\r\n"
				+ "}";
		
		
		//	String exampleRequest = FileUtils.readFileToString(new File(json), StandardCharsets.UTF_8);
		
	//	String authors = JsonPath.read(exampleRequest, "$.dsGroup1004.dsString").toString();
	//	  System.out.println( authors);
		
		JSONObject value= JsonUtils.fetchJSONObject(json);
		String v1 =  value.get("dsLocation1001").toString();
		System.out.println(v1);
		
		JSONObject value1= JsonUtils.fetchJSONObject(v1);
		String v2 = (String) value1.get("userInput");
		System.out.println(v2);
		
		String v3 = JsonPath.read(v1, "$.userInput").toString();
		System.out.println(v3);
		
		
	}

}
