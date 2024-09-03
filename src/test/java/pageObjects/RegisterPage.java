package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterPage extends BasePage {

	public RegisterPage(WebDriver driver) {

		super(driver);
	}

	@FindBy(xpath = "//input[@id='input-firstname']")
	WebElement textbox_1stname;
	@FindBy(xpath = "//input[@id='input-lastname']")
	WebElement textbox_lastname;
	@FindBy(xpath = "//input[@id='input-email']")
	WebElement textbox_email;
	@FindBy(xpath = "//input[@id='input-telephone']")
	WebElement textbox_telephone;
	@FindBy(xpath = "//input[@id='input-password']")
	WebElement textbox_password;
	@FindBy(xpath = "//input[@id='input-confirm']")
	WebElement textbox_cnfpassword;
	@FindBy(xpath = "//input[@name='agree']")
	WebElement checkbox_privacypolicy;
	@FindBy(xpath = "//input[@value='Continue']")
	WebElement button_Continue;

	public void enterFirstName(String firstName) {
		textbox_1stname.sendKeys(firstName);
	}

	public void enterLastName(String LastName) {
		textbox_lastname.sendKeys(LastName);
	}

	public void enterEmail(String email) {
		textbox_email.sendKeys(email);
	}

	public void enterTelNum(String tnumber) {
		textbox_telephone.sendKeys(tnumber);
	}

	public void enterPassword(String password) {
		textbox_password.sendKeys(password);
	}

	public void entercnfPassword(String cnfPassword) {
		textbox_cnfpassword.sendKeys(cnfPassword);
	}

	public void clickPrivacyPolicy() {
		checkbox_privacypolicy.click();
	}

	public void clickContinue() {
		button_Continue.click();
	}

}
