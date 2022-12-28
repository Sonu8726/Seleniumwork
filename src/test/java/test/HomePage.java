package test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import base.BaseClass;
import data.TestData;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import listner.TestListner;
import pageObjects.HomePagelocators;
import selenium.SafeActions;

@Listeners(TestListner.class)
public class HomePage extends BaseClass implements HomePagelocators {

	private SafeActions safeAction;
	private TestData testData;

	@BeforeMethod(alwaysRun = true)
	@Step("Setup the required thing to execute the scripts.")
	@Description("Setup the necessary files and data")
	public void setup() {
		safeAction = new SafeActions(driver);
		testData = new TestData();
	}

	@org.testng.annotations.Test
	@Description("Login to the Application")
	@Severity(SeverityLevel.BLOCKER)
	public void testcase1_selectARadioButton() {
		safeAction.openURL(testData.applicationUrl);
		

	}
}
