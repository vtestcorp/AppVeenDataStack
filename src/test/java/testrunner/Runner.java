package testrunner;

import java.util.stream.Stream;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="./testcases/date&Time.feature",
				dryRun=false,
				glue="stepdefinitions",
				monochrome=true,
				strict = true,
				plugin = { "pretty", "html:target/cucumber-reports" ,"json:target/cucumber-reports/cucumber.json" 
						,"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
}
				)

public class Runner {
	

	  private static String[] defaultOptions = {
	            "--glue", "stepdefinitions",
	            
	            "--strict",
	            "--plugin", "pretty",
	            "--plugin", "html:target/cucumber-reports",
	            "--plugin", "json:target/cucumber-reports/jsonReport.json",
	            "--plugin", "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:Report"
	           
	    };
	 
		public static void main(String[] args) {
	        Stream<String> cucumberOptions = Stream.concat(Stream.of(defaultOptions), Stream.of(args));
//	        cucumber.api.cli.Main.main(cucumberOptions.toArray(String[]::new));
	        io.cucumber.core.cli.Main.main(cucumberOptions.toArray(String[]::new));
	    }
}
 