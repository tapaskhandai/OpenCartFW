package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;

public class TC02_LoginTest extends BaseClass {

	@Test(groups = {"sanity"})
	public void verify_Login() {

		logger.info("****TC02 Started*******");

		// home page
		HomePage homepage = new HomePage(driver);
		homepage.clickMyAccount();
		homepage.clickLogin();

		// login page
		LoginPage login = new LoginPage(driver);
		login.enterEmail(pro.getProperty("email"));
		login.enterPassword(pro.getProperty("password"));
		login.clickLogin();

		// my account page
		MyAccountPage accountpage = new MyAccountPage(driver);

		Assert.assertEquals(accountpage.isMyAccountPageExist(), true, "Login Failed");
		logger.info("Login Successful");
		logger.info("****TC02 Completed*******");

	}

}
