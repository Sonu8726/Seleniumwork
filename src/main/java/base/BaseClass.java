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

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	protected static WebDriver driver = null;
	private static final Logger log = LogManager.getLogger(BaseClass.class);
	ConfigManager sys = new ConfigManager();

	@BeforeTest(alwaysRun = true)
	public WebDriver init() {
		if (sys.getProperty("browser").equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (sys.getProperty("browser").equalsIgnoreCase("Edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else if (sys.getProperty("browser").equalsIgnoreCase("internetExlorer")) {
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
		} else {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		driver.manage().window().maximize();
		log.info(sys.getProperty("browser") + " browser initialized.");
		return driver;
	}

	@AfterTest
	public void close() {
		driver.quit();
	}

}
