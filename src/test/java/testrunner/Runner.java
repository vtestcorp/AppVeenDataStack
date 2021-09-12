package testrunner;

import java.util.stream.Stream;

//import org.junit.AfterClass;
import org.junit.runner.RunWith;

//import com.vimalselvam.cucumber.listener.Reporter;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="C:\\Users\\DELL\\eclipse-workspace\\ds-dev-ui-automation-framework\\testcases\\string_RichText.feature",
				dryRun = false,
				glue={"stepdefinitions","cucumberHooks"},
				monochrome=true,
				strict = true,
				plugin = { "pretty", "html:report" ,"json:target/cucumber-reports/cucumber.json" 
//		 ,"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" 
						,"cucumberHooks.customReportListener"
}
				)

public class Runner {

	  private static String[] defaultOptions = {
	            "--glue", "stepdefinitions",
	            "--strict",
	            "--plugin", "pretty",
	            "--plugin", "html:report/htmlReport",
	            "--plugin", "json:report/jsonReport/jsonReport.json",
//	            "--plugin", "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
	            "--plugin","cucumberHooks.customReportListener"
	           
	    };
	 
		public static void main(String[] args) {
	        Stream<String> cucumberOptions = Stream.concat(Stream.of(defaultOptions), Stream.of(args));
//	        cucumber.api.cli.Main.main(cucumberOptions.toArray(String[]::new));
	        io.cucumber.core.cli.Main.main(cucumberOptions.toArray(String[]::new));
	    }
		
		
}
 