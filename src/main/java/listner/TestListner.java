package listner;

import java.io.File;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import io.qameta.allure.Attachment;

public class TestListner implements ITestListener {

	private static String getTestName(ITestResult test) {
		return test.getMethod().getConstructorOrMethod().getName();
	}

	@Attachment
	public byte[] saveFailedTestScreenShot(WebDriver driver) {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	}

	@Attachment(value = "{0}", type = "text/plain")
	public static String saveTextLog(String log) {
		return log;
	}
}
