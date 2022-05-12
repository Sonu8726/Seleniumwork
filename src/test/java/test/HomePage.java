package test;

import org.testng.annotations.BeforeMethod;
import base.BaseClass;
import base.ConfigManager;
import selenium.SafeActions;

public class HomePage extends BaseClass {

	private SafeActions safeAction;
	private ConfigManager app;

	@BeforeMethod(alwaysRun = true)
	public void setup() {
		safeAction = new SafeActions(driver);
		app = new ConfigManager("App");
	}

	@org.testng.annotations.Test
	public void Test() {

		safeAction.openURL(app.getProperty("applicationUrl"));

	}
}
