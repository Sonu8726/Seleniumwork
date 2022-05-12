package test;

import org.openqa.selenium.By;

import base.BaseClass;
import selenium.SafeActions;

public class HomePage extends BaseClass {

	@org.testng.annotations.Test
	public void Test() {

		SafeActions safeAction = new SafeActions(driver);
		driver.get("https://rahulshettyacademy.com/angularpractice/");

		safeAction.safeClick(By.xpath("//a[text()='Shop']"));
		
		//a[contains(text(),'Checkout')]

	}
}
