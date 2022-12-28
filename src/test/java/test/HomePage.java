package test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import base.BaseClass;
import base.ConfigManager;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import listner.TestListner;
import pageObjects.HomePagelocators;
import selenium.SafeActions;

@Listeners(TestListner.class)
@Feature("Home Page tests > login and new data creation")
public class HomePage extends BaseClass implements HomePagelocators {

	private SafeActions safeAction;
	private ConfigManager app;

	@BeforeMethod(alwaysRun = true)
	@Step("Setup the required thing to execute the scripts.")
	@Description("Setup the necessary files and data")
	public void setup() {
		safeAction = new SafeActions(driver);
		app = new ConfigManager("App");
	}

	@org.testng.annotations.Test
	@Description("Login to the Application")
	@Severity(SeverityLevel.BLOCKER)
	public void Test() {

		// open URL
		safeAction.openURL(app.getProperty("applicationUrl"));

		// Username
		safeAction.safeType(USERNAME, app.getProperty("username"), " Username ", 10);

		// Password
		safeAction.safeType(PASSWORD, app.getProperty("password"), " Password ", 10);

		// Submit
		safeAction.safeClick(SUBMIT);

		String currentTitle = driver.getTitle();

		Assert.assertEquals(currentTitle, "Insurance Broker System - Loginsdnsj");
	}
}
