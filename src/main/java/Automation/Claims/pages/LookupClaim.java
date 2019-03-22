package Automation.Claims.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Automation.Claims.testBase.TestBase;

public class LookupClaim extends TestBase {
	
	public static final Logger log = Logger.getLogger(LookupClaim.class.getName());
	
	WebDriver driver;
	
	@FindBy(id="txtClaimID")//Text Field
	WebElement claimnumber;
	
	@FindBy(id="submit1")//Button
	WebElement lookup;
	
	public LookupClaim(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver=driver;		
	}
	
	public void TypeClaimNumber(String TypeClaimNumber) {
		claimnumber.clear();
		claimnumber.sendKeys(TypeClaimNumber);
			Log("Entered Claim Number: " + TypeClaimNumber);
	}
	
	public void ClickLookup() {
		lookup.click();
			Log("Clicked the \"Lookup\" button on the Claim Lookup Page");
	}
}
