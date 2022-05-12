package selenium;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SafeActions {

	WebDriver driver;
	private static final Logger log = LogManager.getLogger(SafeActions.class);

	public SafeActions(WebDriver driver) {
		this.driver = driver;
	}

	public void openURL(String url) {
		driver.get(url);
		log.info(url + " link is opened in the browser.");
	}

	public void highlightElement(By element) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		String attributeValue = "border:2px solid red;";
		String getAttribute = driver.findElement(element).getAttribute("style");
		jse.executeScript("arguments[0].setAttribute('style',arguments[1]);", driver.findElement(element), attributeValue);
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		jse.executeScript("arguments[0].setAttribute('style',arguments[1]);", driver.findElement(element), getAttribute);
		log.info("Element is highlighted");
	}

	public void safeClick(By elem) {
		highlightElement(elem);
		driver.findElement(elem).click();
		log.info(driver.findElement(elem).getText() + " clicked.");
	}

	public void safeType(By elem, String value, String elementName, int timeout) {
		highlightElement(elem);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(elem)));
		driver.findElement(elem).sendKeys(value);
		log.info("Type" + value + " in to the element " + driver.findElement(elem).getText());
	}
}
