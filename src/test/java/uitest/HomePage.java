package uitest;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.BaseClass;
import data.TestData;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import listner.TestListner;
import pageObjects.HomePagelocators;
import selenium.SafeActions;

@Listeners(TestListner.class)
public class HomePage extends BaseClass implements HomePagelocators {

	private SafeActions safeAction;
	private TestData testData;

	@BeforeMethod(alwaysRun = true)
	public void setup() {
		safeAction = new SafeActions(driver);
		testData = new TestData();
	}

	@Test
	@Description("Login to the Application")
	@Severity(SeverityLevel.BLOCKER)
	public void testcase1_selectARadioButton() {
		safeAction.openURL(testData.applicationUrl);

		safeAction.safeValidateThePageTitle("Practice Page");

		safeAction.isElementPresent();

		safeAction.safeClickUsingActionsClass(By.id("opentab"), "Open Tab ", 5);
	}
}
