package Automation.Claims.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Automation.Claims.testBase.TestBase;

public class Claimatic_Login extends TestBase {

	public static final Logger log = Logger.getLogger(Claimatic_Login.class.getName());

	WebDriver driver;

	@FindBy(id="username")//Text Box
	WebElement username;

	@FindBy(id="password")//Text Box
	WebElement password;

	@FindBy(xpath="//*[@id=\"login-form\"]/button")//Button
	WebElement login;


	public Claimatic_Login(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver=driver;		
	}

	public void TypeUsername(String TypeUsername) {
		username.clear();
		username.sendKeys(TypeUsername);
		Log("Entered Claimatic Username: " + TypeUsername);
	}

	public void TypePassword(String TypePassword) {
		password.clear();
		password.sendKeys(TypePassword);
		Log("Entered Claimatic Password: " + TypePassword);
	}

	public void ClickLogin() {
		login.click();
		Log("Clicked the \"Login\" button on the Claimatic Login Page");
	}
}