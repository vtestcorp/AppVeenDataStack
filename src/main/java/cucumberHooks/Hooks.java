package cucumberHooks;

import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import base.BaseClass;
import helperMethods.Screenshots;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;

public class Hooks extends BaseClass{

	
	@SuppressWarnings("deprecation")
	@After
    public void tearDown(Scenario scenario) {
		System.out.println("------------------------------------------------------------------"+scenario.getName());
    	if (scenario.isFailed()) {
//    		scenario.embed(((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES), "image/png", "Step FAILED (Screenshot):");
    		try {
				Screenshots.takeScreenshot(driver, "files");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++"+scenario.getName());
    	}
	}
	
	
	
}	

