package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
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
	protected Properties prop = new Properties();
	String systemPropertiesFile = System.getProperty("user.dir") + "\\config\\System.properties";
	String appPropertiesFile = System.getProperty("user.dir") + "\\config\\System.properties";

	@BeforeTest(alwaysRun = true)
	public WebDriver initBrowser() {
		try {
			FileInputStream fis = new FileInputStream(new File(systemPropertiesFile));
			prop.load(fis);
			String browser = prop.getProperty("browser");
			
			if (browser.equalsIgnoreCase("firefox")) {
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
			} else if (browser.equalsIgnoreCase("Edge")) {
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
			} else if (browser.equalsIgnoreCase("internetExlorer")) {
				WebDriverManager.iedriver().setup();
				driver = new InternetExplorerDriver();
			} else {
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(prop.getProperty("IMPLICET_TIME"))));
		return driver;
	}

	@AfterTest
	public void close() {
		driver.quit();
	}

}
