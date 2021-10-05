package cucumberHooks;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.gherkin.model.Feature;
import com.aventstack.extentreports.gherkin.model.Given;
import com.aventstack.extentreports.gherkin.model.Scenario;
//import com.aventstack.extentreports.gherkin.model.Scenario;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import base.BaseClass;
import io.cucumber.plugin.EventListener;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.PickleStepTestStep;
import io.cucumber.plugin.event.TestCaseStarted;
import io.cucumber.plugin.event.TestRunFinished;
import io.cucumber.plugin.event.TestRunStarted;
import io.cucumber.plugin.event.TestSourceRead;
import io.cucumber.plugin.event.TestStepFinished;
import io.cucumber.plugin.event.TestStepStarted;
import listeners.ExtentReportListener;
import io.cucumber.plugin.event.HookTestStep;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CustomReportListener extends BaseClass implements EventListener {
//	private ExtentSparkReporter spark;
//	private ExtentReports extent;
	Map<String, ExtentReports> feature = new HashMap<String, ExtentReports>();
	ExtentTest scenario;
	ExtentTest step;
	String keyword;

	public CustomReportListener() {
	};

	@Override
	public void setEventPublisher(EventPublisher publisher) {
// TODO Auto-generated method stub
		/*
		 * :: is method reference , so this::collecTag means collectTags method in
		 * 'this' instance. Here we says runStarted method accepts or listens to
		 * TestRunStarted event type
		 */
		
		publisher.registerHandlerFor(TestRunStarted.class, this::runStarted);
		publisher.registerHandlerFor(TestRunFinished.class, this::runFinished);
		publisher.registerHandlerFor(TestSourceRead.class, this::featureRead);
		publisher.registerHandlerFor(TestCaseStarted.class, this::ScenarioStarted);
		publisher.registerHandlerFor(TestStepStarted.class, this::stepStarted);
		publisher.registerHandlerFor(TestStepFinished.class, this::stepFinished);
	};

	/*
	 * Here we set argument type as TestRunStarted if you set anything else then the
	 * corresponding register shows error as it doesn't have a listner method that
	 * accepts the type specified in TestRunStarted.class
	 */
// Here we create the reporter
	
	private void runStarted(TestRunStarted event) {
//		spark = new ExtentSparkReporter("./ExtentReportResults.html");
//		extent = new ExtentReports();
//		spark.config().setTheme(Theme.DARK);
//// Create extent report instance with spark reporter
//		extent.attachReporter(spark);

	};

// TestRunFinished event is triggered when all feature file executions are
// completed
	private void runFinished(TestRunFinished event) {
		//extent.flush();
		
		for(ExtentReports f : feature.values()) {
			f.flush();
		}
	};

// This event is triggered when feature file is read
// here we create the feature node
	public void featureRead(TestSourceRead event) {
		String featureSource = event.getUri().toString();
		String featureName1 = featureSource.split(".*/")[1]; //location.feature
		 featureName = featureName1.replace(".feature", "");
		 extent= ExtentReportListener.setUp(featureName);
		if (feature.get(featureSource) == null) {
			//feature.putIfAbsent(featureSource, extent.createTest(featureName));
			feature.putIfAbsent(featureName, extent);
		}
	};

// This event is triggered when Test Case is started
// here we create the scenario node
	private void ScenarioStarted(TestCaseStarted event) {

		String featureSource = event.getTestCase().getUri().toString();
		String featureName12 = featureSource.split(".*/")[1];
		String featureName1 = featureName12.replace(".feature", "");
		//scenario = feature.get(featureName).createNode(event.getTestCase().getName());
//		test=feature.get(featureName).createTest(featureName);
//		scenario = feature.get(featureName1).createTest(featureName1).createNode(event.getTestCase().getName());
		scenario = feature.get(featureName1).createTest(event.getTestCase().getName()).createNode(event.getTestCase().getName()) ;

	};

// step started event
// here we creates the test node
	private void stepStarted(TestStepStarted event) {
		String stepName = " ";
		keyword = "Triggered the hook :";
// We checks whether the event is from a hook or step
		if (event.getTestStep() instanceof PickleStepTestStep) {
// TestStepStarted event implements PickleStepTestStep interface
// WHich have additional methods to interact with the event object
// So we have to cast TestCase object to get those methods
			PickleStepTestStep steps = (PickleStepTestStep) event.getTestStep();
			stepName = steps.getStep().getText();
			keyword = steps.getStep().getKeyWord();
		} else {
// Same with HoojTestStep
//			HookTestStep hoo = (HookTestStep) event.getTestStep();
//			stepName = hoo.getHookType().name();
		}
		if(!keyword.equals("Triggered the hook :")) {
		step = scenario.createNode(Given.class, keyword + " " + stepName);
		}
	};

// This is triggered when TestStep is finished
	private void stepFinished(TestStepFinished event) {
		if(!keyword.equals("Triggered the hook :")) {
		if (event.getResult().getStatus().toString() == "PASSED") {
			step.log(Status.PASS, "This passed");
		} else if (event.getResult().getStatus().toString() == "SKIPPED") {
			step.log(Status.SKIP, "This step was skipped ");
		} else {
			step.log(Status.FAIL, "This failed");
			try {
				if(errorMessage.length()>1) {
				step.error(errorMessage);
				}
				step.addScreenCaptureFromPath(ExtentReportListener.captureScreenShot(driver));
			} catch (Exception e) {
//				e.printStackTrace();
			}
		}
		}
	};
}