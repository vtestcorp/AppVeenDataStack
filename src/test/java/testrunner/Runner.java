package testrunner;

import java.util.stream.Stream;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features="src\\test\\resources\\feature\\loginToAppCenter.feature",
//				dryRun=true,
				glue="stepdefinitions",
//				tags="@DataService",
				plugin = { "pretty", "html:target/cucumber-reports","json:target/cucumber-reports/cucumber.json" }
				)
public class Runner {
	
	public static String testData=System.getProperty("testData");
	
	
	String var="user";
	
	public static String browser=System.getProperty("browser");
	
	
//	  private static String[] defaultOptions = {
//	            "--glue", "stepdefinitions",
//	            "--plugin", "pretty",
//	            "--plugin", "json:cucumber.json"
//	    };
//	 
//	    public static void main(String[] args) {
//	        Stream<String> cucumberOptions = Stream.concat(Stream.of(defaultOptions), Stream.of(args));
//	        cucumber.api.cli.Main.main(cucumberOptions.toArray(String[]::new));
//	    }

//	public static void main(String[] args) {
//        cucumber.api.cli.Main.main(args );
//    }
//	
	
}
 