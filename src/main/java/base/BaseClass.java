package base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseClass {
	protected static WebDriver driver = null;
	protected static final Logger log = LogManager.getLogger(BaseClass.class);
	ConfigManager sys = new ConfigManager();

	@BeforeTest(alwaysRun = true)
	public static WebDriver init() {
		BaseClass bc = new BaseClass();
		if (bc.sys.getProperty("browser").equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (bc.sys.getProperty("browser").equalsIgnoreCase("Edge")) {
			driver = new EdgeDriver();
		} else if (bc.sys.getProperty("browser").equalsIgnoreCase("internetExlorer")) {
			driver = new InternetExplorerDriver();
		} else {
			driver = new ChromeDriver();
		}
		driver.manage().window().maximize();
		log.info(bc.sys.getProperty("browser") + " browser initialized.");
		return driver;
	}

	@AfterTest
	public void close() {
		if (driver != null) {
			BaseClass bc = new BaseClass();
			driver.manage().deleteAllCookies();
			driver.quit();
			log.info(bc.sys.getProperty("browser") + " browser closed successfully.");
		}

	}

	public static WebDriver getDriver() {
		return driver;
	}

}
