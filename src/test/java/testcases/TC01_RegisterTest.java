package testcases;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.RegisterPage;

public class TC01_RegisterTest extends BaseClass {

	@Test(groups = { "smoke", "Regression" })
	public void verify_register() {

		logger.info("TC01 Started");

		HomePage homepage = new HomePage(driver);
		homepage.clickMyAccount();
		homepage.clickregister();
		RegisterPage regpage = new RegisterPage(driver);
		regpage.enterFirstName(generateRandomText());
		regpage.enterLastName(generateRandomText());
		regpage.enterEmail(generateRandomText() + "@gmail.com");
		regpage.enterTelNum(randomNumber());
		String password = generateRandomText();
		regpage.enterPassword(password);
		regpage.entercnfPassword(password);
		regpage.clickPrivacyPolicy();
		// regpage.clickContinue();
		boolean status = driver.findElement(By.xpath("//h1[text()='Your Account Has Been Created!']")).isDisplayed();
		if (status == true) {
			logger.info("Test Passed");
		}
		logger.info("TC01 Completed");

	}

	@Test(groups = { "smoke" })
	public void verify_register2() {

		logger.info("TC01 Started");
		logger.info("Demo Test");
	}
}
