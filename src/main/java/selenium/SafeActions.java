package selenium;

import static org.testng.Assert.assertEquals;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.qameta.allure.Step;

public class SafeActions extends Sync {

	WebDriver driver;
	private static final Logger log = LogManager.getLogger(SafeActions.class);

	public SafeActions(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	/**
	 * It will launch the URL
	 * 
	 * @param url - Application Web url
	 */
	@Step("Opening the url: {0}")
	public void openURL(String url) {
		driver.get(url);
		log.info(url + " link is opened in the browser.");
	}

	/**
	 * It will highlight the element. It will add a border which will be use to
	 * identify the correct element while execution
	 * 
	 * @param element - Element locator
	 */
	@Step("Highlighting the element: {0}")
	private void highlightElement(By element) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		String attributeValue = "border:2px solid red;";
		String getAttribute = driver.findElement(element).getAttribute("style");
		jse.executeScript("arguments[0].setAttribute('style',arguments[1]);", driver.findElement(element),
				attributeValue);
		try {
			Thread.sleep(300);
         } catch (InterruptedException e) {
            e.printStackTrace();
         }
		jse.executeScript("arguments[0].setAttribute('style',arguments[1]);", driver.findElement(element),
				getAttribute);
		log.info("Element is highlighted");
	}

	/**
	 * This function will be used to Validate the current page title.
	 * 
	 * @param expectedTitle - What will be the title
	 */
	@Step("Validating the current Page title is : {0}")
	public void safeValidateThePageTitle(String expectedTitle) {
		String actualTitle = driver.getTitle();
		assertEquals(actualTitle, expectedTitle);
	}

	/**
	 * This function will be used for the click operation.
	 * 
	 * @param elementLocator - Element locator
	 * @param elementName    - A name for the element
	 * @param timeout        - timeout (in seconds)
	 */
	@Step("Perform click on the element: {1}")
	public void safeClick(By elem, String elementName, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(elem));
			highlightElement(elem);
			driver.findElement(elem).click();
			log.info(elementName + " clicked.");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This function will be used for the click operation using Actions class of
	 * Selenium.
	 * 
	 * @param elementLocator - Element locator
	 * @param elementName    - A name for the element
	 * @param timeout        - timeout (in seconds)
	 */
	@Step("Perform click on the element: {1}")
	public void safeClickUsingActionsClass(By elem, String elementName, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		Actions actn = new Actions(driver);
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(elem));
			highlightElement(elem);
			WebElement element = driver.findElement(elem);
			actn.click(element).build().perform();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This function will be used for the double click operation using Actions class
	 * of Selenium.
	 * 
	 * @param elementLocator - Element locator
	 * @param elementName    - A name for the element
	 * @param timeout        - timeout (in seconds)
	 */
	@Step("Perform double Click on the element: {1}")
	public void safeDoubleClick(By elem, String elementName, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		Actions actn = new Actions(driver);
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(elem));
			highlightElement(elem);
			WebElement element = driver.findElement(elem);
			actn.doubleClick(element).build().perform();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This function will be used for the right click operation using Actions class
	 * of Selenium.
	 * 
	 * @param elementLocator - Element locator
	 * @param elementName    - A name for the element
	 * @param timeout        - timeout (in seconds)
	 */
	@Step("Perform Context/Right Click on the element: {1}")
	public void safeContextClick(By elem, String elementName, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		Actions actn = new Actions(driver);
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(elem));
			highlightElement(elem);
			WebElement element = driver.findElement(elem);
			actn.contextClick(element).build().perform();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This function will be used to type something into the element.
	 * 
	 * @param elementLocator - Element locator
	 * @param value          - typing message/text
	 * @param elementName    - A name for the element
	 * @param timeout        - timeout (in seconds)
	 */
	@Step("Typing {1} into the element {2}")
	public void safeType(By elem, String value, String elementName, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(elem));
			highlightElement(elem);
			driver.findElement(elem).sendKeys(value);
			log.info("Type" + value + " in to the element " + driver.findElement(elem).getText());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
