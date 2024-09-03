package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import utilities.DataProviders;

public class TC03_LoginDDT extends BaseClass {

	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class, groups = { "sanity", "Regression" }) // getting
																													// data
																													// from
																													// a
																													// different																												// class
	public void verify_login_DDT(String email, String password) {

		logger.info("****TC03 Started*******");

		// home page
		HomePage homepage = new HomePage(driver);
		homepage.clickMyAccount();
		homepage.clickLogin();

		// login page
		LoginPage login = new LoginPage(driver);
		login.enterEmail(email);
		login.enterPassword(password);
		login.clickLogin();

		// my account page
		MyAccountPage accountpage = new MyAccountPage(driver);

		boolean status = accountpage.isMyAccountPageExist();
		if (status == true) {
			Assert.assertTrue(true);
			logger.info("Login Successful");
			accountpage.clickLogout();
		}

		else {
			logger.info("Login Failed");
			Assert.assertTrue(false);
		}
		logger.info("****TC03 Completed*******");
	}
}
