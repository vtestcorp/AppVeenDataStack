package testrunner;

import java.util.stream.Stream;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="C:\\Users\\DELL\\eclipse-workspace\\ds-dev-ui-automation-framework\\Demo\\number_Number.feature",
				dryRun = false,
				glue={"stepdefinitions","cucumberHooks"},
				monochrome=true,
				strict = true,
				plugin = { "pretty", "html:report/htmlReport" 
						,"json:report/jsonReport/jsonReport.json" 
						,"cucumberHooks.CustomReportListener"
}
				)

public class Runner {
	

	  public static String[] defaultOptions = {
	            "--glue", "stepdefinitions",
	            "--strict",
	            "--plugin", "pretty",
	            "--plugin", "html:report/htmlReport",
	            "--plugin", "json:target/cucumber-reports/jsonReport.json",
	            "--plugin","cucumberHooks.CustomReportListener"

	    };
	 
		public static void main(String[] args) {
	        Stream<String> cucumberOptions = Stream.concat(Stream.of(defaultOptions), Stream.of(args));
	        io.cucumber.core.cli.Main.main(cucumberOptions.toArray(String[]::new));
	    }
}
 