package cucumberHooks;

import java.io.IOException;

import org.junit.BeforeClass;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.BaseClass;
import helperMethods.Screenshots;
import helperMethods.WaitTypes;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.plugin.event.TestSourceRead;
import pageObjects.Object_AuthorPage;

public class Hooks extends BaseClass{
	@SuppressWarnings("deprecation")
	@After
    public void tearDown(Scenario scenario) {
    	if (scenario.isFailed()) {
    		try {
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}
	}
	
	
	
	
	
}	

