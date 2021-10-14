package base;

import com.jayway.jsonpath.JsonPath;

import helperMethods.JsonUtils;

public class Test {

	public static void main(String[] args) {
     String file = JsonUtils.getJsonValue("C:\\data-Stack\\ds-dev-ui-automation-framework\\testData\\stringStateModel.data.json", "dob.utc");
     	System.out.println(file);
     String path ="{\r\n"
     		+ "  \"_metadata\": {\r\n"
     		+ "    \"deleted\": false,\r\n"
     		+ "    \"lastUpdated\": \"2021-10-12T07:49:07.633Z\",\r\n"
     		+ "    \"version\": {\r\n"
     		+ "      \"release\": \"1.1.2\",\r\n"
     		+ "      \"document\": 1\r\n"
     		+ "    },\r\n"
     		+ "    \"createdAt\": \"2021-10-12T07:49:07.633Z\"\r\n"
     		+ "  },\r\n"
     		+ "  \"_id\": \"STR1001\",\r\n"
     		+ "  \"name\": \"Ram\",\r\n"
     		+ "  \"dob\": {\r\n"
     		+ "    \"rawData\": \"1995-01-11T00:00:00Z\",\r\n"
     		+ "    \"tzData\": \"1995-01-11T00:00:00Z\",\r\n"
     		+ "    \"tzInfo\": \"Zulu\",\r\n"
     		+ "    \"utc\": \"1995-01-11T00:00:00.000Z\",\r\n"
     		+ "    \"unix\": 789782400000\r\n"
     		+ "  },\r\n"
     		+ "  \"salary\": 100000,\r\n"
     		+ "  \"onboardingStatus\": \"Approved\",\r\n"
     		+ "  \"__v\": 0\r\n"
     		+ "}\r\n"
     		+ "}";
     
     String text =JsonUtils.getJsonValue("C:\\data-Stack\\ds-dev-ui-automation-framework\\testData\\string_Text.json", "_metadata.version.release");
     System.out.println(text);
     
     String ds = JsonUtils.getJsonValue("C:\\data-Stack\\ds-dev-ui-automation-framework\\testData\\string_Text.json", "workflowHooks.postHooks.submit[0].version.release");
     System.out.println(ds);
     
     
     String Calender = JsonPath.read(path,"$.dob.rawData").toString(); //1995-01-11T00:00:00Z
     System.out.println(Calender);
     
     Calender = "1995-01-11T10:20:30Z";
     String[] part = Calender.split("T");
     System.out.println(part[0] +"_____________"+part[1]);
     String year =  part[0].split("-")[0];
     System.out.println(year);
     String month = part[0].split("-")[1];
     System.out.println(month);
     String date = part[0].split("-")[2];
     System.out.println(date);
     
     
     String time = part[1]; //10:20:30Z
     System.out.println(time);
     String hour = time.split(":")[0];
     System.out.println(hour);
     String min = time.split(":")[1];
     System.out.println(min);
     String sec = time.split(":")[2].replace("Z", "");
     System.out.println(sec);
     
     
     
     
     
     
	}

}
