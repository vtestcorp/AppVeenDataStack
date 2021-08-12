package testrunner;

import java.util.stream.Stream;
import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features="./testcases/string_Email.feature",
//				dryRun=true,
				glue="stepdefinitions",
				monochrome=true,
				plugin = { "pretty", "html:target/cucumber-reports" ,"json:target/cucumber-reports/cucumber.json" }
				)

public class Runner {
	

	  private static String[] defaultOptions = {
	            "--glue", "stepdefinitions",
	            "--plugin", "pretty",
	            "--plugin", "json:cucumber1.json"
	    };
	 
	    public static void main(String[] args) {
	        Stream<String> cucumberOptions = Stream.concat(Stream.of(defaultOptions), Stream.of(args));
	        cucumber.api.cli.Main.main(cucumberOptions.toArray(String[]::new));
	    }
}
 