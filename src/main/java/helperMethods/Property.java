package helperMethods;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Property {
	
	public static String getProperty(String key){
		Properties prop=new Properties();
		String path=System.getProperty("user.dir");
		try {
			FileInputStream file=new FileInputStream(path+"\\configs\\dataStack.properties");
			prop.load(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String value=prop.getProperty(key);
		return value;
		
	}

}
