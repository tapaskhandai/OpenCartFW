package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {

		super(driver);

	}

	@FindBy(xpath = "//input[@id='input-email']")
	WebElement textbox_emailAddress;
	@FindBy(xpath = "//input[@id='input-password']")
	WebElement textbox_password;
	@FindBy(xpath = "//input[@value='Login']")
	WebElement button_login;

	public void enterEmail(String email) {
		textbox_emailAddress.sendKeys(email);
	}

	public void enterPassword(String pass) {
		textbox_password.sendKeys(pass);
	}

	public void clickLogin() {
		button_login.click();
	}

}
