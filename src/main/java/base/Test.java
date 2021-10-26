package base;

import java.io.IOException;

import com.jayway.jsonpath.JsonPath;

import helperMethods.ExcelUtils;
import helperMethods.JsonUtils;

public class Test {

	public static void main(String[] args) throws IOException {
		
		
		String s="{\r\n"
				+ "  \"_metadata\": {\r\n"
				+ "    \"deleted\": false,\r\n"
				+ "    \"lastUpdated\": \"2021-10-07T17:19:20.416Z\",\r\n"
				+ "    \"version\": {\r\n"
				+ "      \"release\": \"dev\",\r\n"
				+ "      \"document\": 1\r\n"
				+ "    },\r\n"
				+ "    \"createdAt\": \"2021-10-07T17:19:20.415Z\"\r\n"
				+ "  },\r\n"
				+ "  \"cText1001\": [\r\n"
				+ "    \"Test 1001\",\r\n"
				+ "    \"Test 1002\",\r\n"
				+ "    \"Test 1003\",\r\n"
				+ "    \"Test 1004\"\r\n"
				+ "  ],\r\n"
				+ "  \"cText1002\": [\r\n"
				+ "    \"Test 2001\",\r\n"
				+ "    \"Test 2002\"\r\n"
				+ "  ],\r\n"
				+ "  \"cNumber1003\": [\r\n"
				+ "    1001,\r\n"
				+ "    1002,\r\n"
				+ "    1003,\r\n"
				+ "    1004\r\n"
				+ "  ],\r\n"
				+ "  \"cNumber1004\": [\r\n"
				+ "    2001,\r\n"
				+ "    2002\r\n"
				+ "  ],\r\n"
				+ "  \"cNumber1005\": [\r\n"
				+ "    3002\r\n"
				+ "  ],\r\n"
				+ "  \"cBoolean1006\": [\r\n"
				+ "    true,\r\n"
				+ "    false,\r\n"
				+ "    true\r\n"
				+ "  ],\r\n"
				+ "  \"_id\": \"COL1002\",\r\n"
				+ "  \"cLocation1007\": [\r\n"
				+ "    {\r\n"
				+ "      \"geometry\": {\r\n"
				+ "        \"type\": \"Point\",\r\n"
				+ "        \"coordinates\": [\r\n"
				+ "          88.36389500000001,\r\n"
				+ "          22.572646\r\n"
				+ "        ]\r\n"
				+ "      },\r\n"
				+ "      \"_id\": \"615f2c18af493ff9de14b78d\",\r\n"
				+ "      \"userInput\": \"Kolkata, West Bengal, India\",\r\n"
				+ "      \"formattedAddress\": \"Kolkata, West Bengal, India\",\r\n"
				+ "      \"town\": \"Kolkata\",\r\n"
				+ "      \"state\": \"West Bengal\",\r\n"
				+ "      \"country\": \"India\"\r\n"
				+ "    }\r\n"
				+ "  ],\r\n"
				+ "  \"__v\": 0\r\n"
				+ "}";

		
//		String authors = JsonPath.read(s, "$.cText1001.0").toString();
		
//		System.out.println(authors);
		
		String q="cText1001.0";       //output = cText1001[0]
		
		String qw=q.split("[.]")[0]+"["+q.split("[.]")[1]+"]";
//		System.out.println(qw);
		String v="C:\\Users\\DELL\\OneDrive\\Desktop\\ExcelHandling.xlsx";
		System.out.println(ExcelUtils.getExcelCellValue(v, "Name"));
		System.out.println(ExcelUtils.getExcelCellValue(v, "Compny"));
		System.out.println(ExcelUtils.getExcelCellValue(v, "Position"));

		ExcelUtils.readExcel("C:\\Users\\DELL\\OneDrive\\Desktop", "ExcelHandling.xlsx", "Sheet1");
		
	}

}
