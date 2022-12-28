package listner;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import base.BaseClass;
import io.qameta.allure.Attachment;

public class TestListner implements ITestListener {

	private static String getTestMethodName(ITestResult itr) {
		return itr.getMethod().getConstructorOrMethod().getName();
	}

	@Attachment(value = "Screenshot", type = "image/png")
	public byte[] saveAsScreenshotAsPNG(WebDriver driver) {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	}

	@Attachment(value = "{0}", type = "text/plain")
	public static String saveTextLong(String txt) {
		return txt;
	}

	@Override
	public void onFinish(ITestContext arg0) {
		try {
			ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c",
					"cd \"C:\\Users\\5onuk\\git\\Seleniumwork\" && allure serve allure-results");
			builder.redirectErrorStream(true);
			Process p = builder.start();
			BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line;
			while (true) {
				line = r.readLine();
				if (line == null) {
					break;
				}
				System.out.println(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailure(ITestResult arg0) {
		System.out.println(getTestMethodName(arg0) + " failed.");

		WebDriver driver = BaseClass.init();

		if (driver instanceof WebDriver) {
			System.out.println("Capturing Screenshot for " + getTestMethodName(arg0));
			saveAsScreenshotAsPNG(driver);
		} else {
			System.out.println("NOOONTPNSJ FKNJFNSJKFNJHSB FJKF HNJS NDHBSF NSKJFBHFBFH");
		}

		saveTextLong(getTestMethodName(arg0) + " is failed and screenshot captured successfully.");
	}

	@Override
	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestStart(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestSuccess(ITestResult arg0) {
		// TODO Auto-generated method stub

	}
}
