package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {

	public MyAccountPage(WebDriver driver) {

		super(driver);

	}

	@FindBy(xpath = "//h2[normalize-space()='My Account']")
	WebElement header_MyAccount;
	@FindBy(xpath = "//a[@class='list-group-item'][normalize-space()='Logout']")
	WebElement link_logout;

	public boolean isMyAccountPageExist() {

		try {
			return header_MyAccount.isDisplayed();
		} catch (Exception e) {

			return false;
		}
	}

	public void clickLogout() {
		link_logout.click();
	}

}
