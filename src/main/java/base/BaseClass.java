package base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseClass {
	protected static WebDriver driver = null;
	protected static final Logger log = LogManager.getLogger(BaseClass.class);
	static ConfigManager sys = new ConfigManager();

	@BeforeMethod(alwaysRun = true)
	public static WebDriver init() {
		if (sys.getProperty("browser").equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (sys.getProperty("browser").equalsIgnoreCase("Edge")) {
			driver = new EdgeDriver();
		} else if (sys.getProperty("browser").equalsIgnoreCase("internetExlorer")) {
			driver = new InternetExplorerDriver();
		} else {
			driver = new ChromeDriver();
		}
		driver.manage().window().maximize();
		log.info(sys.getProperty("browser") + " browser initialized.");
		return driver;
	}

	@AfterMethod
	public void close() {
		if (driver != null) {
			driver.manage().deleteAllCookies();
			driver.quit();
		}

	}

	public static WebDriver getDriver() {
		return driver;
	}

}
