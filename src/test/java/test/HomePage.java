package test;

import org.testng.annotations.BeforeMethod;
import base.BaseClass;
import base.ConfigManager;
import pageObjects.HomePagelocators;
import selenium.SafeActions;

public class HomePage extends BaseClass implements HomePagelocators {

	private SafeActions safeAction;
	private ConfigManager app;

	@BeforeMethod(alwaysRun = true)
	public void setup() {
		safeAction = new SafeActions(driver);
		app = new ConfigManager("App");
	}

	@org.testng.annotations.Test
	public void Test() {

		// open URL
		safeAction.openURL(app.getProperty("applicationUrl"));

		// Username
		safeAction.safeType(USERNAME, app.getProperty("username"), " Username ", 10);

		// Password
		safeAction.safeType(PASSWORD, app.getProperty("password"), " Password ", 10);

		// Submit
		safeAction.safeClick(SUBMIT);
	}
}
