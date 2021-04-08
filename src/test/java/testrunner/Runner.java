package testrunner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features="src\\test\\resources\\feature\\login.feature",
//				dryRun=true,
				glue="stepdefinations",
				tags="@DataService",
				plugin = { "pretty", "html:target/cucumber-reports","json:target/cucumber-reports/cucumber.json" })
public class Runner {

}
