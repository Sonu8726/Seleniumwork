package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SafeActions {

	WebDriver driver;

	public SafeActions(WebDriver driver) {
		this.driver = driver;
	}

	public void safeClick(By elem) {
		driver.findElement(elem).click();
	}

}
