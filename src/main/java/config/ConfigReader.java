package config;

import helperMethods.Property;

public class ConfigReader {
	
	
	public static String getReportConfigPath(){
		String reportConfigPath = Property.getProperty("reportConfigPath");
		if(reportConfigPath!= null) return reportConfigPath;
		else throw new RuntimeException("Report Config Path not specified in the Configuration.properties file for the Key:reportConfigPath");		
	}


}
