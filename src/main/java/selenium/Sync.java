package selenium;

import org.openqa.selenium.WebDriver;

import base.BaseClass;

public class Sync extends BaseClass {

	private WebDriver driver;

	public Sync(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isElementPresent() {
		return true;
	}
}
