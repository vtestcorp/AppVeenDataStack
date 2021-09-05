package com.pages;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.jayway.jsonpath.JsonPath;

public class Test1 {

	public static void main(String[] args) throws IOException {
		String json="{\r\n"
				+ "  \"_metadata\" : {\r\n"
				+ "    \"deleted\" : false,\r\n"
				+ "    \"lastUpdated\" : \"2021-09-02T15:04:53.359Z\",\r\n"
				+ "    \"version\" : {\r\n"
				+ "      \"release\" : \"dev\",\r\n"
				+ "      \"document\" : 1\r\n"
				+ "    },\r\n"
				+ "    \"createdAt\" : \"2021-09-02T15:04:53.359Z\"\r\n"
				+ "  },\r\n"
				+ "  \"user1\" : [ \"admin\", \"test\", \"sample\" ],\r\n"
				+ "  \"user2\" : [ \"admin1\", \"test1\", \"sample1\" ],\r\n"
				+ "  \"_id\" : \"SAM1001\",\r\n"
				+ "  \"__v\" : 0\r\n"
				+ "}";
		
		
		//	String exampleRequest = FileUtils.readFileToString(new File(json), StandardCharsets.UTF_8);
	

		
		
		
		
		String authors = JsonPath.read(json, "$.user1[0]").toString();
		  System.out.println( authors); 
	}

}
